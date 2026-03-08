package com.paipai.core.service;

import com.paipai.data.dto.ProjectListDto;
import com.paipai.data.entity.PProject;
import com.paipai.data.vo.PProjectVo;

import java.util.List;

public interface PProjectService {

    PProject queryById(Long id);

    List<PProjectVo> queryProjectVoByPageable(ProjectListDto dto);

    int addSub(Long projectId, Long userId);

    int unSub(Long projectId, Long userId);

    boolean exists(Long id);
}
