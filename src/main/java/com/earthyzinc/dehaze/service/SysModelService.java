package com.earthyzinc.dehaze.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.earthyzinc.dehaze.common.model.Option;
import com.earthyzinc.dehaze.model.entity.SysModel;
import com.earthyzinc.dehaze.model.form.ModelForm;
import com.earthyzinc.dehaze.model.query.ModelQuery;
import com.earthyzinc.dehaze.model.vo.ModelVO;

import java.util.List;

/**
 * 模型业务接口
 */
public interface SysModelService extends IService<SysModel> {
    List<ModelVO> listModels(ModelQuery queryParams);

    List<Option> listModelOptions();

    Long saveModel(ModelForm formData);

    Long updateModel(Long modelId, ModelForm formData);

    boolean deleteByIds(String ids);

    ModelForm getModelForm(Long modelId);
}
