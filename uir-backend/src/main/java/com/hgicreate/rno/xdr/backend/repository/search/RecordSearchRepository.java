package com.hgicreate.rno.xdr.backend.repository.search;

import com.hgicreate.rno.xdr.backend.domain.Record;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;


public interface RecordSearchRepository extends ElasticsearchCrudRepository<Record,Long> {
}
