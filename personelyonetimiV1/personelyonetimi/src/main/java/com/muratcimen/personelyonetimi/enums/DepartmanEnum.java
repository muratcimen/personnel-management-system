package com.muratcimen.personelyonetimi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DepartmanEnum {
    IK("İK", 1),
    MUHASEBE("Muhasebe", 2),
    SATIS("Satış", 3);

    private final String adi;
    private final int id;

    public static int getIdByAdi(String adi) {
        for (DepartmanEnum departman : DepartmanEnum.values()) {
            if (departman.getAdi().equals(adi)) {
                return departman.getId();
            }
        }
        return -1;
    }
}
