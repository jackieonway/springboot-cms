package com.pengzu.service;

import com.pengzu.entity.Headline;
import com.pengzu.entity.vo.HeadlineVo;

import java.util.List;

/**
 * Created by Administrator on 2017/12/3.
 */
public interface HeadlineService {

    Headline addHeadline(HeadlineVo headlineVo);

    Headline updateHeadline(HeadlineVo headlineVo);

    Headline queryHeadline(HeadlineVo headlineVo);

    List<Headline> queryHeadlines(HeadlineVo headlineVo);

    Long queryHeadlineCount(HeadlineVo headlineVo);

    Integer deleteHeadline(HeadlineVo headlineVo);

    Integer updateHeadlinePublishState(Headline headline);

}
