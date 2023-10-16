package com.bkizilkaya.culturelbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "artworks")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Artwork {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long Id;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private Date createDate;
    @Column
    private Double coordinateX;
    @Column
    private Double coordinateY;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "artwork")
    private List<Image> images;
    @Column
    private Long authorId;

}
