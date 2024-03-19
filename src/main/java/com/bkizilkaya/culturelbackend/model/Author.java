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
@Table(name = "AUTHORS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(generator = "genAuthorSeq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "genAuthorSeq", sequenceName = "SEQ_AUTHORS", initialValue = 1000, allocationSize = 1)
    private Long Id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;
    @Column(name = "MODIFIED_DATE")
    private LocalDateTime modifiedDate;
    @Column(name = "ROLE")
    private Integer role;
}
