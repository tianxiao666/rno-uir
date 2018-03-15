package com.hgicreate.rno.xdr.backend.service;

import com.hgicreate.rno.xdr.backend.service.dto.DataImportDTO;
import com.hgicreate.rno.xdr.backend.service.dto.DataSearchDTO;
import com.hgicreate.rno.xdr.backend.web.rest.cond.DataImportCond;
import com.hgicreate.rno.xdr.backend.web.rest.cond.DataSearchCond;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class DataSearchService {

    private final Logger log = LoggerFactory.getLogger(DataSearchService.class);

    private final EntityManager entityManager;

    public DataSearchService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Transactional(readOnly = true)
    public Page<DataSearchDTO> findByCond(DataSearchCond cond, Pageable pageable) {
        String sql = cond.buildSql();
        String countSql = "select count(*) from (" + sql + ") count_sql";
        Query query = entityManager.createNativeQuery(sql);
        Query countQuery = entityManager.createNativeQuery(countSql);
        if (query == null || countQuery == null) {
            return new PageImpl<>(Collections.emptyList(), pageable, 0);
        }
        Long total = ((BigInteger)countQuery.getSingleResult()).longValue();
        query.setFirstResult(pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        List<DataSearchDTO> content = total > pageable.getOffset() ? query.getResultList() : Collections.emptyList();
        return new PageImpl<>(content, pageable, total);
    }

    @Transactional(readOnly = true)
    public List<DataSearchDTO> downloadByCond(DataSearchCond cond) {
        String sql = cond.buildSql();
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList();
    }

}
