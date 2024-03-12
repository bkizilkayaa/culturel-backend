package com.bkizilkaya.culturelbackend.model;

import com.bkizilkaya.culturelbackend.dto.artwork.response.ArtworkResponseDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "IMAGES")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE")
    private String type;

    private LocalDateTime date;

    @Lob
    @Column(name = "IMAGE_DATA", length = 65555)
    private byte[] imageData;

    @ManyToOne
    @JoinColumn(name = "artworkId")
    private Artwork artwork;

    @JsonBackReference
    public Artwork getArtwork(){
        return artwork;
    }
}
