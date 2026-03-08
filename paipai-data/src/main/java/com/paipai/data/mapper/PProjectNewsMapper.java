package com.paipai.data.mapper;

import com.paipai.data.entity.PProjectNews;
import com.paipai.data.dto.ProjectNewsListDto;

import java.util.List;

public interface PProjectNewsMapper {

    int insert(PProjectNews row);

    PProjectNews selectByPrimaryKey(Long id);

    List<PProjectNews> selectByPageable(ProjectNewsListDto dto);

    int updateByPrimaryKeySelective(PProjectNews row);

    int updateByPrimaryKey(PProjectNews row);
}