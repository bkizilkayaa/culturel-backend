package com.bkizilkaya.culturelbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "ZIP_CODES")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ZipCode {
    @Id
    @GeneratedValue(generator = "genZipCodeSeq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "genZipCodeSeq", sequenceName = "SEQ_ZIP_CODES", initialValue = 1000, allocationSize = 1)
    private Long Id;
    @Column
    private String name;
    @Column
    private String value;
    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @Column(name = "MODIFIED_DATE")
    private LocalDateTime modifiedDate;
}
