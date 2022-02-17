package com.many.reader.service;

import com.many.reader.entity.Evaluation;
import com.many.reader.entity.Member;
import com.many.reader.entity.MemberReadState;

import java.util.Map;

public interface MemberService {
    /**
     *  会员注册
     * @param username 用户名
     * @param password 密码
     * @param nickname 昵称
     * @return 新会员
     */
    public Member createMember(String username, String password, String nickname);

    /**
     *  检查登录
     * @param username 用户名
     * @param password 密码
     * @return 会员
     */
    public Member checkLogin(String username, String password);

    /**
     *  获取会员阅读状态
     * @param memberId 会员编号
     * @param bookId 图书编号
     * @return 阅读状态对象
     */
    public MemberReadState selectMemberReadState(Long memberId, Long bookId);

    /**
     *  更新会员阅读状态
     * @param memberId 会员编号
     * @param bookId 图书编号
     * @param readState 阅读状态
     * @return 阅读状态对象
     */
    public MemberReadState updateMemberReadState(Long memberId, Long bookId, Integer readState);



    /**
     *写短评
     * @param bookId 图书编号
     * @param memberId 会员编号
     * @param score 评分
     * @param content 短评内容
     * @return 短评对象
     */

    public Evaluation evaluate(Long bookId, Long memberId, Integer score, String content);

    /**
     *  短评点赞
     * @param evaluationId 短评编号
     * @return 短评对象
     */
    public Evaluation enjoy(Long evaluationId, Long memberId, Long bookId);

    public Map selectList();

    /**
     * 更新会员信息
     */
    public void updateByMemberId(Member member);
    public Member select(Long memberId);

    public void batchUpdate();
}
