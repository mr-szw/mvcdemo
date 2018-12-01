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
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Dawei 2018/11/17
 */
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping(value = "/subLogin")
    public ModelAndView login(UserInfoDto userInfoDto) {
        logger.info("登陆信息为 userInfo={}", JSON.toJSONString(userInfoDto));
        ResultDto<String> resultDto = new ResultDto<>();
        String resultUrl = "login";
        if (userInfoDto == null || StringUtils.isEmpty(userInfoDto.getLoginName()) || StringUtils.isEmpty(userInfoDto.getPassWord()) || userInfoDto.getRememberType() == null) {
            resultDto.setParamError();
        } else {
            //获取主体
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userInfoDto.getLoginName(), userInfoDto.getPassWord());
            try {
                subject.login(token);
                resultDto.setSuccess();
                resultUrl = "redirect: /indexPage";
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
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(resultUrl);
        modelAndView.addObject("resultData", resultDto);
        return modelAndView;
    }

}
