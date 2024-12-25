package com.example.incidentmanage.service;

import com.example.incidentmanage.entity.Incident;
import com.example.incidentmanage.repository.IncidentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IncidentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IncidentService.class);

    @Autowired
    private IncidentRepository incidentRepository;

    /**
     * 获得分页数据
     * @param pageNumber 开始页
     * @param pageSize 每页大小
     * @return 分页数据
     * @throws Exception 异常
     */
    public Page<Incident> getIncidentList(int pageNumber,int pageSize) throws Exception{
        Page<Incident> result = null;
        try{
            PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
            result = incidentRepository.findAll(pageRequest);
        }
        catch (Exception ex){
            LOGGER.error("getIncidentList error:", ex);
            throw ex;
        }
        return result;
    }

    /**
     * 保存单挑事件
     * @param incident 事件
     * @throws Exception 异常
     */
    public void saveIncident(Incident incident) throws Exception{
        try{
            if(incident==null){
                throw new Exception("事件不能为空！");
            }
            if(incident.getId()!=null){
                boolean exists = incidentRepository.existsById(incident.getId());
                if(!exists){
                    throw new Exception("事件不存在！");
                }
            }
            this.incidentRepository.save(incident);
        }
        catch (Exception ex){
            LOGGER.error("saveIncident error:", ex);
            throw ex;
        }
    }

    /**
     * 删除单条事件，根据事件ID主键
     * @param id 事件ID主键
     * @throws Exception 异常
     */
    public void deleteIncident(Long id) throws Exception {
        try{
            Optional<Incident> incident = this.incidentRepository.findById(id);
            if(incident.isPresent()){
                incidentRepository.delete(incident.get());
            }
            else{
                throw new Exception("事件不存在！");
            }
        }
        catch (Exception ex){
            LOGGER.error("deleteIncident error:", ex);
            throw ex;
        }
    }
}
