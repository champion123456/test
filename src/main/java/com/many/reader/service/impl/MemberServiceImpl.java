package com.many.reader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.many.reader.entity.Enjoy;
import com.many.reader.entity.Evaluation;
import com.many.reader.entity.Member;
import com.many.reader.entity.MemberReadState;
import com.many.reader.mapper.EnjoyMapper;
import com.many.reader.mapper.EvaluationMapper;
import com.many.reader.mapper.MemberMapper;
import com.many.reader.mapper.MemberReadStateMapper;
import com.many.reader.service.MemberService;
import com.many.reader.service.exception.BussinessException;
import com.many.reader.utils.MD5Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
    @Resource
    private EnjoyMapper enjoyMapper;

    @Resource
    private MemberMapper memberMapper;

    @Resource
    private MemberReadStateMapper memberReadStateMapper;

    @Resource
    private EvaluationMapper evaluationMapper;
    /**
     * 会员注册
     *
     * @param username 用户名
     * @param password 密码
     * @param nickname 昵称
     * @return 新会员
     */
    @Override
    public Member createMember(String username, String password, String nickname) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<Member>();
        queryWrapper.eq("username", username);
        List<Member> memberList = memberMapper.selectList(queryWrapper);
        //判断用户名是否存在
        if (memberList.size() > 0){
            throw new BussinessException("M01", "用户已存在");
        }
        Member member = new Member();
        member.setUsername(username);
        member.setNickname(nickname);
        int salt = new Random().nextInt(1000) + 1000;//盐值
        String md5 = MD5Utils.md5Digest(password, salt);
        member.setPassword(md5);
        member.setSalt(salt);
        member.setCreateTime(new Date());
        memberMapper.insert(member);
        return member;
    }

    /**
     * 检查登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 会员
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Member checkLogin(String username, String password) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<Member>();
        queryWrapper.eq("username", username);
        Member member = memberMapper.selectOne(queryWrapper);
        if (member == null){
            throw new BussinessException("M02", "用户不存在");
        }
        String md5 = MD5Utils.md5Digest(password, member.getSalt());
        if (!md5.equals(member.getPassword())){
            throw new BussinessException("M03", "密码错误");
        }
        return member;
    }

    /**
     * 获取会员阅读状态
     *
     * @param memberId 会员编号
     * @param bookId   图书编号
     * @return 会员阅读状态
     */
    @Override
    public MemberReadState selectMemberReadState(Long memberId, Long bookId) {
        QueryWrapper<MemberReadState> queryWrapper = new QueryWrapper<MemberReadState>();
        queryWrapper.eq("member_id", memberId);
        queryWrapper.eq("book_id", bookId);
        MemberReadState memberReadState = memberReadStateMapper.selectOne(queryWrapper);
        return memberReadState;
    }

    /**
     * 更新会员阅读状态
     *
     * @param memberId  会员编号
     * @param bookId    图书编号
     * @param readState 阅读状态
     * @return 阅读状态对象
     */
    @Override
    public MemberReadState updateMemberReadState(Long memberId, Long bookId, Integer readState) {
        QueryWrapper<MemberReadState> queryWrapper = new QueryWrapper<MemberReadState>();
        queryWrapper.eq("member_id", memberId);
        queryWrapper.eq("book_id", bookId);
        MemberReadState memberReadState = memberReadStateMapper.selectOne(queryWrapper);
        //阅读状态无则新增，有则更新
        if (memberReadState == null){
            memberReadState = new MemberReadState();
            memberReadState.setReadState(readState);
            memberReadState.setMemberId(memberId);
            memberReadState.setBookId(bookId);
            memberReadState.setCreateTime(new Date());
            memberReadStateMapper.insert(memberReadState);
        }else {
            memberReadState.setReadState(readState);
            memberReadStateMapper.updateById(memberReadState);
        }
        return memberReadState;
    }

    /**
     * @param bookId   图书编号
     * @param memberId 会员编号
     * @param score    评分
     * @param content  短评内容
     * @return 短评对象
     */
    @Override
    public Evaluation evaluate(Long bookId, Long memberId, Integer score, String content) {
        Evaluation evaluation = new Evaluation();
        evaluation.setBookId(bookId);
        evaluation.setMemberId(memberId);
        evaluation.setScore(score);
        evaluation.setContent(content);
        evaluation.setCreateTime(new Date());
        evaluation.setState("enable");
        evaluation.setEnjoy(0);//默认点赞数量
        evaluationMapper.insert(evaluation);
        return evaluation;
    }

    /**
     * 短评点赞
     *
     * @param evaluationId 短评编号
     * @return 短评对象
     */
    @Override
    public Evaluation enjoy(Long evaluationId, Long memberId, Long bookId) {
        QueryWrapper<Enjoy> queryWrapper = new QueryWrapper<Enjoy>();
        queryWrapper.eq("evaluation_id", evaluationId);
        queryWrapper.eq("member_id", memberId);
        queryWrapper.eq("book_id", bookId);
        Evaluation evaluation = evaluationMapper.selectById(evaluationId);
        Enjoy enjoy = enjoyMapper.selectOne(queryWrapper);
        //点赞过
        if (enjoy != null){
            if (enjoy.getIsEnjoyed() == 1) {
                enjoy.setIsEnjoyed(0);
                evaluation.setEnjoy(evaluation.getEnjoy() - 1);
                enjoyMapper.updateById(enjoy);
            }else {
                enjoy.setIsEnjoyed(1);
                evaluation.setEnjoy(evaluation.getEnjoy() + 1);
                enjoyMapper.updateById(enjoy);
            }
        }else {
            //不曾点赞
            Enjoy enjoy1 = new Enjoy();
            enjoy1.setEvaluationId(evaluationId);
            enjoy1.setMemberId(memberId);
            enjoy1.setIsEnjoyed(1);
            enjoy1.setBookId(bookId);
            evaluation.setEnjoy(evaluation.getEnjoy() + 1);
            enjoyMapper.insert(enjoy1);
        }
        evaluationMapper.updateById(evaluation);
        return evaluation;
    }

    @Override
    public Map selectList() {
        Map map = new HashMap();
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("member_id");
        List<Member> memberList = memberMapper.selectList(queryWrapper);
        int size = memberList.size();
        map.put("count", size);
        map.put("memberList", memberList);
        return map;
    }

    /**
     * 更新会员信息
     */
    public void updateByMemberId(Member member) {


        memberMapper.updateById(member);
    }

    public Member select(Long memberId) {
        Member member = memberMapper.selectById(memberId);
        return member;
    }

    public void batchUpdate() {
        memberMapper.batchUpdate();
    }


}
