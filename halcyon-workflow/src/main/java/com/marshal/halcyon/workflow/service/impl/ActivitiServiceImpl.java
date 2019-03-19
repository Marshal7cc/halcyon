package com.marshal.halcyon.workflow.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marshal.halcyon.base.hr.mapper.HrEmployeeMapper;
import com.marshal.halcyon.workflow.entity.HistoricTaskInstanceResponseExt;
import com.marshal.halcyon.workflow.entity.TaskResponseExt;
import com.marshal.halcyon.workflow.service.ActivitiService;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.persistence.entity.CommentEntityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.rest.service.api.RestResponseFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

    @Autowired
    HrEmployeeMapper employeeMapper;


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


    @Override
    public List<TaskResponseExt> getTaskList(List<TaskResponseExt> taskList) {
        taskList.forEach(item -> {
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(item.getProcessInstanceId()).singleResult();
            //流程名称
            if (StringUtils.isNotEmpty(processInstance.getProcessDefinitionName())) {
                item.setProcessName(processInstance.getProcessDefinitionName());
            }
            //流程发起人
            if (StringUtils.isNotEmpty(processInstance.getStartUserId())) {
                item.setStartUserId(processInstance.getStartUserId());
                item.setStartUserName(employeeMapper.getEmployeeNameByCode(processInstance.getStartUserId()));
            }
            //当前审批人
            if (StringUtils.isNotEmpty(item.getAssignee())) {
                item.setAssignee(employeeMapper.getEmployeeNameByCode(item.getAssignee()));
            }
        });
        return taskList;
    }

    @Override
    public TaskResponseExt getTaskDetail(TaskResponseExt task) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
        //流程实例
        task.setProcessInstance(restResponseFactory.createProcessInstanceResponse(processInstance));
        //流程名称
        if (StringUtils.isNotEmpty(processInstance.getProcessDefinitionName())) {
            task.setProcessName(processInstance.getProcessDefinitionName());
        }
        //流程发起人
        if (StringUtils.isNotEmpty(processInstance.getStartUserId())) {
            task.setStartUserId(processInstance.getStartUserId());
            task.setStartUserName(employeeMapper.getEmployeeNameByCode(processInstance.getStartUserId()));
        }
        //审批历史
        List<HistoricActivityInstance> historicActivityInstanceList = historyService
                .createHistoricActivityInstanceQuery().processInstanceId(task.getProcessInstanceId()).list();
        List<HistoricTaskInstanceResponseExt> list = new ArrayList<>();
        for (HistoricActivityInstance historicActivityInstance : historicActivityInstanceList) {
            setHistoricActivityInstanceResponseExt(historicActivityInstance, list, task.getProcessInstanceId());
        }
        list.sort(Comparator.comparing(HistoricTaskInstanceResponseExt::getEndTime));
        task.getHistoricTaskList().addAll(list);

        return task;
    }


    private void setHistoricActivityInstanceResponseExt(HistoricActivityInstance historicActivityInstance,
                                                        List<HistoricTaskInstanceResponseExt> list, String processInstanceId) {
        String activityType = historicActivityInstance.getActivityType();
        if ("userTask".equals(activityType)) {
            List<Comment> comments = getCommentOfType(historicActivityInstance.getTaskId(), "comment");
            List<Comment> actions = getCommentOfType(historicActivityInstance.getTaskId(), "action");
            if (comments != null && comments.size() != 0) {
                for (int index = comments.size() - 1; index >= 0; index--) {
                    HistoricTaskInstanceResponseExt historicTaskInstanceResponseExt = new HistoricTaskInstanceResponseExt(
                            historicActivityInstance);
                    CommentEntityImpl commentEntity = (CommentEntityImpl) comments.get(index);
                    historicTaskInstanceResponseExt.setComment(commentEntity.getMessage());
                    historicTaskInstanceResponseExt.setAction(actions.get(index).getFullMessage());
                    historicTaskInstanceResponseExt.setAssignee(actions.get(index).getUserId());
                    StringBuilder sb = new StringBuilder();
                    String temp = employeeMapper.getEmployeeNameByCode(actions.get(index).getUserId());
                    sb.append(StringUtils.isEmpty(temp) ? "" : temp);
                    sb.append(StringUtils.isEmpty(actions.get(index).getUserId()) ? "" : "(" + actions.get(index).getUserId() + ")");
                    historicTaskInstanceResponseExt.setAssigneeName(sb.toString());
                    historicTaskInstanceResponseExt.setEndTime(actions.get(index).getTime());
                    list.add(historicTaskInstanceResponseExt);
                }
            }
            return;
        }
        HistoricTaskInstanceResponseExt historicTaskInstanceResponseExt = new HistoricTaskInstanceResponseExt(
                historicActivityInstance);
        if ("startEvent".equalsIgnoreCase(activityType)
                && StringUtils.isEmpty(historicActivityInstance.getActivityName())) {
            String startUser = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId)
                    .list().get(0).getStartUserId();
            historicTaskInstanceResponseExt.setAssignee(startUser);
            historicTaskInstanceResponseExt.setAssigneeName(employeeMapper.getEmployeeNameByCode(startUser) + "(" + startUser + ")");
            String start = "开始";
            historicTaskInstanceResponseExt.setName(start);
        }
        if ("endEvent".equalsIgnoreCase(activityType)
                && StringUtils.isEmpty(historicActivityInstance.getActivityName())) {
            String end = "结束";
            historicTaskInstanceResponseExt.setName(end);
        }
        if (!"exclusiveGateway".equals(activityType) && !"parallelGateway".equals(activityType)
                && !"eventBasedGateway".equals(activityType) && !"inclusiveGateway".equals(activityType)
                && null == historicActivityInstance.getDeleteReason()) {
            list.add(historicTaskInstanceResponseExt);
        }
    }

    protected List<Comment> getCommentOfType(String taskId, String type) {
        List<Comment> list = taskService.getTaskComments(taskId, type);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }
}
