package com.many.reader.controller;

import com.many.reader.entity.Evaluation;
import com.many.reader.entity.Member;
import com.many.reader.service.MemberService;
import com.many.reader.service.exception.BussinessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MemberController {
    @Resource
    private MemberService memberService;

    @GetMapping("/register.html")
    public ModelAndView showRegister(){
        return new ModelAndView("/register");
    }

    @GetMapping("/login.html")
    public ModelAndView showLogin(){
        return new ModelAndView("/login");
    }

    @PostMapping("/registe")
    @ResponseBody
    public Map registe(String vc, String username, String password, String nickname, HttpServletRequest request){
        //正确验证码
        String verifyCode = (String) request.getSession().getAttribute("kaptchaVerifyCode");
        //验证码对比
        Map result = new HashMap();
        if (vc == null || verifyCode == null || !vc.equalsIgnoreCase(verifyCode)){
            result.put("code", "VC01");
            result.put("msg", "验证码错误");
        } else {
            try{
                memberService.createMember(username, password, nickname);
                result.put("code", "0");
                result.put("msg", "success");
            } catch (BussinessException be){
                be.printStackTrace();
                result.put("code", be.getCode());
                result.put("msg", be.getMsg());
            }
        }
        return result;
    }

    @PostMapping("/check_login")
    @ResponseBody
    public Map checkLogin(String username, String password, String vc, HttpSession session){
        //正确验证码
        String verifyCode = (String) session.getAttribute("kaptchaVerifyCode");
        //验证码对比
        Map result = new HashMap();
        if (vc == null || verifyCode == null || !vc.equalsIgnoreCase(verifyCode)){
            result.put("code", "VC01");
            result.put("msg", "验证码错误");
        } else {
            try {
                Member member = memberService.checkLogin(username, password);
                session.setAttribute("loginMember", member);
                result.put("code", "0");
                result.put("msg", "success");
            } catch (BussinessException be){
                be.printStackTrace();
                be.printStackTrace();
                result.put("code", be.getCode());
                result.put("msg", be.getMsg());
            }
        }
        return result;
    }

    /**
     * 图书阅读状态变更
     * @param bookId 图书编号
     * @param memberId 会员编号
     * @param readState 阅读状态
     * @return
     */
    @PostMapping("/update_read_state")
    @ResponseBody
    public Map updateReadState(Long bookId, Long memberId, Integer readState){
        Map result = new HashMap<>();
        try {
            memberService.updateMemberReadState(memberId, bookId, readState);
            result.put("code", "0");
            result.put("msg", "success");
        } catch (BussinessException be){
            be.printStackTrace();
            result.put("code", be.getCode());
            result.put("msg", be.getMsg());
        }
        return result;
    }

    /**
     * 评论方法
     * @param bookId
     * @param memberId
     * @param score
     * @param content
     * @return
     */
    @PostMapping("/evaluate")
    @ResponseBody
    public Map evaluate(Long bookId, Long memberId, Integer score, String content){
        Map result = new HashMap<>();
        try {
            Evaluation eva = memberService.evaluate(bookId, memberId, score, content);
            result.put("code", "0");
            result.put("msg", "success");
            result.put("evaluation", eva);
        } catch (BussinessException be){
            be.printStackTrace();
            result.put("code", be.getCode());
            result.put("msg", be.getMsg());
        }
        return result;
    }

    /**
     * 短评点赞
     * @param evaluationId 评论编号
     * @param memberId 会员编号
     * @param bookId 图书编号
     * @return
     */
    @PostMapping("/enjoy")
    @ResponseBody
    public Map evaluate(Long evaluationId, Long memberId, Long bookId){
        Map result = new HashMap<>();
        try {
            Evaluation eva = memberService.enjoy(evaluationId, memberId, bookId);
            result.put("code", "0");
            result.put("msg", "success");
            result.put("evaluation", eva);
        } catch (BussinessException be){
            be.printStackTrace();
            result.put("code", be.getCode());
            result.put("msg", be.getMsg());
        }
        return result;
    }
}
