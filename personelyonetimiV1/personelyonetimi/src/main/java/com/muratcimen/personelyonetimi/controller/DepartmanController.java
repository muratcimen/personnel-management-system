package com.muratcimen.personelyonetimi.controller;


import com.muratcimen.config.RateLimited;
import com.muratcimen.personelyonetimi.dto.requestDTO.DepartmanRequestDTO;
import com.muratcimen.personelyonetimi.dto.responseDTO.DepartmanResponseDTO;
import com.muratcimen.personelyonetimi.entity.Departman;
import com.muratcimen.personelyonetimi.mapper.EntityDtoMapper;
import com.muratcimen.personelyonetimi.service.DepartmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/departman")
public class DepartmanController {

    @Autowired
    private DepartmanService departmanService;

    @Autowired
    private EntityDtoMapper entityDtoMapper;

    @RateLimited
    @GetMapping
    public ResponseEntity<List<DepartmanResponseDTO>> getAllDepartmanlar() {
        return departmanService.getAllDepartmanlar();
    }

    @RateLimited
    @GetMapping("/{departmanID}")
    public ResponseEntity<DepartmanResponseDTO> getDepartmanById(@PathVariable Long departmanID) {
        return departmanService.getDepartmanById(departmanID);
    }

    @PostMapping
    public ResponseEntity<DepartmanResponseDTO> createDepartman(@Valid @RequestBody DepartmanRequestDTO departmanRequestDTO) {
        Departman departman = entityDtoMapper.toDepartman(departmanRequestDTO);
        return departmanService.createDepartman(departman);
    }

    @PutMapping("/{departmanID}")
    public ResponseEntity<DepartmanResponseDTO> updateDepartman(@PathVariable Long departmanID, @Valid @RequestBody DepartmanRequestDTO departmanRequestDTO) {
        Departman departman = entityDtoMapper.toDepartman(departmanRequestDTO);
        departman.setDepartmanID(departmanID);
        return departmanService.updateDepartman(departman);
    }

    @DeleteMapping("/{departmanID}")
    public ResponseEntity<Void> deleteDepartman(@PathVariable Long departmanID) {
        departmanService.deleteDepartman(departmanID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
