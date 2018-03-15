package com.hgicreate.rno.xdr.backend.service.mapper;

import com.hgicreate.rno.xdr.backend.domain.*;
import com.hgicreate.rno.xdr.backend.service.dto.DataStateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DataState and its DTO DataStateDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DataStateMapper extends EntityMapper <DataStateDTO, DataState> {
    
    
    default DataState fromId(Long id) {
        if (id == null) {
            return null;
        }
        DataState dataState = new DataState();
        dataState.setId(id);
        return dataState;
    }
}
