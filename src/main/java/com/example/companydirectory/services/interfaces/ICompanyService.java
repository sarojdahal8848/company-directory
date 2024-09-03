package com.example.companydirectory.services.interfaces;

import com.example.companydirectory.dtos.CompanyRequestDto;
import com.example.companydirectory.dtos.CompanyResponseDto;
import com.example.companydirectory.models.Company;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICompanyService {
    CompanyResponseDto create(CompanyRequestDto companyRequestDto);
    Page<Company> findAll(Integer page, Integer size, String sortBy, String dir);
    CompanyResponseDto findById(Integer id);
    boolean update(Integer id, CompanyRequestDto companyRequestDto);
    boolean delete(Integer id);
}
