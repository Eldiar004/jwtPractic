package com.example.spring_rest_api_session_java7.converter.company;

import com.example.spring_rest_api_session_java7.dto.request.CompanyRequest;
import com.example.spring_rest_api_session_java7.entity.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyConverterRequest {

    public Company create(CompanyRequest companyRequest) {
        if (companyRequest == null) return null;
        Company company = new Company();
        company.setCompanyName(companyRequest.getCompanyName());
        company.setLocatedCountry(companyRequest.getLocatedCountry());
        return company;
    }

    public void update(Company company, CompanyRequest companyRequest) {
        if (companyRequest.getCompanyName() != null) {
            company.setCompanyName(companyRequest.getCompanyName());
        }
        if (companyRequest.getLocatedCountry() != null) {
            company.setLocatedCountry(companyRequest.getLocatedCountry());
        }
    }
}
