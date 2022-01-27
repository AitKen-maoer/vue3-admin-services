package com.vueadmin.modules.ums.service;


import com.vueadmin.modules.ums.Dto.AdminparamsDto;
import com.vueadmin.modules.ums.model.Admin;

import java.util.ArrayList;


public interface AdminService {
    Admin getUser(AdminparamsDto adminparamsDto);

    ArrayList<String> getUserNameList();
}
