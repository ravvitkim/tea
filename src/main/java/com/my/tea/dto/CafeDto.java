package com.my.tea.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CafeDto {
    private String name;
    private String address;
    private String phone;
    private String mapX;
    private String mapY;
}
