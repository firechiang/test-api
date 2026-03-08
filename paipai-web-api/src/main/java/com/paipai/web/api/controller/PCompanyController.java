package com.paipai.web.api.controller;

import com.paipai.core.service.PCompanyService;
import com.paipai.data.entity.PCompany;
import com.paipai.security.res.RestRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Tag(name = "公司相关API")
@Slf4j
@RequestMapping("/company")
@RestController
public class PCompanyController {

    @Autowired
    private PCompanyService pcompanyService;


    @Operation(summary = "公司信息")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = RestResCompany.class)))
    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestRes<PCompany> get(@PathVariable @Parameter(description = "公司ID", required = true) String id) {
        try {
            Long companyId = Long.parseLong(id);
            PCompany pcompany = pcompanyService.queryById(companyId);
            if (Objects.nonNull(pcompany)) {
                return RestRes.success(pcompany);
            }
        } catch (Exception e) {
        }
        return RestRes.success(PCompany.builder().build());
    }
}


@Schema(name = "公司信息")
class RestResCompany extends RestRes<PCompany> {
}
