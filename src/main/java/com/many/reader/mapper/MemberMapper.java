package com.many.reader.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.many.reader.entity.Member;

public interface MemberMapper extends BaseMapper<Member> {
    void batchUpdate();
}
