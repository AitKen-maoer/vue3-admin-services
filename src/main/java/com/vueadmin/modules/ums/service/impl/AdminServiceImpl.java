package com.vueadmin.modules.ums.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.vueadmin.modules.ums.Dto.AdminparamsDto;
import com.vueadmin.modules.ums.dao.AdminDao;
import com.vueadmin.modules.ums.model.Admin;
import com.vueadmin.modules.ums.service.AdminService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class AdminServiceImpl implements AdminService {


    @Resource
    private AdminDao adminDao;

    @Override
    public Admin getUser(AdminparamsDto adminparamsDto) {
        Admin adminBy = adminDao.findByUsername(adminparamsDto.getUsername());
        if (adminBy.getPassword().equals(SecureUtil.md5(adminparamsDto.getPassword()))){
            return adminBy;
        }
        return null;
    }

    @Override
    public ArrayList<String> getUserNameList() {
        return adminDao.findAllByUsername();
    }
}
