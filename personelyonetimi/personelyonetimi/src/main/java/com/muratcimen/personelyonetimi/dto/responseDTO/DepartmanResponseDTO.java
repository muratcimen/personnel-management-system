package com.muratcimen.personelyonetimi.dto.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmanResponseDTO {

    private Long departmanID;
    private String adi;
    private String tanimi;
}