package com.many.reader.service;

import com.many.reader.entity.Member;
import com.many.reader.mapper.MemberMapper;
import com.many.reader.service.exception.BussinessException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestServiceTest {
    @Resource
    private TestService testService;
    @Test
    public void batchImport() {
        testService.batchImport();
        System.out.println("批量导入成功");
    }

    @Resource
    private MemberService memberService;

    @Test
    public void batchUpdate(){
        String newName = "reader_";
        String newNickname = "老铁-";
        for (int i = 0; i < 502; i ++){
            try {
                Member member = memberService.select(i + 1l);
                String s = String.valueOf(member.getMemberId());
                //拿到memberId 拼接到"reader_"和"老铁-"后面，进行修改
                member.setUsername(newName + s);
                member.setNickname(newNickname + s);
                member.setMemberId(member.getMemberId());
                member.setSalt(member.getSalt());
                member.setPassword(member.getPassword());
                member.setCreateTime(member.getCreateTime());
                memberService.updateByMemberId(member);
            }catch (BussinessException be){
                be.printStackTrace();
            }
        }
    }
   /* public void batchUpdate(){
        memberService.batchUpdate();
    }*/
}