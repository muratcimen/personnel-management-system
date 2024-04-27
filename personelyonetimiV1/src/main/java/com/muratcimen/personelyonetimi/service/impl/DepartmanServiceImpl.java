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

import java.util.List;
import java.util.Optional;

@Service
public class DepartmanServiceImpl implements DepartmanService {

    @Autowired
    private DepartmanRepository departmanRepository;

    @Autowired
    private EntityDtoMapper entityDtoMapper;

    @Override
    public ResponseEntity<List<DepartmanResponseDTO>> getAllDepartmanlar() {
        List<Departman> departmanlar = departmanRepository.findAll();
        List<DepartmanResponseDTO> departmanResponseDTOs = entityDtoMapper.toDepartmanResponseDTOs(departmanlar);
        return new ResponseEntity<>(departmanResponseDTOs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DepartmanResponseDTO> getDepartmanById(Long departmanID) {
        Optional<Departman> departmanOptional = departmanRepository.findById(departmanID);
        return departmanOptional.map(departman -> new ResponseEntity<>(entityDtoMapper.toDepartmanResponseDTO(departman), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<DepartmanResponseDTO> createDepartman(Departman departman) {
        departman = departmanRepository.save(departman);
        DepartmanResponseDTO departmanResponseDTO = entityDtoMapper.toDepartmanResponseDTO(departman);
        return new ResponseEntity<>(departmanResponseDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DepartmanResponseDTO> updateDepartman(Departman departman) {
        departman = departmanRepository.save(departman);
        DepartmanResponseDTO departmanResponseDTO = entityDtoMapper.toDepartmanResponseDTO(departman);
        return new ResponseEntity<>(departmanResponseDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteDepartman(Long departmanID) {
        departmanRepository.deleteById(departmanID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
