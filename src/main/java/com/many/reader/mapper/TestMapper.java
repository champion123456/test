package com.many.reader.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.many.reader.entity.Test;

public interface TestMapper extends BaseMapper<Test> {
    public void insertSample();
}
