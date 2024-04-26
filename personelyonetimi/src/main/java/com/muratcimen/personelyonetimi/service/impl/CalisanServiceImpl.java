package com.muratcimen.personelyonetimi.service.impl;

import com.muratcimen.personelyonetimi.dto.responseDTO.CalisanResponseDTO;
import com.muratcimen.personelyonetimi.entity.Calisan;
import com.muratcimen.personelyonetimi.mapper.EntityDtoMapper;
import com.muratcimen.personelyonetimi.repository.CalisanRepository;
import com.muratcimen.personelyonetimi.service.CalisanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalisanServiceImpl implements CalisanService {

    @Autowired
    private CalisanRepository calisanRepository;

    @Autowired
    private EntityDtoMapper entityDtoMapper;

    @Override
    public ResponseEntity<CalisanResponseDTO> saveCalisan(Calisan calisan) {
        calisan = calisanRepository.save(calisan);
        CalisanResponseDTO calisanResponseDTO = entityDtoMapper.toCalisanResponseDTO(calisan);
        return new ResponseEntity<>(calisanResponseDTO, HttpStatus.CREATED);
    }

    @Override
    public List<Calisan> getAllCalisan() {
        List<Calisan> calisanlar = calisanRepository.findAll();

        return calisanlar;
    }

    @Override
    public ResponseEntity<CalisanResponseDTO> getCalisanById(Long id) {
        Optional<Calisan> calisanOptional = calisanRepository.findById(id);
        return calisanOptional.map(calisan -> new ResponseEntity<>(entityDtoMapper.toCalisanResponseDTO(calisan), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Void> deleteCalisan(Long id) {
        calisanRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> deleteCalisan(Calisan calisan) {
        calisanRepository.delete(calisan);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
