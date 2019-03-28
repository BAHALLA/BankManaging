package com.dev.dao;

import com.dev.entities.Operations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OperationRepository extends JpaRepository<Operations,Long> {

        @Query("select o from Operations o where o.compte.codeCompte=:x order by o.dateOperation desc ")
    public Page<Operations> listOperations(@Param("x") String codeCompte, Pageable pageable);
}
