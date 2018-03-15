package com.hgicreate.rno.xdr.backend.repository;

import com.hgicreate.rno.xdr.backend.domain.DataImport;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the DataImport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DataImportRepository extends JpaRepository<DataImport,Long> {
    List<DataImport> findByFileType(String fileType);
}
