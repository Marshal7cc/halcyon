package com.marshal.halcyon.workflow.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.activiti.rest.service.api.RestResponseFactory;
import org.activiti.rest.service.api.repository.ModelRequest;
import org.activiti.rest.service.api.repository.ModelResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * author: Marshal
 * Date: 2018/10/29
 * Time: 23:42
 * Description:工作流controller
 */
@Controller
@RequestMapping("/activiti")
public class ActivitiController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    RestResponseFactory restResponseFactory;


    /**
     * 新建模型(editor)
     *
     * @param modelRequest
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/repository/models", method = RequestMethod.POST, produces = "application/json")
    public ModelResponse createModel(@RequestBody ModelRequest modelRequest, HttpServletRequest request,
                                     HttpServletResponse response) {
        Model model = repositoryService.newModel();
        model.setCategory(modelRequest.getCategory());
        model.setDeploymentId(modelRequest.getDeploymentId());
        model.setKey(modelRequest.getKey());
        model.setMetaInfo(modelRequest.getMetaInfo());
        model.setName(modelRequest.getName());
        model.setVersion(modelRequest.getVersion());
        model.setTenantId(modelRequest.getTenantId());

        repositoryService.saveModel(model);
        response.setStatus(HttpStatus.CREATED.value());

        HashMap<String, Object> content = new HashMap<>();
        content.put("resourceId", model.getId());

        HashMap<String, String> properties = new HashMap<>();
        properties.put("process_id", modelRequest.getKey());
        properties.put("name", modelRequest.getName());
        properties.put("process_namespace", modelRequest.getCategory());
        content.put("properties", properties);

        HashMap<String, String> stencilset = new HashMap<>();
        stencilset.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
        content.put("stencilset", stencilset);

        try {
            repositoryService.addModelEditorSource(model.getId(), objectMapper.writeValueAsBytes(content));
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
        }

        return restResponseFactory.createModelResponse(model);

    }
}
