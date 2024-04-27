package com.muratcimen.personelyonetimi.service.impl;

import com.muratcimen.personelyonetimi.dto.responseDTO.DepartmanResponseDTO;
import com.muratcimen.personelyonetimi.entity.Departman;
import com.muratcimen.personelyonetimi.repository.DepartmanRepository;
import com.muratcimen.personelyonetimi.mapper.EntityDtoMapper;
import com.muratcimen.personelyonetimi.service.DepartmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class DepartmanServiceImpl implements DepartmanService {

    @Autowired
    private DepartmanRepository departmanRepository;

    @Autowired
    private EntityDtoMapper entityDtoMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ResponseEntity<List<DepartmanResponseDTO>> getAllDepartmanlar() {
        String hql = "FROM Departman";
        Query query = entityManager.createQuery(hql);
        List<Departman> departmanlar = query.getResultList();
        List<DepartmanResponseDTO> departmanResponseDTOs = entityDtoMapper.toDepartmanResponseDTOs(departmanlar);
        return new ResponseEntity<>(departmanResponseDTOs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DepartmanResponseDTO> getDepartmanById(Long departmanId) {
        String hql = "FROM Departman d WHERE d.id = :id";
        Query query = entityManager.createQuery(hql);
        query.setParameter("id", departmanId);
        Departman departman = (Departman) query.getSingleResult();
        return new ResponseEntity<>(entityDtoMapper.toDepartmanResponseDTO(departman), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<DepartmanResponseDTO> createDepartman(Departman departman) {
        entityManager.persist(departman);
        DepartmanResponseDTO departmanResponseDTO = entityDtoMapper.toDepartmanResponseDTO(departman);
        return new ResponseEntity<>(departmanResponseDTO, HttpStatus.CREATED);
    }


    @Override
    @Transactional
    public ResponseEntity<DepartmanResponseDTO> updateDepartman(Departman departman) {
        departman = entityManager.merge(departman);
        DepartmanResponseDTO departmanResponseDTO = entityDtoMapper.toDepartmanResponseDTO(departman);
        return new ResponseEntity<>(departmanResponseDTO, HttpStatus.OK);
    }


    @Override
    @Transactional
    public ResponseEntity<Void> deleteDepartman(Long departmanId) {
        String hql = "DELETE FROM Departman d WHERE d.id = :id";
        Query query = entityManager.createQuery(hql);
        query.setParameter("id", departmanId);
        int deletedCount = query.executeUpdate();
        if (deletedCount > 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
