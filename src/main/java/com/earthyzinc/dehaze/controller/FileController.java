package com.earthyzinc.dehaze.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.earthyzinc.dehaze.common.result.PageResult;
import com.earthyzinc.dehaze.common.result.Result;
import com.earthyzinc.dehaze.model.dto.FileInfo;
import com.earthyzinc.dehaze.model.query.FilePageQuery;
import com.earthyzinc.dehaze.model.vo.FilePageVO;
import com.earthyzinc.dehaze.service.LocalFileService;
import com.earthyzinc.dehaze.service.OssService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "07.文件接口")
@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {

    private final OssService ossService;

    private final LocalFileService fileService;

    @PostMapping
    @Operation(summary = "文件上传到OOS", security = {@SecurityRequirement(name = "Authorization")})
    public Result<FileInfo> uploadFile(
            @Parameter(description ="表单文件对象") @RequestParam(value = "file") MultipartFile file
    ) {
        FileInfo fileInfo = ossService.uploadFile(file);
        return Result.success(fileInfo);
    }

    @DeleteMapping
    @Operation(summary = "从OOS中删除文件", security = {@SecurityRequirement(name = "Authorization")})
    @SneakyThrows
    public Result deleteFile(
            @Parameter(description ="文件路径") @RequestParam String filePath
    ) {
        boolean result = ossService.deleteFile(filePath);
        return Result.judge(result);
    }

    @GetMapping("/list")
    @Operation(summary = "查看文件列表", security = {@SecurityRequirement(name = "Authorization")})
    public PageResult<FilePageVO> listFileVOList(@ParameterObject FilePageQuery queryParams){
        IPage<FilePageVO> filePageVOPage = fileService.listFileVOList(queryParams);
        return PageResult.success(filePageVOPage);
    }

    @GetMapping("/check/{md5}")
    @Operation(summary = "检查文件是否在服务器存在，如存在则返回文件ID", security = {@SecurityRequirement(name = "Authorization")})
    public Result<String> checkFileExists(@PathVariable("md5") String md5){
        String fileId = fileService.checkMD5(md5);
        return fileId != null ? Result.success(fileId) : Result.failed("文件不存在");
    }

    @PostMapping("/upload")
    @Operation(summary = "上传文件到服务器", security = {@SecurityRequirement(name = "Authorization")})
    public Result<String> uploadFileToServer(
            @Parameter(description ="表单文件对象") @RequestParam(value = "file") MultipartFile file
    ) {
        String fileId = fileService.uploadFile(file);
        return Result.success(fileId);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除服务器上的文件", security = {@SecurityRequirement(name = "Authorization")})
    public Result<?> deleteFileFromServer(@RequestBody List<Long> fileIdList){
        fileService.deleteFile(fileIdList);
        return Result.success();
    }

    @GetMapping("/{fileId}")
    @Operation(summary = "下载文件", security = {@SecurityRequirement(name = "Authorization")})
    public Result<?> downloadFile(@PathVariable("fileId") Long fileId){
        fileService.downloadFile(fileId);
        return Result.success();
    }
}
