package com.mvc.demo.controller;

import com.alibaba.fastjson.JSON;
import com.mvc.demo.enums.ErrorEnum;
import com.mvc.demo.pojo.ResultDto;
import com.mvc.demo.pojo.UserInfoDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Dawei 2018/11/17
 */
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 提交登陆信息
     *
     * @param userInfoDto 登陆用户信息体
     */
    @PostMapping(value = "/subLogin")
    public String login(UserInfoDto userInfoDto, Model model, String callbackUrl) {
        logger.info("登陆信息为 userInfo={}, callbackUrl={}", JSON.toJSONString(userInfoDto), callbackUrl);
        ResultDto<String> resultDto = new ResultDto<>();
        String resultUrl = "/toLogin";
        if (userInfoDto == null || StringUtils.isEmpty(userInfoDto.getLoginName()) || StringUtils.isEmpty(userInfoDto.getPassWord()) || userInfoDto.getRememberType() == null) {
            resultDto.setParamError();
        } else {
            //获取主体
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userInfoDto.getLoginName(), userInfoDto.getPassWord());
            try {
                subject.login(token);
                resultDto.setSuccess();
                if (!StringUtils.isEmpty(callbackUrl)) {
                    resultUrl = "redirect:/" + callbackUrl;
                } else {
                    resultUrl = "redirect:/home/index";
                }
            } catch (IncorrectCredentialsException e) {
                resultDto.setResult(ErrorEnum.LOGIN_FAIL_PASSWORD);
                resultDto.setData(e.getMessage());
            } catch (AuthenticationException e) {
                resultDto.setResult(ErrorEnum.LOGIN_FAIL_USERNAME);
                resultDto.setData(e.getMessage());
            } catch (Exception e) {
                resultDto.setResult(ErrorEnum.LOGIN_FAILED);
                resultDto.setData(e.getMessage());
            }
        }
        logger.info("登陆结果为： resultUrl={} resultDto={}", resultUrl, JSON.toJSONString(resultDto));
        model.addAttribute("resultData", resultDto);
        return resultUrl;
    }


    /**
     * 請求空或根目录
     */
    @RequestMapping({"/", ""})
    public String rootRequest() {
        logger.info("进入Root请求~~~~~");
        Subject subject = SecurityUtils.getSubject();
        //已经认证跳转取去主页面 否则去登陆
        return subject.isAuthenticated() ? "redirect:/home/index" : "/toLogin";
    }

    /**
     * 进入首页
     */
    @GetMapping(value = "/home/index")
    public String homePageIndex() {
        logger.info("进入主页面请求~~~~~");
        Subject subject = SecurityUtils.getSubject();
        //已经认证跳转取去主页面 否则去登陆
        return subject.isAuthenticated() ? "/homePageIndex" : "/toLogin";
    }

}
