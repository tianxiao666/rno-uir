package com.hgicreate.rno.xdr.backend.service;

import com.hgicreate.rno.xdr.backend.service.dto.DataStateDTO;
import com.hgicreate.rno.xdr.backend.web.rest.cond.DataStateCond;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class DataStateService {

    private final Logger log = LoggerFactory.getLogger(DataStateService.class);

    private final EntityManager entityManager;

    public DataStateService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public List<DataStateDTO> searchByCond(DataStateCond cond) {
        String flag = "search";
        String sql = cond.buildSql(flag);
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<DataStateDTO> analysisByCond(DataStateCond cond) {
        String countSql = cond.countRecord();
        Query countQuery = entityManager.createNativeQuery(countSql);
        if("0".equals(countQuery.getSingleResult().toString())){
            return Collections.emptyList();
        }else {
            String flag = "analysis";
            String sql = cond.buildSql(flag);
            Query query = entityManager.createNativeQuery(sql);
            return query.getResultList();
        }
    }

}
