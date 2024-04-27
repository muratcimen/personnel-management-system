package com.muratcimen.personelyonetimi.controller;

import com.muratcimen.personelyonetimi.entity.Calisan;
import com.muratcimen.personelyonetimi.service.CalisanService;
import com.muratcimen.personelyonetimi.util.LazyCalisanDataModel;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean("dtIndexView")
@ViewScoped
public class DtIndexView implements Serializable {

    private LazyDataModel<Calisan> lazyModel;
    private Calisan selectedCalisan;
    @Autowired
    private CalisanService calisanService;
    private static final Logger logger =
            LoggerFactory.getLogger(DtIndexView.class);

    @PostConstruct
    public void init() {
        veriGetir();

    }

    public void veriGetir() {
        lazyModel = new LazyCalisanDataModel(calisanService.getAllCalisan());
    }

    public void yeniEkle() {
        selectedCalisan = new Calisan();
    }

    public void kaydet() {
        // Ekle
        if (selectedCalisan.getCalisanID() == null) {
            selectedCalisan.getDepartman().setDepartmanID(1L);
            calisanService.saveCalisan(selectedCalisan);
            lazyModel.getWrappedData().add(selectedCalisan);
            logger.info("Çalışan eklendi: " + selectedCalisan);
            selectedCalisan = null;
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Çalışan Eklendi"));
        } else { // Güncelle
            calisanService.updateCalisan(selectedCalisan);
            logger.info("Çalışan güncellendi: " + selectedCalisan);
            selectedCalisan = null;
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
        calisanService.deleteCalisan(selectedCalisan);
        lazyModel.getWrappedData().remove(selectedCalisan);
        logger.info("Çalışan silindi: " + selectedCalisan);
        selectedCalisan = null;
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Çalışan kaydı silindi"));
        PrimeFaces.current().ajax().update("calisan-form:mesaj",
                "calisan-form:calisan-tablo");
    }

    public LazyDataModel<Calisan> getLazyModel() {
        return lazyModel;
    }

    public Calisan getSelectedCalisan() {
        return selectedCalisan;
    }

    public void setSelectedCalisan(Calisan selectedCalisan) {
        this.selectedCalisan = selectedCalisan;
    }

    public void setService(CalisanService calisanService) {
        this.calisanService = calisanService;
    }

    public void onRowSelect(SelectEvent<Calisan> event) {
        FacesMessage msg = new FacesMessage("Calisan Seçildi", String.valueOf(event.getObject().getCalisanID()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
