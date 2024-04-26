package com.muratcimen.personelyonetimi.controller;

import com.muratcimen.personelyonetimi.entity.Calisan;
import com.muratcimen.personelyonetimi.enums.Departman;
import com.muratcimen.personelyonetimi.service.CalisanService;
import lombok.Data;
import lombok.Getter;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@RestController
@RequestMapping("/calisan")
@Data
public class IndexController {

    @Autowired
    CalisanService calisanService;
    private List<Calisan> calisanList;
    private Calisan selectedCalisan;
    private List<Calisan> filteredCalisanList;
    private List<Departman> departmanList;
    private Departman selectedDepartman;
    private static final Logger logger =
            LoggerFactory.getLogger(IndexController.class);

    @PostConstruct
    public void init() {
        veriGetir();
    }

    public void veriGetir() {
        this.calisanList = calisanService.getAllCalisan();
        this.filteredCalisanList = new ArrayList<>(calisanList);
        calisanList.forEach((calisan) -> logger.info(calisan.toString()));
        this.departmanList = Arrays.asList(Departman.values());
    }

    public void yeniEkle() {
        this.selectedCalisan = new Calisan();
    }

    public void kaydet() {
        // Ekle
        if (this.selectedCalisan.getCalisanID() == null) {
            this.selectedCalisan.getDepartman().setDepartmanID(1L);
            this.calisanService.saveCalisan(this.selectedCalisan);
            this.calisanList.add(this.selectedCalisan);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Calişan Eklendi"));
        } else { // Güncelle
            this.calisanService.saveCalisan(this.selectedCalisan);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Çalışan bilgileri güncellendi"));
        }
        // Pencereyi gizliyoruz
        PrimeFaces.current().executeScript("PF('modalAccountwindow').hide()");
        // Tabloyu güncelliyoruz
        PrimeFaces.current().ajax().update("calisan-form:mesaj",
                "calisan-form:calisan-tablo");
    }

    public void sil() {
        this.calisanService.deleteCalisan(this.selectedCalisan);
        this.calisanList.remove(this.selectedCalisan);
        this.selectedCalisan = null;
        logger.info("Çalışan silindi: " + this.selectedCalisan);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Çalışan kaydı silindi"));
        PrimeFaces.current().ajax().update("calisan-form:mesaj",
                "calisan-form:calisan-tablo");
    }

    public String ortalamaMaas() {
        if (!filteredCalisanList.isEmpty()) {
            BigDecimal totalSalary = BigDecimal.ZERO;
            for (Calisan calisan : filteredCalisanList) {
                totalSalary = totalSalary.add(calisan.getMaas());
            }
            BigDecimal averageSalary = totalSalary.divide(BigDecimal.valueOf(filteredCalisanList.size()), 2, RoundingMode.HALF_UP);
            return averageSalary.toString() + " $";
        } else {
            return "0 $";
        }
    }
}
