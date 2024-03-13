package com.bkizilkaya.culturelbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    public Artwork getArtwork() {
        return artwork;
    }
}
