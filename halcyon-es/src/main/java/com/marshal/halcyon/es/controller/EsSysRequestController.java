package com.marshal.halcyon.es.controller;

import com.marshal.halcyon.es.dto.SysRequest;
import com.marshal.halcyon.es.repository.SysRequestRepository;
import com.marshal.halcyon.es.service.SysRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/es/sys/request")
public class EsSysRequestController {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    SysRequestService sysRequestService;

    @RequestMapping("/createIndex")
    public void createIndex() {
        sysRequestService.createIndex(SysRequest.class);
    }

    @RequestMapping("/putMappings")
    public void putMappings() {
        sysRequestService.putMapping(SysRequest.class);
    }

    @RequestMapping("/save")
    public void save() {
        List<SysRequest> requestList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            SysRequest item = new SysRequest();
            item.setBrowser("谷歌" + i);
            item.setRemoteAddr("192.168.1." + i);
            item.setRequestId(((Integer) i).longValue());
            item.setRequestTime(new Date());
            requestList.add(item);
        }
        sysRequestService.save(requestList);
    }

    @RequestMapping("/query")
    public List<SysRequest> query() {
        return sysRequestService.query("谷歌", SysRequest.class);
    }

    @RequestMapping("/queryHit")
    public Map queryHit() {
        return sysRequestService.queryHighlight("谷歌", "halcyon-sys", "browser");
    }
}
