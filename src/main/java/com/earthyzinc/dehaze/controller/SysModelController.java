package com.earthyzinc.dehaze.controller;

import com.earthyzinc.dehaze.common.annotation.PreventDuplicateSubmit;
import com.earthyzinc.dehaze.common.model.Option;
import com.earthyzinc.dehaze.common.result.Result;
import com.earthyzinc.dehaze.model.form.ModelForm;
import com.earthyzinc.dehaze.model.query.ModelQuery;
import com.earthyzinc.dehaze.model.vo.ModelVO;
import com.earthyzinc.dehaze.service.SysModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "09.模型接口")
@RestController
@RequestMapping("/api/v1/model")
@RequiredArgsConstructor
public class SysModelController {

    private final SysModelService modelService;

    @Operation(summary = "获取模型列表", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping
    public Result<List<ModelVO>> listModels(
            @ParameterObject ModelQuery queryParams
    ) {
        List<ModelVO> list = modelService.listModels(queryParams);
        return Result.success(list);
    }

    @Operation(summary = "获取模型下拉选项", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/options")
    public Result<List<Option>> listModelOptions() {
        List<Option> list = modelService.listModelOptions();
        return Result.success(list);
    }

    @Operation(summary = "新增部门", security = {@SecurityRequirement(name = "Authorization")})
    @PostMapping
    @PreventDuplicateSubmit
    public Result saveDept(
            @Valid @RequestBody ModelForm formData
    ) {
        Long id = modelService.saveModel(formData);
        return Result.success(id);
    }

    @Operation(summary = "修改模型", security = {@SecurityRequirement(name = "Authorization")})
    @PutMapping(value = "/{modelId}")
    public Result updateModel(
            @PathVariable Long modelId,
            @Valid @RequestBody ModelForm formData
    ) {
        modelId = modelService.updateModel(modelId, formData);
        return Result.success(modelId);
    }

    @Operation(summary = "删除模型", security = {@SecurityRequirement(name = "Authorization")})
    @DeleteMapping("/{ids}")
    public Result deleteModels(
            @Parameter(description ="模型ID，多个以英文逗号(,)分割")
            @PathVariable("ids") String ids
    ) {
        boolean result = modelService.deleteByIds(ids);
        return Result.judge(result);
    }
}
