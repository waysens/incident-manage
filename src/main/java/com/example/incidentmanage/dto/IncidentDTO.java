package com.example.incidentmanage.dto;
import com.example.incidentmanage.entity.Incident;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.Date;

public class IncidentDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Digits(integer = 19, fraction = 0,message = "ID必须是整数！")
    private Long id;// 事件ID

    @NotBlank(message = "事件名称不能为空！")
    @Size(max = 100,message = "事件名称长度不能超过100！")
    private String name;// 事件名称

    @Size(max = 500,message = "事件名称长度不能超过500！")
    private String description;// 事件描述

    @NotBlank(message = "创建人不能为空！")
    @Size(max = 100,message = "创建人长度不能超过100！")
    private String createUser;// 事件创建人

    public Long getId() {
        return id;
    }

    public @NotBlank(message = "事件名称不能为空！") @Size(max = 100, message = "事件名称长度不能超过100！") String getName() {
        return name;
    }

    public @Size(max = 500, message = "事件名称长度不能超过500！") String getDescription() {
        return description;
    }

    public @NotBlank(message = "创建人不能为空！") @Size(max = 100, message = "事件名称长度不能超过100！") String getCreateUser() {
        return createUser;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(@NotBlank(message = "事件名称不能为空！") @Size(max = 100, message = "事件名称长度不能超过100！") String name) {
        this.name = name;
    }

    public void setDescription(@Size(max = 500, message = "事件名称长度不能超过500！") String description) {
        this.description = description;
    }

    public void setCreateUser(@NotBlank(message = "创建人不能为空！") @Size(max = 100, message = "事件名称长度不能超过100！") String createUser) {
        this.createUser = createUser;
    }

    public Incident convertTo(){
        Incident incident = new Incident();
        incident.setId(this.getId());
        incident.setName(this.getName());
        incident.setDescription(this.getDescription());
        incident.setCreateUser(this.getCreateUser());
        incident.setCreateDate(new Date());
        return incident;
    }
}
