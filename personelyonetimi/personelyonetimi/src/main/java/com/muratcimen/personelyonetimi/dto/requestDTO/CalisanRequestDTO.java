package com.muratcimen.personelyonetimi.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalisanRequestDTO {

    private String adi;
    private String soyadi;
    private Long departmanID;
    private BigDecimal maas;
}
