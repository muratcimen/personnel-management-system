package com.muratcimen.personelyonetimi.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Departman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmanID;

    @Column(name = "adi", nullable = false)
    private String adi;

    @Column(name = "tanimi")
    private String tanimi;

}