package com.muratcimen.personelyonetimi.dto.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaporResponseDTO {

    private Long raporID;
    private Date olusturmaTarihi;
    private String raporTipi;
    private String veriler;  //Büyük veriler için alternatif düşünün (daha sonra tartışılacaktır)
}