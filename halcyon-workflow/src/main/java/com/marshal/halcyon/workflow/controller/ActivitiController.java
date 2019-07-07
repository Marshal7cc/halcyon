package com.marshal.halcyon.workflow.controller;

import com.marshal.halcyon.base.hr.service.HrEmployeeService;
import com.marshal.halcyon.core.component.SessionContext;
import com.marshal.halcyon.core.controller.BaseController;
import com.marshal.halcyon.core.entity.ResponseData;
import com.marshal.halcyon.core.util.RequestHelper;
import com.marshal.halcyon.core.util.ResponseUtil;
import com.marshal.halcyon.workflow.entity.HistoricProcessInstanceResponseExt;
import com.marshal.halcyon.workflow.exception.TaskHandleException;
import com.marshal.halcyon.workflow.entity.TaskActionRequestExt;
import com.marshal.halcyon.workflow.entity.TaskResponseExt;
import com.marshal.halcyon.workflow.service.ActivitiService;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @auth: Marshal
 * @date: 2018/10/29
 * @desc: activiti工作流controller
 */
@RestController
@RequestMapping("/workflow")
public class ActivitiController extends BaseController {

    @Autowired
    protected ProcessEngineConfiguration processEngineConfiguration;

    @Autowired
    TaskService taskService;

    @Autowired
    HistoryService historyService;

    @Autowired
    ActivitiService activitiService;

    @Autowired
    HrEmployeeService employeeService;

    /**
     * 任务处理
     *
     * @param request
     * @param taskId
     * @param taskActionRequest
     * @return
     * @throws Exception
     */
    @RequestMapping("/task/handle/{taskId}")
    @ResponseBody
    public ResponseData taskHandle(HttpServletRequest request,
                                   @PathVariable String taskId,
                                   @RequestBody TaskActionRequestExt taskActionRequest) throws Exception {
        SessionContext sessionContext = RequestHelper.getSessionContext(request);
        activitiService.handleTask(sessionContext, taskId, taskActionRequest, false);
        return ResponseUtil.responseOk();
    }

    /**
     * 任务处理(管理员)
     *
     * @param request
     * @param taskId
     * @param taskActionRequest
     * @return
     * @throws Exception
     */
    @RequestMapping("/task/handle/admin/{taskId}")
    @ResponseBody
    public ResponseData taskHandleAdmin(HttpServletRequest request,
                                        @PathVariable String taskId,
                                        @RequestBody TaskActionRequestExt taskActionRequest) throws TaskHandleException {
        SessionContext sessionContext = RequestHelper.getSessionContext(request);
        activitiService.handleTask(sessionContext, taskId, taskActionRequest, true);
        return ResponseUtil.responseOk();
    }

    /**
     * 我的待办列表
     *
     * @return
     */
    @RequestMapping("/tasks/query")
    public ResponseData queryTasks(HttpServletRequest request,
                                   @RequestParam int start,
                                   @RequestParam int size) {
        SessionContext sessionContext = RequestHelper.getSessionContext(request);
        String employeeCode = sessionContext.getEmployeeCode();

        int total = taskService.createTaskQuery().taskAssignee(employeeCode).list().size();

        List<Task> tasks = taskService.createTaskQuery().taskAssignee(employeeCode).listPage(start, size);

        List<TaskResponseExt> taskList = tasks.stream().map(task -> new TaskResponseExt(task)).collect(Collectors.toList());

        return new ResponseData(activitiService.getTaskList(taskList), total);
    }

    /**
     * 我的待办，任务详情
     *
     * @param request
     * @param taskId
     * @return
     */
    @RequestMapping("/tasks/{taskId}/details")
    @ResponseBody
    public TaskResponseExt taskDetails(HttpServletRequest request,
                                       @PathVariable String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        TaskResponseExt taskExt = new TaskResponseExt(task);

        return activitiService.getTaskDetail(taskExt);
    }

    /**
     * 流程监控查询
     *
     * @param request
     * @param start
     * @param size
     * @param requestParam
     * @return
     */
    @RequestMapping(value = "/process-instances/monitor/query")
    public ResponseData queryAllProcessInstances(HttpServletRequest request,
                                                 @RequestParam int start,
                                                 @RequestParam int size,
                                                 @RequestBody Map<String, String> requestParam) {
        HistoricProcessInstanceQuery historicProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery();

        SessionContext sessionContext = RequestHelper.getSessionContext(request);
        if ("Y".equals(requestParam.get("involved"))) {
            historicProcessInstanceQuery.involvedUser(sessionContext.getEmployeeCode());
        }
        if ("Y".equals(requestParam.get("started"))) {
            historicProcessInstanceQuery.startedBy(sessionContext.getEmployeeCode());
        }

        Integer total = historicProcessInstanceQuery.list().size();

        List<HistoricProcessInstance> processInstances = historicProcessInstanceQuery.listPage(start, size);

        List<HistoricProcessInstanceResponseExt> historicProcessInstances = processInstances.stream().map(processInstance -> new HistoricProcessInstanceResponseExt(processInstance)).collect(Collectors.toList());

        return new ResponseData(activitiService.getHistoricProcessInstanceList(historicProcessInstances), total);
    }

    /**
     * 流程监控-流程详情
     *
     * @param request
     * @param processInstanceId
     * @return
     */
    @GetMapping("/processInstance/{processInstanceId}")
    @ResponseBody
    public HistoricProcessInstanceResponseExt instanceDetail(HttpServletRequest request,
                                                             @PathVariable String processInstanceId) {
        SessionContext sessionContext = RequestHelper.getSessionContext(request);
        HistoricProcessInstanceResponseExt processInstanceDetail = activitiService.getProcessInstanceDetail(sessionContext, processInstanceId);
        return processInstanceDetail;
    }

}
