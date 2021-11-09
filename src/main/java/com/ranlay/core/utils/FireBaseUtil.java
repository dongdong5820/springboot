package com.ranlay.core.utils;

import com.alibaba.druid.support.json.JSONUtils;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: Ranlay.su
 * @date: 2021-11-02 10:56
 * @description: google_firebase推送工具
 * @version: 1.0.0
 */
public class FireBaseUtil {
    private static final Logger log = LoggerFactory.getLogger(FireBaseUtil.class);

    //存放多个实例的Map
    private static Map<String, FirebaseApp> firebaseAppMap = new ConcurrentHashMap<>();

    /**
     * 判断SDK是否初始化
     * @param appName
     * @return
     */
    public static boolean isInit(String appName) {
        return firebaseAppMap.get(appName) != null;
    }

    /**
     * 初始化SDK
     * @param jsonPath      JSON路径
     * @param dataUrl       firebase数据库
     * @param appName       APP名字
     * @throws IOException
     */
    public static void initSDKFromPath(String jsonPath, String dataUrl, String appName) throws IOException {
        FileInputStream serviceAccount = new FileInputStream(jsonPath);
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(credentials)
                .setDatabaseUrl(dataUrl)
                .build();
        //初始化firebaseApp
        FirebaseApp firebaseApp = FirebaseApp.initializeApp(options);
        //存放
        firebaseAppMap.put(appName, firebaseApp);
    }

    /**
     * 初始化SDK
     * @param configJson    json格式配置
     * @param dataUrl       firebase数据库
     * @param appName       APP名字
     * @throws IOException
     */
    public static void initSDKFromConfig(String configJson, String dataUrl, String appName) throws IOException {
        InputStream serviceAccount = new ByteArrayInputStream(configJson.getBytes());
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(credentials)
                .setDatabaseUrl(dataUrl)
                .build();
        //初始化firebaseApp
        FirebaseApp firebaseApp = FirebaseApp.initializeApp(options);
        //存放
        firebaseAppMap.put(appName, firebaseApp);
    }

    /**
     * 获取push内容
     * @param title
     * @param content
     * @return
     */
    private static String getBody(String title, String content) {
        // 构建push消息内容
        Map<String, String> body = new HashMap<>(16);
        body.put("messageTitle", title);
        body.put("content", content);
//        body.put("image","https://static.oneplus.cn/data/attachment/avatar/202104/16/205638fs3zsbto4c9bcbmq.jpg");
        body.put("jumpType", "1");
        body.put("circleId", "4");
        body.put("articleId", "");
        body.put("connectId", "4");
        body.put("trackerId", "official_15");
        return JSONUtils.toJSONString(body);
    }

    /**
     * 单设备推送
     * @param appName      应用的名字
     * @param token        注册token
     * @param title        推送题目
     * @param content      推送内容
     * @return
     */
    public static void pushSingle(String appName, String token, String title, String content) {
        //获取实例
        FirebaseApp firebaseApp = firebaseAppMap.get(appName);
        //实例为空的情况
        if (firebaseApp == null) {
            return;
        }

        Map<String, String> failedTokenMap = new HashMap<>();
        String body = getBody(title, content);
        // 构建message
        Message message = Message.builder()
                .putData("type", "0")
                .putData("body", body)
                .setToken(token)
                .build();
        try {
            //发送后，返回messageID
            String response = FirebaseMessaging.getInstance(firebaseApp).send(message);
            System.out.println("推送成功: " + response);
        } catch (FirebaseMessagingException e) {
            failedTokenMap.put(token, String.format("[%s]%s", e.getErrorCode(), e.getMessage()));
        }
        System.out.println(String.format("单个设备推送结果, failedTokenMap: %s", failedTokenMap));
    }

    /**
     * 向多给设备发送消息
     * @param appName
     * @param tokenList
     * @param title
     * @param content
     */
    public static void sendMulticast(String appName, List<String> tokenList, String title, String content) {
        //获取实例
        FirebaseApp firebaseApp = firebaseAppMap.get(appName);
        //实例为空的情况
        if (firebaseApp == null) {
            return;
        }

        Map<String, String> failedTokenMap = new HashMap<>();
        String body = getBody(title, content);
        List<List<String>> listChunk = ListUtil.listChunk(tokenList, 3);
        listChunk.forEach(chunk -> {
            // 构建message
            MulticastMessage message = MulticastMessage.builder()
                    .putData("type", "0")
                    .putData("body", body)
                    .addAllTokens(chunk)
                    .build();
            try {
                BatchResponse batchResponse = FirebaseMessaging.getInstance(firebaseApp).sendMulticast(message);
                List<SendResponse> responses = batchResponse.getResponses();
                for (int i = 0; i < responses.size(); i++) {
                    SendResponse response = responses.get(i);
                    if (!response.isSuccessful()) {
                        FirebaseMessagingException fe = response.getException();
                        failedTokenMap.put(chunk.get(i), String.format("[%s]%s", fe.getErrorCode(), fe.getMessage()));
                        System.out.println(fe);
                    }
                }
            } catch (FirebaseMessagingException e) {
                log.error(String.format("push message error. token: %s", chunk), e);
            }
        });
        System.out.println(String.format("多个设备推送结果, failedTokenMap: %s", failedTokenMap));
    }
}
