package com.many.reader.service.impl;

import com.many.reader.service.MemberService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MemberServiceImplTest extends TestCase {
    @Resource
    private MemberService memberService;

    @Test
    public void testCreateMember() {
        memberService.createMember("user0005", "123456", "laowang");
    }
}