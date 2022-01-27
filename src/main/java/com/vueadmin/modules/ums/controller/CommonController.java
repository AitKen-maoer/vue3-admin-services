package com.vueadmin.modules.ums.controller;

import com.vueadmin.common.api.CommonResult;
import com.vueadmin.common.api.ResultCode;
import com.vueadmin.modules.ums.Dto.AdminparamsDto;
import com.vueadmin.modules.ums.model.Admin;
import com.vueadmin.utils.JWTUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Api(tags = "公共api")
@RequestMapping("/api")
public class CommonController {

    @GetMapping("/getEchartsInfo")
    public CommonResult login() {
        ArrayList<ArrayList<Object>> result = new ArrayList<>();
        Random r = new Random(1);
        for (int index = 0; index < 200; index++) {
            ArrayList<Object> resultList = new ArrayList<>();
            int e = r.nextInt(200);
            resultList.add("@Data");
            resultList.add(e);
            result.add(resultList);
        }
        return CommonResult.success(result);
    }

}
