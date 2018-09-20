package com.pengzu.service;

import com.pengzu.entity.PengzuConfig;

import java.util.List;

public interface PengzuConfigService {

    /**
     * 新增配置信息
     *
     * @param pengzuConfig
     * @return
     */
    Integer addPengzuConfig(PengzuConfig pengzuConfig);

    /**
     * 查询配置信息
     *
     * @param pengzuConfig
     * @return
     */
    PengzuConfig queryPengzuConfig(PengzuConfig pengzuConfig);

    /**
     * 根据Key删除配置信息
     *
     * @param key
     * @return
     */
    Integer deletePengzuConfig(String key);

    /**
     * 修改配置信息
     *
     * @param pengzuConfig
     * @return
     */
    Integer updatePengzuConfig(PengzuConfig pengzuConfig);

    /**
     * 查询配置信息
     *
     * @param pengzuConfig
     * @return
     */
    List<PengzuConfig> queryPengzuConfigs(PengzuConfig pengzuConfig);

    /**
     * 查询配置信息总数
     *
     * @param pengzuConfig
     * @return
     */
    Long queryPengzuConfigsCount(PengzuConfig pengzuConfig);
}
