package com.bkizilkaya.culturelbackend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ARTWORKS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Artwork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "PARENT_ID")
    private Long parentId;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "artworkImages", cascade = CascadeType.ALL)
    private List<FileData> fileData;

    @Column
    private Long authorId;

    @JsonManagedReference
    public List<FileData> getFiles() {
        return fileData;
    }

}
