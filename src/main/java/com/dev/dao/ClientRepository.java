package com.dev.dao;

import com.dev.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client,Long> {
    @Query("select c from Client c where c.nom like :x")
    public Page<Client> listClient(@Param("x") String nomClient, Pageable pageable);
}
