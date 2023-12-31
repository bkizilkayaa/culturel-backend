package com.bkizilkaya.culturelbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "zip_codes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ZipCode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column
    private String name;
    @Column
    private String value;
}
