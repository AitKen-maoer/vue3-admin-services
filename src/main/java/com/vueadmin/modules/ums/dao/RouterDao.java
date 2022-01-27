package com.vueadmin.modules.ums.dao;

import com.vueadmin.modules.ums.model.Router;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository("RouterDao")
public interface RouterDao  extends JpaRepository<Router,Long> {

    ArrayList<Router> findByRole(String role);

    ArrayList<Router> findByParentId(String role);

    Page<Router> findAll(Pageable pageable);

    Router findById(String id);


}
