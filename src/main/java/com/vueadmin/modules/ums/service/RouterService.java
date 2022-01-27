package com.vueadmin.modules.ums.service;

import com.vueadmin.modules.ums.model.Router;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;


public interface RouterService {

    Boolean addRouter(Router router);

    List<Router> getRouterRole(String role);

//    ArrayList<Router> getRouterName(String name);

    Page<Router> getRouterList(Pageable pageable);

    Boolean EditRouter(Router router);
}
