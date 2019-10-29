package com.team.house.utils;
public class SmsUtil {
    //用户名
    private static String Uid = "kris_xq";

    //接口安全秘钥
    private static String Key = "d41d8cd98f00b204e980";


    /**
     * @param tel 手机号码，多个号码如13800000000,13800000001,13800000002
     * @param msg  短信息内容
     * @return
     */
    public static int  sendMsg(String tel,String msg) {
        HttpClientUtil client = HttpClientUtil.getInstance();
        //UTF发送
        int result = client.sendMsgUtf8(Uid, Key, msg, tel);
        return result;
    }

}
