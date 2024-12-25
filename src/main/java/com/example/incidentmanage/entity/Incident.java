package com.example.incidentmanage.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "t_incident")
public class Incident implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;// 事件ID

    @Column(name = "name")
    private String name;// 事件名称

    @Column(name = "description")
    private String description;// 事件描述

    @Column(name = "create_user")
    private String createUser;// 事件创建人

    @Column(name = "create_date")
    private Date createDate;// 事件创建人

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCreateUser() {
        return createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
