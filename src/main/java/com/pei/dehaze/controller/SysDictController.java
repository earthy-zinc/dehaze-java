package com.pei.dehaze.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pei.dehaze.common.model.Option;
import com.pei.dehaze.common.result.PageResult;
import com.pei.dehaze.common.result.Result;
import com.pei.dehaze.model.form.DictForm;
import com.pei.dehaze.model.form.DictTypeForm;
import com.pei.dehaze.model.query.DictPageQuery;
import com.pei.dehaze.model.query.DictTypePageQuery;
import com.pei.dehaze.model.vo.DictPageVO;
import com.pei.dehaze.model.vo.DictTypePageVO;
import com.pei.dehaze.plugin.dupsubmit.annotation.PreventDuplicateSubmit;
import com.pei.dehaze.service.SysDictService;
import com.pei.dehaze.service.SysDictTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "06.字典接口")
@RestController
@RequestMapping("/api/v1/dict")
@RequiredArgsConstructor
public class SysDictController {

    private final SysDictService dictService;

    private final SysDictTypeService dictTypeService;

    @Operation(summary = "字典分页列表")
    @GetMapping("/page")
    public PageResult<DictPageVO> getDictPage(
            @ParameterObject DictPageQuery queryParams
    ) {
        Page<DictPageVO> result = dictService.getDictPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "字典数据表单数据")
    @GetMapping("/{id}/form")
    public Result<DictForm> getDictForm(
            @Parameter(description ="字典ID") @PathVariable Long id
    ) {
        DictForm formData = dictService.getDictForm(id);
        return Result.success(formData);
    }

    @Operation(summary = "新增字典")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('sys:dict:add')")
    @PreventDuplicateSubmit
    public Result<Void> saveDict(
            @RequestBody DictForm DictForm
    ) {
        boolean result = dictService.saveDict(DictForm);
        return Result.judge(result);
    }

    @Operation(summary = "修改字典")
    @PutMapping("/{id}")
    @PreAuthorize("@ss.hasPerm('sys:dict:edit')")
    public Result<Void> updateDict(
            @PathVariable Long id,
            @RequestBody DictForm DictForm
    ) {
        boolean status = dictService.updateDict(id, DictForm);
        return Result.judge(status);
    }

    @Operation(summary = "删除字典")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('sys:dict:delete')")
    public Result<Void> deleteDict(
            @Parameter(description ="字典ID，多个以英文逗号(,)拼接") @PathVariable String ids
    ) {
        boolean result = dictService.deleteDict(ids);
        return Result.judge(result);
    }


    @Operation(summary = "字典下拉列表")
    @GetMapping("/{typeCode}/options")
    public Result<List<Option<String>>> listDictOptions(
            @Parameter(description ="字典类型编码") @PathVariable String typeCode
    ) {
        List<Option<String>> list = dictService.listDictOptions(typeCode);
        return Result.success(list);
    }


    /*----------------------------------------------------*/
    @Operation(summary = "字典类型分页列表")
    @GetMapping("/types/page")
    public PageResult<DictTypePageVO> getDictTypePage(
            @ParameterObject DictTypePageQuery queryParams
    ) {
        Page<DictTypePageVO> result = dictTypeService.getDictTypePage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "字典类型表单数据")
    @GetMapping("/types/{id}/form")
    public Result<DictTypeForm> getDictTypeForm(
            @Parameter(description ="字典ID") @PathVariable Long id
    ) {
        DictTypeForm dictTypeForm = dictTypeService.getDictTypeForm(id);
        return Result.success(dictTypeForm);
    }

    @Operation(summary = "新增字典类型")
    @PostMapping("/types")
    @PreAuthorize("@ss.hasPerm('sys:dict_type:add')")
    @PreventDuplicateSubmit
    public Result<Void> saveDictType(@RequestBody DictTypeForm dictTypeForm) {
        boolean result = dictTypeService.saveDictType(dictTypeForm);
        return Result.judge(result);
    }

    @Operation(summary = "修改字典类型")
    @PutMapping("/types/{id}")
    @PreAuthorize("@ss.hasPerm('sys:dict_type:edit')")
    public Result<Void> updateDictType(@PathVariable Long id, @RequestBody DictTypeForm dictTypeForm) {
        boolean status = dictTypeService.updateDictType(id, dictTypeForm);
        return Result.judge(status);
    }

    @Operation(summary = "删除字典类型")
    @DeleteMapping("/types/{ids}")
    @PreAuthorize("@ss.hasPerm('sys:dict_type:delete')")
    public Result<Void> deleteDictTypes(
            @Parameter(description ="字典类型ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = dictTypeService.deleteDictTypes(ids);
        return Result.judge(result);
    }

}
