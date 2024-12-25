package com.example.incidentmanage.controller;

import com.example.incidentmanage.dto.IncidentDTO;
import com.example.incidentmanage.entity.Incident;
import com.example.incidentmanage.repository.IncidentRepository;
import com.example.incidentmanage.service.IncidentService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class IncidentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IncidentService incidentService;

    @Autowired
    private IncidentRepository incidentRepository;


    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    // 没有数据
    @Test
    void getIncidentList_NoData() throws Exception {
        RequestBuilder request;
        MvcResult mvcResult = null;
        request = get("/incident/list/all/0/5");
        mvcResult = this.mockMvc.perform(request).andReturn();
        String str = mvcResult.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(str);
        int total = jsonNode.get("totalElements").asInt();
        Assert.assertEquals(0,total);
    }

    @Test
    void saveIncident() throws Exception {
        RequestBuilder request;
        MvcResult mvcResult = null;
        IncidentDTO incidentDTO = new IncidentDTO();
        incidentDTO.setId(-1L);
        incidentDTO.setName("name1");
        incidentDTO.setDescription("description1");
        incidentDTO.setCreateUser("createUser1");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(incidentDTO);
        request = post("/incident/save").contentType(MediaType.APPLICATION_JSON_VALUE).content(json);
        mvcResult = this.mockMvc.perform(request).andReturn();
        String str = mvcResult.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(str);
        boolean success = jsonNode.get("success").asBoolean();
        Assert.assertFalse(success);
    }

    @Test
    void deleteIncident() throws Exception {
        RequestBuilder request;
        MvcResult mvcResult = null;
        request = get("/incident/delete/-1");
        mvcResult = this.mockMvc.perform(request).andReturn();
        String str = mvcResult.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(str);
        boolean success = jsonNode.get("success").asBoolean();
        Assert.assertFalse(success);
    }
}