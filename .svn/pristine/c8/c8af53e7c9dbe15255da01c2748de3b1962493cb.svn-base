package com.hgicreate.rno.xdr.backend.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the DataState entity.
 */
public class DataStateDTO implements Serializable {

    private Long id;

    private Integer stateId;

    private String interfereGrade;

    private Integer cellCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getInterfereGrade() {
        return interfereGrade;
    }

    public void setInterfereGrade(String interfereGrade) {
        this.interfereGrade = interfereGrade;
    }

    public Integer getCellCount() {
        return cellCount;
    }

    public void setCellCount(Integer cellCount) {
        this.cellCount = cellCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DataStateDTO dataStateDTO = (DataStateDTO) o;
        if(dataStateDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dataStateDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DataStateDTO{" +
            "id=" + getId() +
            ", stateId='" + getStateId() + "'" +
            ", interfereGrade='" + getInterfereGrade() + "'" +
            ", cellCount='" + getCellCount() + "'" +
            "}";
    }
}
