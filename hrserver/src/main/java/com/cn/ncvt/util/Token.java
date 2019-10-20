package com.cn.ncvt.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cn.ncvt.entity.User;
import org.apache.shiro.SecurityUtils;

import java.util.*;

public class Token {

    private static String key = "cchh103";

    /**
     * 签发自定义token
     */
    public String createTokenWithClaim(int userId, String userName) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(key);
            Map<String, Object> map = new HashMap<String, Object>();
            Date nowDate = new Date();
            Date expireDate = getAfterDate(nowDate, 0, 0, 7, 0, 0, 10);    //获取当前时间2小时后的时间
            map.put("alg", "HS256");
            map.put("typ", "JWT");
            String token = JWT.create()
                    .withHeader(map)    //设置头部信息 Header
                    .withClaim("userId", userId)    //设置 载荷 Payload
                    .withClaim("userName", userName)    //设置 载荷 Payload
                    .withIssuer("DickDragon")    //签名是有谁生成 例如 服务器
                    //.withNotBefore(new Date())    //定义在什么时间之前，该jwt都是不可用的.
                    .withAudience("APP")    //签名的观众 也可以理解谁接受签名的
                    .withIssuedAt(nowDate)     //生成签名的时间
                    .withExpiresAt(expireDate)    //签名过期的时间
                    .sign(algorithm);    //签名 Signature
            return token;
        } catch (JWTCreationException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     *  解析token
     * @param token  接收前台传来的token,进行验证
     */
    public static boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(key);
            JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("DickDragon")
                .build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> claims = jwt.getClaims();
            Claim claimUserId = claims.get("userId");    //负载内容
            Claim claimUserName = claims.get("userName");    //负载内容

            User user= (User) SecurityUtils.getSubject().getPrincipal();
            if(user.getId() == claimUserId.asInt() && user.getUsername().equals(claimUserName.asString())){
                return true;
            } else {
                return false;
            }

            /*System.out.println(claim.asString());
            List<String> audience = jwt.getAudience();    //接收token的用户
            System.out.println(jwt.getSubject());    //主题
            System.out.println(audience.get(0));*/
            //return true;
        } catch (JWTVerificationException exception){
            exception.printStackTrace();
            return false;
        }
    }

    /**
     * 返回一定时间后的日期
     *
     * @param date   开始计时的时间
     * @param year   增加的年
     * @param month  增加的月
     * @param day    增加的日
     * @param hour   增加的小时
     * @param minute 增加的分钟
     * @param second 增加的秒
     * @return
     */
    public Date getAfterDate(Date date, int year, int month, int day, int hour, int minute, int second) {
        if (date == null) {
            date = new Date();
        }

        Calendar cal = new GregorianCalendar();

        cal.setTime(date);
        if (year != 0) {
            cal.add(Calendar.YEAR, year);
        }
        if (month != 0) {
            cal.add(Calendar.MONTH, month);
        }
        if (day != 0) {
            cal.add(Calendar.DATE, day);
        }
        if (hour != 0) {
            cal.add(Calendar.HOUR_OF_DAY, hour);
        }
        if (minute != 0) {
            cal.add(Calendar.MINUTE, minute);
        }
        if (second != 0) {
            cal.add(Calendar.SECOND, second);
        }
        return cal.getTime();
    }
}
