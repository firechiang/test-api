package com.paipai.data.mapper;

import com.paipai.data.dto.ProjectListDto;
import com.paipai.data.entity.PProject;
import com.paipai.data.vo.PProjectVo;

import java.util.List;

public interface PProjectMapper {

    PProject selectByPrimaryKey(Long id);

    List<PProjectVo> selectProjectVoByPageable(ProjectListDto dto);

    Long selectId(Long id);

    int updateAddSubCount(Long id);

    int updateUnSubCount(Long id);

}