package com.marshal.halcyon.es.service.impl;

import com.marshal.halcyon.es.component.BaseElasticsearchServiceImpl;
import com.marshal.halcyon.es.dto.SysRequest;
import com.marshal.halcyon.es.repository.SysRequestRepository;
import com.marshal.halcyon.es.service.SysRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysRequestServiceImpl extends BaseElasticsearchServiceImpl<SysRequest> implements SysRequestService {

    @Autowired
    SysRequestRepository sysRequestRepository;

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public void save(List<SysRequest> list) {
        sysRequestRepository.saveAll(list);
    }

    @Override
    public void delete(Long id) {
        sysRequestRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        sysRequestRepository.deleteAll();
    }

    @Override
    public List<SysRequest> getAll() {
        List<SysRequest> list = new ArrayList<>();
        sysRequestRepository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public SysRequest getById(Long id) {
        return sysRequestRepository.findById(id).get();
    }
}
