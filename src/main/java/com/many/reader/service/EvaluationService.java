package com.many.reader.service;


import com.many.reader.entity.Evaluation;

import java.util.List;

public interface EvaluationService {
    /**
     * 根据图书编号查询有效短评
     * @param bookId 图书编号
     * @return  短评列表
     */
    public List<Evaluation> selectByBookId(Long bookId);
}
