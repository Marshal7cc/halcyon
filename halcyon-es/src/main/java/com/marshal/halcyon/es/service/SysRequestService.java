package com.marshal.halcyon.es.service;

import com.marshal.halcyon.es.component.BaseElasticsearchService;
import com.marshal.halcyon.es.dto.SysRequest;

import java.util.List;

public interface SysRequestService extends BaseElasticsearchService<SysRequest> {

    void save(List<SysRequest> list);

    void delete(Long id);

    void deleteAll();

    List<SysRequest> getAll();

    SysRequest getById(Long id);
}
