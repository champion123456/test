package com.many.reader.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("enjoy")
public class Enjoy {
    @TableId(type = IdType.AUTO)
    private Long enjoyId;
    private Long evaluationId;
    private Long memberId;
    private Integer isEnjoyed;
    private Long bookId;

    public Long getEnjoyId() {
        return enjoyId;
    }

    public void setEnjoyId(Long enjoyId) {
        this.enjoyId = enjoyId;
    }

    public Long getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Long evaluationId) {
        this.evaluationId = evaluationId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Integer getIsEnjoyed() {
        return isEnjoyed;
    }

    public void setIsEnjoyed(Integer isEnjoyed) {
        this.isEnjoyed = isEnjoyed;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
