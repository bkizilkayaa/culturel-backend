package com.bkizilkaya.culturelbackend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TOURIST_SPOT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TouristSpot {
    @Id
    @GeneratedValue(generator = "genTouristSpotSeq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "genTouristSpotSeq", sequenceName = "SEQ_TOURIST_SPOT", initialValue = 1000, allocationSize = 1)
    private Long Id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CONTENT", length = 65535)
    @Lob
    private String content;

    @Column(name = "PARENT_ID")
    private Long parentId;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @Column(name = "MODIFIED_DATE")
    private LocalDateTime modifiedDate;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "touristSpotImages", cascade = CascadeType.ALL)
    private List<FileData> fileData;

    @Column
    private Long authorId;

    @ManyToOne(fetch = FetchType.LAZY)
    private ZipCode zipCode;

}
