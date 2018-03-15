package com.hgicreate.rno.xdr.backend.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DataSearch.
 */
@Entity
@Table(name = "data_search")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "datasearch")
public class DataSearch implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "seach_id")
    private Integer seachId;

    @Column(name = "interfere_grade")
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

    public DataSearch seachId(Integer seachId) {
        this.seachId = seachId;
        return this;
    }

    public void setSeachId(Integer seachId) {
        this.seachId = seachId;
    }

    public String getInterfereGrade() {
        return interfereGrade;
    }

    public DataSearch interfereGrade(String interfereGrade) {
        this.interfereGrade = interfereGrade;
        return this;
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
        DataSearch dataSearch = (DataSearch) o;
        if (dataSearch.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dataSearch.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DataSearch{" +
            "id=" + getId() +
            ", seachId='" + getSeachId() + "'" +
            ", interfereGrade='" + getInterfereGrade() + "'" +
            "}";
    }
}
