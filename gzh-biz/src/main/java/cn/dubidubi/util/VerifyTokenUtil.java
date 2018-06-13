package cn.dubidubi.util;

import org.springframework.util.DigestUtils;

import java.util.Arrays;

public class VerifyTokenUtil {
    private  String TOKEN="xiaoyuanfuwuxiong";

    public boolean checkSignature(String signature,String timestamp,String nonce){
        String[] str = { TOKEN, timestamp, nonce };
        Arrays.sort(str); // 字典序排序
        String bigStr = str[0] + str[1] + str[2];
        String digest = org.apache.commons.codec.digest.DigestUtils.sha1Hex(bigStr);
        if (digest.equals(signature)){
                return true;
        }
        return false;
    }
}
