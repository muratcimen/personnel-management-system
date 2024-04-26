package com.muratcimen.personelyonetimi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@ToString
public class Calisan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long calisanID;

    @Column(name = "adi", nullable = false)
    private String adi;

    @Column(name = "soyadi", nullable = false)
    private String soyadi;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departmanID")
    private Departman departman;

    @Column(name = "maas")
    private BigDecimal maas;

    public Calisan() {
        this.departman = new Departman();
    }

}
