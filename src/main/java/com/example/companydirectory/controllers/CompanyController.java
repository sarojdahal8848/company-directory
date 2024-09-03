package com.example.companydirectory.controllers;


import com.example.companydirectory.dtos.CompanyRequestDto;
import com.example.companydirectory.dtos.CompanyResponseDto;
import com.example.companydirectory.models.Company;
import com.example.companydirectory.services.interfaces.ICompanyService;
import com.example.companydirectory.utils.AppConstant;
import com.example.companydirectory.utils.Helper;
import com.example.companydirectory.utils.Mapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final ICompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyResponseDto> createPhoneBook(@Valid @RequestBody CompanyRequestDto requestDto) {
        CompanyResponseDto createdPhoneBook = companyService.create(requestDto);
        return new ResponseEntity<>(createdPhoneBook, HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<CompanyResponseDto>> get(
            @RequestParam(defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer page,
            @RequestParam(defaultValue = AppConstant.PAGE_SIZE, required = false) Integer size,
            @RequestParam(defaultValue = AppConstant.SORT_BY, required = false) String sortBy,
            @RequestParam(defaultValue = AppConstant.SORT_DIR, required = false) String dir,
            HttpServletRequest request
    ) {
        Page<Company> companyPages = companyService.findAll(page, size, sortBy, dir);
        List<Company> company = companyPages.getContent();
        List<CompanyResponseDto> companyResponse =  company
                .stream()
                .map(Mapper::toCompanyResponseDto)
                .toList();

        String fullUrl = Helper.getFullUrl(request);
        int totalPages = companyPages.getTotalPages();
        String linkHeaders = Helper.createLinkHeader(fullUrl, page, size, sortBy, dir, totalPages);

        HttpHeaders headers = new HttpHeaders();

        headers.add(HttpHeaders.LINK, linkHeaders);

        return new ResponseEntity<>(companyResponse, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CompanyResponseDto> get(@PathVariable Integer id) {
        CompanyResponseDto phoneBookResponse = companyService.findById(id);
        if(phoneBookResponse == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(phoneBookResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> update(
            @RequestBody CompanyRequestDto requestDto,
            @PathVariable Integer id
    ) {
        boolean updatePhoneBook = companyService.update(id, requestDto);
        if(!updatePhoneBook) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deletePhoneBook = companyService.delete(id);
        if(!deletePhoneBook) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
