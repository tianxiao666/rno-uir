package com.hgicreate.rno.repository;

import com.hgicreate.rno.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, String> {

    @Query(value = "select arm.* from auth_role_menu arm, auth_user_role aur, jhi_user ju " +
        "where ju.id = aur.user_id and aur.role_id = arm.role_id and ju.login = ?1", nativeQuery = true)
    List<Menu> findMenuByLogin(String login);
}
