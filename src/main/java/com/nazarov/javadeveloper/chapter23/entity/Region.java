package com.nazarov.javadeveloper.chapter23.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "regions")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Exclude
    private Long id;

    @Column(name = "name")
    private String name;

    public Region(String name) {
        this.name = name;
    }
}