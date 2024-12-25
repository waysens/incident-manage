package com.example.incidentmanage.controller;

import com.example.incidentmanage.common.ResultMessage;
import com.example.incidentmanage.dto.IncidentDTO;
import com.example.incidentmanage.entity.Incident;
import com.example.incidentmanage.service.IncidentService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/incident")
public class IncidentController {


    @Autowired
    private IncidentService incidentService;

    /**
     * 获得事件列表分页数据
     * @param pageNumber 开始页
     * @param pageSize 每页大小
     * @return 分页数据列表
     * @throws Exception 异常
     */
    @RequestMapping(value="/list/all/{pageNumber}/{pageSize}",method = RequestMethod.GET)
    public Page<Incident> getIncidentList(@PathVariable() int pageNumber, @PathVariable int pageSize) throws Exception {
        return this.incidentService.getIncidentList(pageNumber,pageSize);
    }

    /**
     * 保存单条事件数据
     * @param incidentDTO 事件DTO
     * @return ResultMessage
     * @throws Exception 异常
     */
    @PostMapping("/save")
    public ResultMessage saveIncident(@RequestBody @Valid IncidentDTO incidentDTO) throws Exception {
        Incident incident = incidentDTO.convertTo();
        this.incidentService.saveIncident(incident);
        return ResultMessage.success(null);
    }

    /**
     * 删除单条事件根据事件ID主键
     * @param id 事件ID
     * @throws Exception 异常
     */
    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    public void deleteIncident(@PathVariable Long id) throws Exception {
        this.incidentService.deleteIncident(id);
    }
}
