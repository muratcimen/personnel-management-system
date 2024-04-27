package com.muratcimen.personelyonetimi.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Rapor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long raporID;

    @Column(name = "olusturmaTarihi", nullable = false)
    private Date olusturmaTarihi;

    @Column(name = "raporTipi", nullable = false)
    private String raporTipi;

    @Lob
    @Column(name = "veriler")
    private String veriler;



}
