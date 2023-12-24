package com.zgm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zgm.bean.Depart;

@Repository
public interface DepartRepository extends JpaRepository<Depart, Integer> {
    

    
}
