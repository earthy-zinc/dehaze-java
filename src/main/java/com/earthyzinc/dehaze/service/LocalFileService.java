package com.earthyzinc.dehaze.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.earthyzinc.dehaze.model.entity.SysFile;
import com.earthyzinc.dehaze.model.query.FilePageQuery;
import com.earthyzinc.dehaze.model.vo.FilePageVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface LocalFileService extends IService<SysFile> {


    IPage<FilePageVO> listFileVOList(FilePageQuery queryParams);

    /**
     * 检查文件MD5
     * @param md5 true 存在此文件 false 不存在此文件
     */
    String checkMD5(String md5);

    /**
     * 上传文件
     *
     * @param file 文件
     */
    String uploadFile(MultipartFile file);

    /**
     * 删除文件
     *
     * @param fileIdList 文件id列表
     */
    void deleteFile(List<Long> fileIdList);

    /**
     * 下载文件
     *
     * @param fileId 文件id
     */
    void downloadFile(Long fileId);
}
