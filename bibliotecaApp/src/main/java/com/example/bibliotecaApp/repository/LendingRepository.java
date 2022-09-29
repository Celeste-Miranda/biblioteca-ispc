package com.example.bibliotecaApp.repository;

import com.example.bibliotecaApp.entity.Lending;
import com.example.bibliotecaApp.entity.LendingDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public interface LendingRepository extends JpaRepository<Lending,Long> {

    Page<Lending> findByDateReturnIsNull(Pageable pageable);
    Page<Lending> findByDateReturnIsNotNull(Pageable pageable);
    Page<LendingDTO> getLending (HashMap<String, Object> conditions);

    Boolean existsLendingByUserApp_IdAndBook_IdAndDateReturnIsNull(Long userId, Long bookId);


}
