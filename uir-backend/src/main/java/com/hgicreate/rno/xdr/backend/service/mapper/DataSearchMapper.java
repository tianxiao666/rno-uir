package com.hgicreate.rno.xdr.backend.service.mapper;

import com.hgicreate.rno.xdr.backend.domain.*;
import com.hgicreate.rno.xdr.backend.service.dto.DataSearchDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DataSearch and its DTO DataSearchDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DataSearchMapper extends EntityMapper <DataSearchDTO, DataSearch> {
    
    
    default DataSearch fromId(Long id) {
        if (id == null) {
            return null;
        }
        DataSearch dataSearch = new DataSearch();
        dataSearch.setId(id);
        return dataSearch;
    }
}
