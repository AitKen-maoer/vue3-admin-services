package com.vueadmin.modules.ums.dao;

import com.vueadmin.modules.ums.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("AdminDao")
public interface AdminDao extends JpaRepository<Admin,Long> {
    Admin findByUsername(String username);

    Optional<Admin> findById(Long id);

    @Query(value = "SELECT username FROM ums_admin",nativeQuery = true)
    ArrayList<String> findAllByUsername();
}
