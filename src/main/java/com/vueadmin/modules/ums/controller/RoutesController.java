package com.vueadmin.modules.ums.controller;

import com.vueadmin.common.api.CommonResult;
import com.vueadmin.modules.ums.model.Router;
import com.vueadmin.modules.ums.service.RouterService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@Api(tags = "获取前台路由")
@ResponseBody
@RequestMapping("/api/asyncRoutes")
public class RoutesController {

    @Resource
    private RouterService routerService;

//    @GetMapping(value = "/getlist")
//    public CommonResult getlist(@RequestParam("name") String name) {
//        ArrayList<Router> router = routerService.getRouterName(name);
//        if(router == null){
//            return CommonResult.failed("查询失败！");
//        }
//        return CommonResult.success(router);
//    }

    /**
     * 获取列表
     * @param role
     * @return
     */
    @GetMapping(value = "/getAsyncRoutes")
    public CommonResult getAsyncRoutes(@RequestParam("role") String role) {
        List<Router> router = routerService.getRouterRole(role);
        if(router == null){
            return CommonResult.failed("查询失败！");
        }
        return CommonResult.success(router);
    }

    /**
     * 新增路由
     * @param router
     * @return
     */
    @PostMapping(value = "/AddRoutes")
    public CommonResult AddRoutes(@RequestBody Router router) {
        Boolean i = routerService.addRouter(router);
        if(i){
            return CommonResult.success(true);
        }
        return CommonResult.failed("新增失败！");
    }

    /**
     * 编辑路由
     * @param router
     * @return
     */
    @PostMapping(value = "/editRoutes")
    public CommonResult editRoutes(@RequestBody Router router) {
        Boolean i = routerService.EditRouter(router);
        if(i){
            return CommonResult.success(true);
        }
        return CommonResult.failed("编辑失败！");
    }
}
