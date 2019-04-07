package com.marshal.halcyon.workflow.controller;

import com.marshal.halcyon.core.entity.ResponseData;
import com.marshal.halcyon.core.util.ResponseUtil;
import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @auth: Marshal
 * @date: 2019/4/6
 * @desc:
 */
@RestController
@RequestMapping("/workflow")
public class RuntimeController {

    @Autowired
    RuntimeService runtimeService;

    @RequestMapping("/runtime/prc/suspend/{procId}")
    public ResponseData suspendProc(HttpServletRequest request, @PathVariable String procId) {
        runtimeService.suspendProcessInstanceById(procId);
        return ResponseUtil.responseOk("挂起成功!");
    }

    @RequestMapping("/runtime/prc/active/{procId}")
    public ResponseData activeProc(HttpServletRequest request, @PathVariable String procId) {
        runtimeService.activateProcessInstanceById(procId);
        return ResponseUtil.responseOk("激活成功!");
    }

    @GetMapping(value = "/runtime/prc/end/{procId}")
    @ResponseBody
    public ResponseData endProc(HttpServletRequest request, @PathVariable String procId) {
        runtimeService.deleteProcessInstance(procId, "adminStop");
        return ResponseUtil.responseOk("终止成功!");
    }

    @RequestMapping(value = "/runtime/prc/back/{procId}", method = RequestMethod.POST)
    @ResponseBody
    public void backProc(HttpServletRequest request, @PathVariable String procId) {
//        IRequest iRequest = createRequestContext(request);
//        if (activitiService.isStartRecall(procId, iRequest.getEmployeeCode())) {
//            activitiService.startRecall(iRequest, procId, iRequest.getEmployeeCode());
//        } else if (activitiService.isTaskRecall(procId, iRequest.getEmployeeCode())) {
//            activitiService.taskRecall(iRequest, procId, iRequest.getEmployeeCode());
//        }
    }
}
