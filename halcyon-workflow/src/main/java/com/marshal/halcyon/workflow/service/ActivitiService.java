package com.marshal.halcyon.workflow.service;

import org.activiti.engine.repository.Model;

public interface ActivitiService {

    Model deployModel(String modelId) throws Exception;

}
