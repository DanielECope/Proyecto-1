package com.afp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.afp.Model.AFP;

public interface IAfpRepository extends JpaRepository<AFP, String> {
    
}
