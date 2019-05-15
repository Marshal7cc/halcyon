package com.marshal.halcyon.es.repository;

import com.marshal.halcyon.es.dto.SysRequest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SysRequestRepository extends ElasticsearchRepository<SysRequest, Long> {
}
