package com.muratcimen.personelyonetimi.mapper;

import com.muratcimen.personelyonetimi.dto.requestDTO.CalisanRequestDTO;
import com.muratcimen.personelyonetimi.dto.requestDTO.DepartmanRequestDTO;
import com.muratcimen.personelyonetimi.dto.requestDTO.RaporRequestDTO;
import com.muratcimen.personelyonetimi.dto.responseDTO.CalisanResponseDTO;
import com.muratcimen.personelyonetimi.dto.responseDTO.DepartmanResponseDTO;
import com.muratcimen.personelyonetimi.dto.responseDTO.RaporResponseDTO;
import com.muratcimen.personelyonetimi.entity.*;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EntityDtoMapper {

    // Calisan Entity <=> Calisan DTO Mappings

    Calisan toCalisan(CalisanRequestDTO calisanRequestDTO);

    CalisanResponseDTO toCalisanResponseDTO(Calisan calisan);

    List<CalisanResponseDTO> toCalisanResponseDTOs(List<Calisan> calisanlar);

    // Departman Entity <=> Departman DTO Mappings

    Departman toDepartman(DepartmanRequestDTO departmanRequestDTO);

    DepartmanResponseDTO toDepartmanResponseDTO(Departman departman);

    List<DepartmanResponseDTO> toDepartmanResponseDTOs(List<Departman> departmanlar);

    // Rapor Entity <=> Rapor DTO Mappings

    Rapor toRapor(RaporRequestDTO raporRequestDTO);

    RaporResponseDTO toRaporResponseDTO(Rapor rapor);

    List<RaporResponseDTO> toRaporResponseDTOs(List<Rapor> raporlar);
}
