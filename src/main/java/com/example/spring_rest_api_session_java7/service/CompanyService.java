package com.example.spring_rest_api_session_java7.service;


import com.example.spring_rest_api_session_java7.dto.request.CompanyRequest;
import com.example.spring_rest_api_session_java7.dto.response.CompanyResponse;

import java.util.List;

public interface CompanyService {

    List<CompanyResponse> getAllCompany();

    CompanyResponse getCompanyById(Long id);

    CompanyResponse saveCompany(CompanyRequest companyRequest);

    CompanyResponse updateCompany(Long id, CompanyRequest companyRequest);

    CompanyResponse deleteCompanyById(Long id);
}
