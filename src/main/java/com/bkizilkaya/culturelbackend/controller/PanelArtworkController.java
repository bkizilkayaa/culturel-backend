package com.bkizilkaya.culturelbackend.controller;

import com.bkizilkaya.culturelbackend.dto.artwork.request.ArtworkCreateDTO;
import com.bkizilkaya.culturelbackend.dto.artwork.response.ArtworkResponseDTO;
import com.bkizilkaya.culturelbackend.service.abstraction.ArtworkService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/artworks-list")
public class PanelArtworkController {
    private final ArtworkService artworkService;

    public PanelArtworkController(ArtworkService artworkService) {
        this.artworkService = artworkService;
    }

    @GetMapping()
    public String artworkPanel(Model model) {
        return findPaginated(1, model);
    }

    @PostMapping("/addArtwork")
    public String addArtworkPanel(@ModelAttribute("artwork") ArtworkCreateDTO model) {
        artworkService.addArtwork(model);
        return "redirect:/artworks-list";
    }

    @PostMapping("/saveArtwork")
    public String saveArtworkPanel(@ModelAttribute("artwork") ArtworkCreateDTO model) {
        model.setModifiedDate(LocalDateTime.now());
        artworkService.addArtwork(model);
        return "redirect:/artworks-list";
    }

    @GetMapping("/showNewArtworkForm")
    public String showNewArtworkForm(Model model) {
        ArtworkCreateDTO artworkCreateDTO = new ArtworkCreateDTO();
        model.addAttribute("artwork", artworkCreateDTO);
        return "new_artwork";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Long id, Model model) {
        ArtworkResponseDTO artworkResponseDTO = artworkService.getArtworkGivenId(id);

        model.addAttribute("artwork", artworkResponseDTO);
        return "update_artwork";
    }

    @GetMapping("/deleteArtwork/{id}")
    public String deleteArtwork(@PathVariable(value = "id") Long id) {
        this.artworkService.deleteArtwork(id);
        return "redirect:/artworks-list";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 5;
        Page<ArtworkResponseDTO> page = artworkService.findPaginated(pageNo, pageSize);
        List<ArtworkResponseDTO> listOfArtworks = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listArtworks", listOfArtworks);
        return "artworks";
    }
}
