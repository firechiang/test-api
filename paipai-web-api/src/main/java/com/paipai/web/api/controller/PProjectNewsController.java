package com.paipai.web.api.controller;

import com.paipai.core.service.PProjectNewsService;
import com.paipai.data.dto.ProjectNewsListDto;
import com.paipai.data.entity.PProjectNews;
import com.paipai.security.res.RestRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@Tag(name = "项目动态相关API")
@RestController
@RequestMapping("/project/news")
public class PProjectNewsController {

    @Autowired
    private PProjectNewsService pprojectNewsService;


    @Operation(summary = "项目动态信息")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = RestResProjectNews.class)))
    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestRes<PProjectNews> get(@PathVariable @Parameter(description = "项目动态ID", required = true) String id) {
        try {
            Long projectNewsId = Long.parseLong(id);
            PProjectNews pprojectNews = pprojectNewsService.queryById(projectNewsId);
            if (Objects.nonNull(pprojectNews)) {
                return RestRes.success(pprojectNews);
            }
        } catch (Exception e) {
        }
        return RestRes.success(PProjectNews.builder().build());
    }

    @Operation(summary = "项目动态列表")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = RestResProjectNewsList.class)))
    @PostMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestRes<List<PProjectNews>> list(@Valid @RequestBody ProjectNewsListDto dto) {
        List<PProjectNews> pprojectNewsList = pprojectNewsService.queryByPageable(dto);
        return RestRes.success(pprojectNewsList);
    }
}


@Schema(name = "项目动态信息")
class RestResProjectNews extends RestRes<PProjectNews> {
}

@Schema(name = "项目动态列表")
class RestResProjectNewsList extends RestRes<List<PProjectNews>> {
}
