package com.muratcimen.personelyonetimi.service;

import com.muratcimen.personelyonetimi.dto.responseDTO.CalisanResponseDTO;
import com.muratcimen.personelyonetimi.entity.Calisan;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface CalisanService {

    ResponseEntity<CalisanResponseDTO> saveCalisan(Calisan calisan);

    List<Calisan> getAllCalisan();

    ResponseEntity<CalisanResponseDTO> getCalisanById(Long id);

    ResponseEntity<Void> deleteCalisan(Long id);

    public ResponseEntity<Void> deleteCalisan(Calisan calisan);
}
