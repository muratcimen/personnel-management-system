package com.muratcimen.personelyonetimi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Departman {
    IK("İK", 1),
    MUHASEBE("Muhasebe", 2),
    SATIS("Satış", 3);

    private final String adi;
    private final int id;
}
