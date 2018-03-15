package com.hgicreate.rno.xdr.backend.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the DataSearch entity.
 */
public class DataSearchDTO implements Serializable {

    private Long id;

    private Integer seachId;

    private String interfereGrade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSeachId() {
        return seachId;
    }

    public void setSeachId(Integer seachId) {
        this.seachId = seachId;
    }

    public String getInterfereGrade() {
        return interfereGrade;
    }

    public void setInterfereGrade(String interfereGrade) {
        this.interfereGrade = interfereGrade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DataSearchDTO dataSearchDTO = (DataSearchDTO) o;
        if(dataSearchDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dataSearchDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DataSearchDTO{" +
            "id=" + getId() +
            ", seachId='" + getSeachId() + "'" +
            ", interfereGrade='" + getInterfereGrade() + "'" +
            "}";
    }

}
