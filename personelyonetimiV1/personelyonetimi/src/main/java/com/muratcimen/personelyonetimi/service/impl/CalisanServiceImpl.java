package com.muratcimen.personelyonetimi.service.impl;

import com.muratcimen.personelyonetimi.dto.responseDTO.CalisanResponseDTO;
import com.muratcimen.personelyonetimi.entity.*;
import com.muratcimen.personelyonetimi.enums.DepartmanEnum;
import com.muratcimen.personelyonetimi.mapper.EntityDtoMapper;
import com.muratcimen.personelyonetimi.repository.CalisanRepository;
import com.muratcimen.personelyonetimi.service.CalisanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.faces.bean.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
@ApplicationScoped
public class CalisanServiceImpl implements CalisanService {

    @Autowired
    private CalisanRepository calisanRepository;

    @Autowired
    private EntityDtoMapper entityDtoMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public ResponseEntity<CalisanResponseDTO> saveCalisan(Calisan calisan) {
        calisan.getDepartman().setDepartmanID((long) DepartmanEnum.getIdByAdi(calisan.getDepartman().getAdi()));
        entityManager.persist(calisan);
        CalisanResponseDTO calisanResponseDTO = entityDtoMapper.toCalisanResponseDTO(calisan);
        return new ResponseEntity<>(calisanResponseDTO, HttpStatus.CREATED);
    }


    @Override
    public List<Calisan> getAllCalisan() {
        String hql = "FROM Calisan";
        Query query = entityManager.createQuery(hql);
        List<Calisan> calisanlar = query.getResultList();
        for (Calisan calisan : calisanlar) {
            calisan.setDepartmanAdi(calisan.getDepartman().getAdi());
        }
        return calisanlar;
    }


    @Override
    public ResponseEntity<CalisanResponseDTO> getCalisanById(Long id) {
        String hql = "FROM Calisan c WHERE c.calisanID = :id";
        Query query = entityManager.createQuery(hql);
        query.setParameter("id", id);
        Calisan calisan = (Calisan) query.getSingleResult();
        return new ResponseEntity<>(entityDtoMapper.toCalisanResponseDTO(calisan), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<Void> updateCalisan(Calisan calisan) {
        String hql = "UPDATE Calisan c SET c.adi = :adi, c.soyadi = :soyadi, c.departman = :departman, c.maas = :maas WHERE c.calisanID = :calisanID";
        Query query = entityManager.createQuery(hql);

        query.setParameter("adi", calisan.getAdi());
        query.setParameter("soyadi", calisan.getSoyadi());
        query.setParameter("departman", new Departman((long) DepartmanEnum.getIdByAdi(calisan.getDepartman().getAdi())));
        query.setParameter("maas", calisan.getMaas());
        query.setParameter("calisanID", calisan.getCalisanID());
        int updatedCount = query.executeUpdate();
        if (updatedCount > 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Void> deleteCalisan(Long id) {
        String hql = "DELETE FROM Calisan c WHERE c.calisanID = :id";
        Query query = entityManager.createQuery(hql);
        query.setParameter("id", id);
        int deleteCount = query.executeUpdate();
        if (deleteCount > 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Void> deleteCalisan(Calisan calisan) {
        if (calisan.getCalisanID() != null) {
            return deleteCalisan(calisan.getCalisanID());
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
