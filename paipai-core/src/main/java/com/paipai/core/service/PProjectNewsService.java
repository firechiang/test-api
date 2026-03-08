package com.paipai.core.service;

import com.paipai.data.dto.ProjectNewsListDto;
import com.paipai.data.entity.PProjectNews;

import java.util.List;

public interface PProjectNewsService {

    PProjectNews queryById(Long id);

    List<PProjectNews> queryByPageable(ProjectNewsListDto dto);
}
