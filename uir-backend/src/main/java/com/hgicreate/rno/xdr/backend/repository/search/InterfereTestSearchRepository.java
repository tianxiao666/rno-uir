package com.hgicreate.rno.xdr.backend.repository.search;

import com.hgicreate.rno.xdr.backend.domain.InterfereTest;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

/**
 * Created by yangch on 2017/9/18.
 */
public interface InterfereTestSearchRepository extends ElasticsearchCrudRepository<InterfereTest,Long> {
}
