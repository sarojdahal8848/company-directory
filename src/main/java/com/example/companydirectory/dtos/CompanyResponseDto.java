package com.example.companydirectory.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyResponseDto {
    private Integer id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String careerLink;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
