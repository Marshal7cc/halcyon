package com.marshal.halcyon.workflow.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marshal.halcyon.workflow.service.ActivitiService;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.rest.service.api.RestResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auth: Marshal
 * @date: 2019/2/6
 * @desc: Activiti工作流service
 */
@Service
public class ActivitiServiceImpl implements ActivitiService {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private FormService formService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private RestResponseFactory restResponseFactory;

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public Model deployModel(String modelId) throws Exception {
        Model model = repositoryService.getModel(modelId);

        byte[] modelData = repositoryService.getModelEditorSource(modelId);
        JsonNode jsonNode = objectMapper.readTree(modelData);
        BpmnModel bpmnModel = new BpmnJsonConverter().convertToBpmnModel(jsonNode);

        // byte[] xmlBytes = new BpmnXMLConverter().convertToXML(bpmnModel,
        // "UTF-8");

        Deployment deploy = repositoryService.createDeployment().category(model.getCategory()).name(model.getName())
                .key(model.getKey()).addBpmnModel(model.getKey() + ".bpmn20.xml", bpmnModel).deploy();

        model.setDeploymentId(deploy.getId());
        repositoryService.saveModel(model);
        return model;
    }
}
