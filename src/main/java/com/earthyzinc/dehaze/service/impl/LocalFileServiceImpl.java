package com.earthyzinc.dehaze.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.earthyzinc.dehaze.mapper.SysFileMapper;
import com.earthyzinc.dehaze.model.entity.SysFile;
import com.earthyzinc.dehaze.model.query.FilePageQuery;
import com.earthyzinc.dehaze.model.vo.FilePageVO;
import com.earthyzinc.dehaze.service.LocalFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
public class LocalFileServiceImpl extends ServiceImpl<SysFileMapper, SysFile>
        implements LocalFileService {


    @Override
    public IPage<FilePageVO> listFileVOList(FilePageQuery queryParams) {
        return null;
    }

    @Override
    public String checkMD5(String md5) {
        return null;
    }

    @Override
    public String uploadFile(MultipartFile file) {
        return null;
    }

    @Override
    public void deleteFile(List<Long> fileIdList) {

    }

    @Override
    public void downloadFile(Long fileId) {

    }
}
