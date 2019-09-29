package com.api.expand.unit;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONException;


import java.io.IOException;

public class VerificationCode {
    public String sendCode(String phone) {
   //public static void main(String [] args) {
        // 短信应用 SDK AppID
        int appid = 1400263519; // SDK AppID 以1400开头
        // 短信应用 SDK AppKey
        String appkey = "342211feb3d17fd9c2e284d504359fae";
        // 需要发送短信的手机号码
        String phoneNumbers = phone;
        // 短信模板 ID，需要在短信应用中申请
        int templateId = 429164; // NOTE: 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请
        // 签名
        String smsSign = "叶延裕个人技术";
        SmsSingleSenderResult result = null;
        String codeV = null;
        try {
            String code=RandomStringUtils.randomNumeric(6);
            String[] params = {code};
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            result = ssender.sendWithParam("86", phoneNumbers,
                    templateId, params, smsSign, "", "");
            System.out.println(result.result);
            if(result.result == 0){
                codeV = code;
            }else{
                codeV = "404";
            }
        } catch (HTTPException e) {
            // HTTP 响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // JSON 解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络 IO 错误
            e.printStackTrace();
        }
        return codeV;
    }
}
