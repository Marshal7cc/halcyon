package com.marshal.halcyon.workflow.service.impl;


import com.marshal.halcyon.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import com.marshal.halcyon.workflow.entity.ActCusDeliver;
import com.marshal.halcyon.workflow.service.ActCusDeliverService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ActCusDeliverServiceImpl extends BaseServiceImpl<ActCusDeliver> implements ActCusDeliverService {

}