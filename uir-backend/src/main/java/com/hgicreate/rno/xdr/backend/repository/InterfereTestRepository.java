package com.hgicreate.rno.xdr.backend.repository;

import com.hgicreate.rno.xdr.backend.domain.InterfereTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface InterfereTestRepository extends JpaRepository<InterfereTest,Long> {
    List<InterfereTest> findByFileId(String fileId);
    InterfereTest findById(Long id);

    @Query(value = "select count(classification) from InterfereTest where interfereGrade = classification and fileId = ?1")
    long countClassifyCationByFileId(String fileId);

    @Query(value = "select count(id) from InterfereTest  where fileId = ?1")
    long countTotalByFileId(String fileId);


}
