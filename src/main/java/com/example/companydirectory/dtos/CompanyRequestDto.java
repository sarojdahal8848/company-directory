package com.example.companydirectory.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyRequestDto {
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Address cannot be blank")
    private String address;
    @NotBlank(message = "Phone cannot be blank")
    private String phone;
    @NotBlank(message = "Email cannot be blank")
    private String email;
    @NotBlank(message = "Career Link cannot be blank")
    private String careerLink;
}
