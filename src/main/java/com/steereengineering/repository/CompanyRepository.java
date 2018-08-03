package com.steereengineering.repository;

import org.springframework.data.repository.CrudRepository;

import com.steereengineering.model.Company;

public interface CompanyRepository extends CrudRepository<Company, Long> {

}
