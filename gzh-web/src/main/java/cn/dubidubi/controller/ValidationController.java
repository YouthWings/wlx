package cn.dubidubi.controller;

import cn.dubidubi.util.VerifyTokenUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/verify")
public class ValidationController {
    /**
     * 微信消息接收和token验证
     * @param request
     * @return
     */
    @RequestMapping("/con")
    public void con(HttpServletRequest request, HttpServletResponse response){
        String signature=request.getParameter("signature");
        String timestamp=request.getParameter("timestamp");
        String nonce =request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        VerifyTokenUtil verifyTokenUtil = new VerifyTokenUtil();
        PrintWriter writer =null;
        if(verifyTokenUtil.checkSignature(signature,timestamp,nonce)){
            try {
                writer=response.getWriter();
                writer.write(echostr);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
               if (writer!=null){
                   writer.close();
               }
            }
        }
    }
}
