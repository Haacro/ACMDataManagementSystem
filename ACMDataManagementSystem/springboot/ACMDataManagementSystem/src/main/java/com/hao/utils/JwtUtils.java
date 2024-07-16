package com.hao.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static String signKey = "acmsystem"; //签名密钥
    private static Long expire = 43200000L; //过期时间为12小时

    /**
     * 生成JWT令牌
     *
     * @param claims JWT第二部分负载 payload 中存储的内容
     * @return
     */
    public static String generateJwt(Map<String, Object> claims) {
        String jwt = Jwts.builder() //链式编程
                .addClaims(claims) //自定义内容（载荷）
                .signWith(SignatureAlgorithm.HS256, signKey) //数字签名的算法
                .setExpiration(new Date(System.currentTimeMillis() + expire)) //设置有效期
                .compact(); //拿到jwt令牌
        return jwt;
    }

    /**
     * 解析JWT令牌
     *
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(signKey) //指定签名密钥
                .parseClaimsJws(jwt) //解析jwt令牌
                .getBody(); //拿到自定义内容
        return claims;
    }
}