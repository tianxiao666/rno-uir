package com.hgicreate.rno.xdr.backend.repository;

import com.hgicreate.rno.xdr.backend.domain.InterfereTrain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface InterfereTrainRepository extends JpaRepository<InterfereTrain,Long> {
    List<InterfereTrain> findByFileId(String fileId);
}
