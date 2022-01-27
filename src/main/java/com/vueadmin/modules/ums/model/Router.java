package com.vueadmin.modules.ums.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Map;


/**
 * 前台路由表
 */

@ApiModel(value = "Router对象", description = "前台路由表")
@Entity
@Data
@Table(name = "ums_router")
public class Router {

    @Id
    @GeneratedValue(generator="sys_uid")
    @GenericGenerator(name="sys_uid", strategy="uuid")
    private String id;

    @ApiModelProperty(value = "json字符串")
    private String jsonStr;

    @ApiModelProperty(value = "父级id")
    private String parentId;

    @ApiModelProperty(value = "模块名称")
    private String modelName;

    @ApiModelProperty(value = "路由name==>key")
    private String routerName;

    @ApiModelProperty(value = "权限")
    private String role;

    @Transient
    private List<Router> children;
}
