package com.example.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.ApplicationServer;

@Repository
public interface ApplicationServerMapper extends JpaRepository<ApplicationServer, Long>{

}
