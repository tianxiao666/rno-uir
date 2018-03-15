package com.hgicreate.rno.xdr.backend.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DataState.
 */
@Entity
@Table(name = "data_state")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "datastate")
public class DataState implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "state_id")
    private Integer stateId;

    @Column(name = "interfere_grade")
    private String interfereGrade;

    @Column(name = "cell_count")
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

    public DataState stateId(Integer stateId) {
        this.stateId = stateId;
        return this;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getInterfereGrade() {
        return interfereGrade;
    }

    public DataState interfereGrade(String interfereGrade) {
        this.interfereGrade = interfereGrade;
        return this;
    }

    public void setInterfereGrade(String interfereGrade) {
        this.interfereGrade = interfereGrade;
    }

    public Integer getCellCount() {
        return cellCount;
    }

    public DataState cellCount(Integer cellCount) {
        this.cellCount = cellCount;
        return this;
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
        DataState dataState = (DataState) o;
        if (dataState.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dataState.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DataState{" +
            "id=" + getId() +
            ", stateId='" + getStateId() + "'" +
            ", interfereGrade='" + getInterfereGrade() + "'" +
            ", cellCount='" + getCellCount() + "'" +
            "}";
    }
}
