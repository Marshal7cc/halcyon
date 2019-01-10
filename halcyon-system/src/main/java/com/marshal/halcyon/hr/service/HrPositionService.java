package com.marshal.halcyon.hr.service;


import com.marshal.halcyon.hr.entity.HrPosition;

import java.util.List;

public interface HrPositionService {

    List<HrPosition> query(HrPosition condition, int pageNum, int pageSize);

    public void submit(HrPosition hrPosition);

    public void remove(Long[] idList);

    public HrPosition selectByPositionId(Long id);

}
