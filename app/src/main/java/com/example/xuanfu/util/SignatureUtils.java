package com.example.xuanfu.util;

import android.content.Context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import Decoder.BASE64Decoder;

/**
 * @author wangmojiang
 */
public class SignatureUtils {
    /**
     * https://github.com/FasterXML/jackson
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 以私钥签名
     *
     * @param map 需要加签的源数据包
     * @return 完成签名的新数据包
     */
    @SuppressWarnings("unchecked")
    public static Map signWith(Map map, PrivateKey key) throws JsonProcessingException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        // 创建一个可更新的map
        HashMap nm = new HashMap(map);
        nm.put("timestamp", System.currentTimeMillis());

        // 重新排序，并且导出JSON
        String jsonText = objectMapper.writeValueAsString(new TreeMap(nm));
        byte[] data = jsonText.getBytes(Charset.forName("UTF-8"));

        Signature s = Signature.getInstance("SHA1withRSA");
        s.initSign(key);
        s.update(data);
        byte[] signResult = s.sign();

        nm.put("signature", Base64.getEncoder().encodeToString(signResult));
        return nm;
    }

    /**
     * 以公钥验签
     *
     * @param map 原数据
     * @return 是否可信任
     */
    @SuppressWarnings("unchecked")
    public static boolean verifySignature(Map map, PublicKey key) throws JsonProcessingException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        if (!map.containsKey("signature"))
            throw new IllegalStateException("缺少必要的签名");
        if (!map.containsKey("timestamp"))
            throw new IllegalStateException("缺少必要的签名");
        byte[] signature = Base64.getDecoder().decode(map.get("signature").toString());

        // 创建一个可更新map, 把签名移除
        HashMap nm = new HashMap(map);
        nm.remove("signature");

        // 重新排序
        String jsonText = objectMapper.writeValueAsString(new TreeMap(nm));
        byte[] data = jsonText.getBytes(Charset.forName("UTF-8"));

        Signature s = Signature.getInstance("SHA1withRSA");
        s.initVerify(key);
        s.update(data);
        return s.verify(signature);
    }

    /**
     * 从Pem获取私钥
     *
     * @param context
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKeyFromPem(Context context) throws Exception {
        InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open("private_key.pem"));
        BufferedReader br = new BufferedReader(inputReader);
        String s = br.readLine();
        String str = "";
        s = br.readLine();
        while (s.charAt(0) != '-') {
            str += s + "\r";
            s = br.readLine();
        }
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b = decoder.decodeBuffer(str);
        // 生成私匙
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(b);
        PrivateKey privateKey = kf.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * 从Pem获取公钥
     *
     * @param context
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKeyFromPem(Context context) throws Exception {
        InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open("public.pem"));
        BufferedReader br = new BufferedReader(inputReader);
        String s = br.readLine();
        String str = "";
        s = br.readLine();
        while (s.charAt(0) != '-') {
            str += s + "\r";
            s = br.readLine();
        }
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b = decoder.decodeBuffer(str);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(b);
        PublicKey pubKey = kf.generatePublic(keySpec);
        return pubKey;
    }
}