package com.muratcimen.personelyonetimi.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Data
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

    public Departman() {}
    public Departman(Long id) {this.departmanID = id;}

}