package com.earthyzinc.dehaze.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.earthyzinc.dehaze.common.model.Option;
import com.earthyzinc.dehaze.mapper.SysModelMapper;
import com.earthyzinc.dehaze.model.entity.SysModel;
import com.earthyzinc.dehaze.model.form.ModelForm;
import com.earthyzinc.dehaze.model.query.ModelQuery;
import com.earthyzinc.dehaze.model.vo.ModelVO;
import com.earthyzinc.dehaze.service.SysModelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysModelServiceImpl extends ServiceImpl<SysModelMapper, SysModel>
        implements SysModelService {
    @Override
    public List<ModelVO> listModels(ModelQuery queryParams) {
        return null;
    }

    @Override
    public List<Option> listModelOptions() {
        return null;
    }

    @Override
    public Long saveModel(ModelForm formData) {
        return null;
    }

    @Override
    public Long updateModel(Long modelId, ModelForm formData) {
        return null;
    }

    @Override
    public boolean deleteByIds(String ids) {
        return false;
    }

    @Override
    public ModelForm getModelForm(Long modelId) {
        return null;
    }
}
