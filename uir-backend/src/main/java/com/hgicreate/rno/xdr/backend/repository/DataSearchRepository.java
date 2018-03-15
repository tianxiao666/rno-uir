package com.hgicreate.rno.xdr.backend.repository;

import com.hgicreate.rno.xdr.backend.domain.DataSearch;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the DataSearch entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DataSearchRepository extends JpaRepository<DataSearch,Long> {
    
}
