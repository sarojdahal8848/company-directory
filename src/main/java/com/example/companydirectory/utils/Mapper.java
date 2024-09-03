package com.example.companydirectory.utils;

import com.example.companydirectory.dtos.CompanyRequestDto;
import com.example.companydirectory.dtos.CompanyResponseDto;
import com.example.companydirectory.models.Company;

public class Mapper {
    public static CompanyResponseDto toCompanyResponseDto(Company company) {
        return CompanyResponseDto.builder()
                .id(company.getId())
                .name(company.getName())
                .email(company.getEmail())
                .address(company.getAddress())
                .phone(company.getPhone())
                .careerLink(company.getCareerLink())
                .createdAt(company.getCreatedAt())
                .updatedAt(company.getUpdatedAt())
                .build();
    }

    public static Company toCompany(CompanyRequestDto companyRequestDto) {
        return Company.builder()
                .name(companyRequestDto.getName())
                .email(companyRequestDto.getEmail())
                .address(companyRequestDto.getAddress())
                .phone(companyRequestDto.getPhone())
                .careerLink(companyRequestDto.getCareerLink())
                .build();
    }
}
