package com.hgicreate.rno.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "auth_role_menu")
public class Menu {

    @Id
    private Long id;
    private Long role_id;
    private String menu;
}
