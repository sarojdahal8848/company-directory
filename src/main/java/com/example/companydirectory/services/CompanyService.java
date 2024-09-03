package com.example.companydirectory.services;

import com.example.companydirectory.dtos.CompanyRequestDto;
import com.example.companydirectory.dtos.CompanyResponseDto;
import com.example.companydirectory.exceptions.CompanyNotFoundException;
import com.example.companydirectory.models.Company;
import com.example.companydirectory.repositories.CompanyRepository;
import com.example.companydirectory.services.interfaces.ICompanyService;
import com.example.companydirectory.utils.ErrorCode;
import com.example.companydirectory.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CompanyService implements ICompanyService {
    private final CompanyRepository companyRepository;
    @Override
    public CompanyResponseDto create(CompanyRequestDto companyRequestDto) {
        Company company = Mapper.toCompany(companyRequestDto);
        company = companyRepository.save(company);
        return Mapper.toCompanyResponseDto(company);
    }

    @Override
    public Page<Company> findAll(Integer page, Integer size, String sortBy, String dir) {
        Sort sort = dir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return companyRepository.findAll(pageable);
    }

    @Override
    public CompanyResponseDto findById(Integer id) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException(ErrorCode.ERROR_COMPANY_NOT_FOUND + id));
        if(company == null){
            return null;
        }
        return Mapper.toCompanyResponseDto(company);
    }

    @Override
    public boolean update(Integer id, CompanyRequestDto companyRequestDto) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException(ErrorCode.ERROR_COMPANY_NOT_FOUND + id));
        if(company == null){
            return false;
        }
        company.setName(companyRequestDto.getName());
        company.setAddress(companyRequestDto.getAddress());
        company.setEmail(companyRequestDto.getEmail());
        company.setPhone(companyRequestDto.getPhone());
        company.setCareerLink(companyRequestDto.getCareerLink());
        companyRepository.save(company);

        return true;
    }

    @Override
    public boolean delete(Integer id) {
        Company phoneBook = companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException(ErrorCode.ERROR_COMPANY_NOT_FOUND + id));
        if(phoneBook == null){
            return false;
        }

        companyRepository.delete(phoneBook);

        return true;
    }
}
