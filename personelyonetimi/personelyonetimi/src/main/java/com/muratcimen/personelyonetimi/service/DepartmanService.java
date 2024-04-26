package com.muratcimen.personelyonetimi.service;

import com.muratcimen.personelyonetimi.dto.responseDTO.DepartmanResponseDTO;
import com.muratcimen.personelyonetimi.entity.Departman;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DepartmanService {

    ResponseEntity<List<DepartmanResponseDTO>> getAllDepartmanlar();

    ResponseEntity<DepartmanResponseDTO> getDepartmanById(Long departmanID);

    ResponseEntity<DepartmanResponseDTO> createDepartman(Departman departman);

    ResponseEntity<DepartmanResponseDTO> updateDepartman(Departman departman);

    ResponseEntity<Void> deleteDepartman(Long departmanID);
}
