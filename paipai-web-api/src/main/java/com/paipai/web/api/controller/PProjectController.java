package com.paipai.web.api.controller;

import com.paipai.core.service.PProjectService;
import com.paipai.data.dto.ProjectListDto;
import com.paipai.data.entity.PProject;
import com.paipai.data.vo.PProjectVo;
import com.paipai.security.auth.HSession;
import com.paipai.security.res.RestRes;
import com.paipai.web.api.vo.SUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@Tag(name = "项目相关API")
@RestController
@RequestMapping("/project")
public class PProjectController {

    @Autowired
    private PProjectService pprojectService;

    @Operation(summary = "项目列表")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = RestResProjectList.class)))
    @PostMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestRes<List<PProjectVo>> list(@Valid @RequestBody ProjectListDto dto) {
        return RestRes.success(pprojectService.queryProjectVoByPageable(dto));
    }

    @Operation(summary = "项目信息")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = RestResProject.class)))
    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestRes<PProject> get(@PathVariable @Parameter(description = "项目ID", required = true) String id) {
        try {
            Long projectId = Long.parseLong(id);
            PProject pproject = pprojectService.queryById(projectId);
            if (Objects.nonNull(pproject)) {
                return RestRes.success(pproject);
            }
        } catch (Exception e) {
        }
        return RestRes.success(PProject.builder().build());
    }

    @Operation(summary = "订阅项目")
    @ApiResponse(responseCode = "200", description = RestRes.SUCCESS_DESC, content = @Content(schema = @Schema(implementation = RestRes.RestResInteger.class)))
    @GetMapping(value = "/addSub/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestRes<Integer>> addSub(@PathVariable @Parameter(description = "项目ID", required = true) String id, @Parameter(hidden = true) HSession<SUser> session) {
        try {
            Long projectId = Long.parseLong(id);
            boolean exists = pprojectService.exists(projectId);
            if (exists) {
                Long userId = session.getUser().getId();
                pprojectService.addSub(projectId, userId);
                return ResponseEntity.ok(RestRes.success());
            }
        } catch (Exception e) {
        }
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(RestRes.error("项目不存在！"));
    }

    @Operation(summary = "订阅取消")
    @ApiResponse(responseCode = "200", description = RestRes.SUCCESS_DESC)
    @GetMapping(value = "/unSub/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestRes<Integer> unSub(@PathVariable @Parameter(description = "项目ID", required = true) String id, @Parameter(hidden = true) HSession<SUser> session) {
        try {
            Long projectId = Long.parseLong(id);
            Long userId = session.getUser().getId();
            pprojectService.unSub(projectId, userId);
        } catch (Exception e) {
        }
        return RestRes.success();
    }
}



@Schema(name = "项目信息")
class RestResProject extends RestRes<PProject> {
}

@Schema(name = "项目列表")
class RestResProjectList extends RestRes<List<PProjectVo>> {
}
