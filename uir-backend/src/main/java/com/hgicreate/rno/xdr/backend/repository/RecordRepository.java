package com.hgicreate.rno.xdr.backend.repository;


import com.hgicreate.rno.xdr.backend.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record,Long>{
    List<Record> findByFileTypeAndState(String fileType,String state);
    List<Record> findByFileNameAndStateAndFileType(String fileName,String state,String fileType);
}
