package com.bkizilkaya.culturelbackend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "FILE_DATA")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name = "NAME")
    private String name;
    @Column(name = "TYPE")
    private String type;

    @ManyToOne
    @JoinTable(name = "artwork_images",
            joinColumns = @JoinColumn(name = "image_id"),
            inverseJoinColumns = @JoinColumn(name = "artwork_id"))
    private Artwork artworkImages;
}