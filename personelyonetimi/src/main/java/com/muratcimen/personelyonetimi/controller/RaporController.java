package com.muratcimen.personelyonetimi.controller;

import com.muratcimen.personelyonetimi.entity.Calisan;
import com.muratcimen.personelyonetimi.enums.Departman;
import com.muratcimen.personelyonetimi.service.CalisanService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/rapor")
@AllArgsConstructor
@Data
public class RaporController {

    @Autowired
    private final CalisanService calisanService;
    private List<Calisan> calisanList;
    private List<Departman> departmanList;
    private static final Logger logger =
            LoggerFactory.getLogger(IndexController.class);

    @PostConstruct
    public void init() {
        veriGetir();
    }

    public void veriGetir() {
        this.calisanList = calisanService.getAllCalisan();
        calisanList.forEach((calisan) -> logger.info(calisan.toString()));
        this.departmanList = Arrays.asList(Departman.values());
    }

    public int calisanSayisi(Departman departman) {
        int count = 0;
        for (Calisan calisan : calisanList) {
            if (calisan.getDepartman().getDepartmanID() == departman.getId()) {
                count++;
            }
        }
        return count;
    }

    public BigDecimal departmanOrtalamaMaas(Departman departman) {
        BigDecimal totalSalary = BigDecimal.ZERO;
        int count = 0;
        for (Calisan calisan : calisanList) {
            if (calisan.getDepartman().getDepartmanID() == departman.getId()) {
                totalSalary = totalSalary.add(calisan.getMaas());
                count++;
            }
        }
        if (count > 0) {
            BigDecimal averageSalary = totalSalary.divide(BigDecimal.valueOf(count), 2, RoundingMode.HALF_UP);
            return averageSalary;
        } else {
            return BigDecimal.ZERO;
        }
    }
}
