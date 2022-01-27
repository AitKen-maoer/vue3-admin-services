package com.vueadmin.modules.ums.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.vueadmin.modules.ums.dao.RouterDao;
import com.vueadmin.modules.ums.model.Router;
import com.vueadmin.modules.ums.service.RouterService;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RouterServiceImpl implements RouterService {

    @Resource
    private RouterDao routerDao;

    @Override
    public Boolean addRouter(Router router) {
        Router save = routerDao.save(router);
        if(save.getId() != null){
            return true;
        }
        return false;

    }
    @Override
    public Boolean EditRouter(Router router) {
        Router byId = routerDao.findById(router.getId());
        if(byId != null){
            Router save = routerDao.save(router);
            return save != null;
        }
        return false;
    }

    @Override
    public List<Router> getRouterRole(String role) {
        if("".equals(role)){
            return null;
        }
        ArrayList<Router> byName = routerDao.findByRole(role);
        List<Router> routers = getChild("0",byName);
        return routers;
    }

    @Override
    public Page<Router> getRouterList(Pageable pageable) {
        return routerDao.findAll(pageable);
    }

    /**
     * 平级数据处理成数（tree）
     * @param id 父节点id
     * @param allList 所有平级数据
     * @return
     */
    private List<Router> getChild(String id, List<Router> allList) {
        // 子级数据
        List<Router> childList = Lists.newArrayList();
        for (Router entity : allList) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (StringUtils.isNotBlank(entity.getParentId())) {
                if (entity.getParentId().equals(id)) {
                    childList.add(entity);
                }
            }
        }
        // 把子级数据的子级再循环一遍
        for (Router entity : childList) {
            if (StringUtils.isNotBlank(entity.getParentId())) {
                // 递归
                entity.setChildren(getChild(entity.getId(), allList));
            }
        }
        // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }
}
