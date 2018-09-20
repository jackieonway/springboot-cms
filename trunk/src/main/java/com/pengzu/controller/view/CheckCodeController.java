package com.pengzu.controller.view;

import com.pengzu.utils.VerifyCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
public class CheckCodeController {

    @RequestMapping("/checkCode.jpg")
    public void checkCode(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        String verifyCode = "";
        try {
            switch (new Random().nextInt(3)) {
                case 0:
                    verifyCode = VerifyCodeUtils.generateVerifyCode(6);
                    VerifyCodeUtils.outputImageResponse(verifyCode, verifyCode, response, request);
                    break;
                case 1:
                    map = VerifyCodeUtils.getChinese(6);
                    VerifyCodeUtils.outputImageResponse(map.get("code") + "", map.get("result") + "", response, request);
                    break;
                case 2:
                    map = VerifyCodeUtils.getText(6);
                    VerifyCodeUtils.outputImageResponse(map.get("code") + "", map.get("result") + "", response, request);
                    break;
                default:
                    verifyCode = VerifyCodeUtils.generateVerifyCode(6);
                    VerifyCodeUtils.outputImageResponse(verifyCode, verifyCode, response, request);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
