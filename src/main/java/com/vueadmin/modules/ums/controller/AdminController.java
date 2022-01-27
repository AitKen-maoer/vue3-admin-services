package com.vueadmin.modules.ums.controller;

import com.vueadmin.common.api.CommonResult;
import com.vueadmin.common.api.ResultCode;
import com.vueadmin.modules.ums.Dto.AdminparamsDto;
import com.vueadmin.modules.ums.model.Admin;
import com.vueadmin.modules.ums.service.AdminService;
import com.vueadmin.utils.JWTUtil;
import com.vueadmin.utils.RedisUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "获取Token")
@ResponseBody
@RequestMapping("/api/admin")
public class AdminController {
    @Resource
    private RedisUtils redisUtils;

    @Resource
    private AdminService adminService;

    @PostMapping("/login")
    public CommonResult login(@RequestBody AdminparamsDto adminparamsDto) {
        if (adminparamsDto.getUsername() == null || adminparamsDto.getPassword() == null) {
            return CommonResult.failed("用户名密码不能为空和密码不能为空");
        } else {
            Admin passWrodIsCorrect = adminService.getUser(adminparamsDto);
            if (passWrodIsCorrect != null) {
                String token = JWTUtil.sign(adminparamsDto.getUsername(), adminparamsDto.getPassword());
                boolean set = redisUtils.set(token, passWrodIsCorrect, 600);
                if (!set) {
                    return CommonResult.failed(ResultCode.FAILED, "redis未启动！");
                }
                Map<String, Object> stringObjectMap = new HashMap<>();
                stringObjectMap.put("token",token);
                return CommonResult.success(stringObjectMap);
            }
            return CommonResult.failed("密码错误！");
        }
    }

    @GetMapping(path = "/getRoleName")
    public CommonResult unauthorized() {
        ArrayList<String> userNameList = adminService.getUserNameList();
        return CommonResult.success(userNameList);
    }

    @GetMapping(path = "/401")
    public CommonResult unauthorized(HttpServletRequest request) {
        String data = (String) request.getAttribute("msg");
        return CommonResult.failed(ResultCode.UNAUTHORIZED,data);
    }
}
