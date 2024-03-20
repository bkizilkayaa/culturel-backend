package com.bkizilkaya.culturelbackend.job;

import com.bkizilkaya.culturelbackend.dto.artwork.response.ArtworkResponseDTO;
import com.bkizilkaya.culturelbackend.dto.filedata.response.FileDataResponseDTO;
import com.bkizilkaya.culturelbackend.service.abstraction.ArtworkService;
import com.bkizilkaya.culturelbackend.service.abstraction.StorageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
@Log4j2
public class FileCleanupJob {

    @Value("${local.file.path}")
    private String FOLDER_PATH;
    private final ArtworkService artworkService;
    private final StorageService storageService;

    public FileCleanupJob(ArtworkService artworkService, StorageService storageService) {
        this.artworkService = artworkService;
        this.storageService = storageService;
    }

    @Scheduled(cron = "0 0 */2 * * *")
    public void cleanupData() {
        log.warn("FileCleanupJob basladi...");
        List<FileDataResponseDTO> allFileDatas = storageService.getAll();
        List<Long> allFileDataIds = new ArrayList<>(allFileDatas.stream().map(FileDataResponseDTO::getId).toList());

        List<ArtworkResponseDTO> allArtworks = artworkService.getAllArtworks();

        List<Long> allArtworksFileDataIds = allArtworks.stream()
                .flatMap(artwork -> artwork.getFileData().stream().map(FileDataResponseDTO::getId))
                .toList();

        allFileDataIds.removeAll(allArtworksFileDataIds);
        //allFileDataIds içerisinde kullanılmayan ancak veritabanında mevcut file lar bulunuyor.

        if (allFileDataIds.size() == 0) {
            log.info("Dosya yolu ile veritabani senkron halde!");
        } else {
            List<String> fileNames = allFileDataIds.stream()
                    .map(id -> allFileDatas.stream()
                            .filter(data -> data.getId().equals(id))
                            .findFirst()
                            .map(FileDataResponseDTO::getName)
                            .orElse(null))
                    .toList();

            deleteFilesFromDb(allFileDataIds);
            deleteFilesFromPath(fileNames);
        }
        log.warn("FileCleanupJob bitti");
    }

    public void deleteFilesFromPath(List<String> fileNamesToDelete) {
        log.info("Dosyalar base pathten okunmaya baslaniyor..");
        for (String fileName : fileNamesToDelete) {
            File fileToDelete = new File(FOLDER_PATH + fileName);
            if (fileToDelete.exists()) {
                if (fileToDelete.delete()) {
                    log.info("Dosya basariyla silindi: " + fileName);
                } else {
                    log.error("Dosya silinirken bir hata olustu: " + fileName);
                }
            } else {
                log.error("Silinecek dosya bulunamadi: " + fileName);
            }
        }
    }

    public void deleteFilesFromDb(List<Long> fileIds) {
        log.info("Veritabanindan kullanilmayan dosyalarin kayitlari siliniyor..");
        fileIds.forEach(storageService::deleteFile);
    }
}
