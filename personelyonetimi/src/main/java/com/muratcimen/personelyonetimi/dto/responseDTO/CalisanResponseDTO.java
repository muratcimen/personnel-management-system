package com.muratcimen.personelyonetimi.dto.responseDTO;

import com.muratcimen.personelyonetimi.entity.Departman;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalisanResponseDTO {

    private Long calisanID;
    private String adi;
    private String soyadi;
    private Departman departman;
    private BigDecimal maas;
}
