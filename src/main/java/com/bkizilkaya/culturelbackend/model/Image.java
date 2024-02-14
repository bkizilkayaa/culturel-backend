package com.bkizilkaya.culturelbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Blob;
import java.util.Date;

@Entity
@Table(name = "images")
@RequiredArgsConstructor
@Getter
@Setter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private Blob image;

    private Date date = new Date();

    @ManyToOne
    @JoinColumn(name = "artworkId")
    private Artwork artwork;

    @JsonBackReference
    public Artwork getArtwork(){
        return artwork;
    }
}
