package com.pengzu.service;

import com.pengzu.entity.PengzuRegion;

import java.util.List;

public interface PengzuRegionService {

    List<PengzuRegion> queryRegionByParentCode(String parentCode);

    PengzuRegion queryRegionByCode(String code);
}
