package com.muratcimen.personelyonetimi.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaporRequestDTO {

    private Date olusturmaTarihi;
    private String raporTipi;
    private String veriler; // Büyük veriler için alternatif düşünün (daha sonra tartışılacaktır)
}