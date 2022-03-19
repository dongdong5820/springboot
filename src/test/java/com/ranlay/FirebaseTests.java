package com.ranlay;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.ranlay.core.utils.FireBaseUtil;
import com.ranlay.enums.PushMsgEnum;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author: Ranlay.su
 * @date: 2021-11-02 11:57
 * @description: google_firebase消息推送测试
 * @version: 1.0.0
 */
@SpringBootTest
public class FirebaseTests {
    private static final Logger log = LoggerFactory.getLogger(FirebaseTests.class);

    /**
     * 项目名称
     */
    private static String appName = "community";
    /**
     * path路径
     */
    private static String jsonPath = "D://firebase-adminsdk.json";
    /**
     * dataUrl
     */
    private static String dataUrl = "https://oneplus-community.firebaseio.com";
    /**
     * firebase私钥配置
     */
    private static String configJson = "{\"type\":\"service_account\",\"project_id\":\"oneplus-community\",\"private_key_id\":\"fe23868be115bb21afd3458c990ea82f8d3d7d49\",\"private_key\":\"-----BEGIN PRIVATE KEY-----\\nMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDFjb5wS/m9Ibf0\\nSzy5qxnrve38C8Ed6Uz7Y7vvVSkaFFsWl22b5h/l+M5rbuR+CSCkh3vhF02vcHs3\\n2bV5N4n3L3klnBMvfWIqM6gvjJMz+ZyICj7q2XqlQUGGRrZlib7H5st3DC1RzXKS\\nb0DNzoXpTapbzutSBIVOo2Im23VZpOhHQeYujMfuDhi4dvr4XDA5Eg/myX85Xe2k\\nmJbU7KBTACCOt3hRnbKobF8nP08tmvkuoXkfa6c2uwDsD54VMKz/5vzTZ+5seniW\\n0El7Qi7gh32/4vMBg9B4XEUsX5LkC9vq2pr0vUtoqhlVLdEmKYn0jgzMwS39jQdo\\n4VpeXKE3AgMBAAECggEAXUDDnI7AnJ7GDvNU/u/dlIJbTJbf2ha+2AwEMr2oYT8q\\noEdq3iSK+iuwhNYb1cLJPeEWgPJXIXGfOIX0O/wTjDNEpJRAGjK9m942jft1oRs6\\n4Jy1hnaShl9/ZrsKbnPPMt2nPdLpsauhYa5SkNPkMnUO7beHLXgk1TIaquz9QAra\\nBrlBzSg4RTvNjcyEQjqCCTVnrxSdLXKPxQ4xiS2b2EvRqb050ZXwISTndi+yh7XP\\ncAFSRx6urFoYYuCi/Ce/zX44CN/Yo9LXBY9TTinGlQHPfvKLVlzeFRckcuA/eD7I\\n7gJAxM5e7sNp1ZWNikqh79Jr06euNze+LYzNzypcgQKBgQD+n0mBijiLagnTF+S4\\nEQJd2Gn6G8lbyc85aIoHBDO8wvnhnR79e0zTiKqkydVSgb0fIKAC+tr+FLSw3JiH\\nW5GSNNzXhDbyl/EpOWhicgoL6/geJYtF4iSZshvDyTAojU9cHRg52/PYid3S3fmV\\njetBl0hUfxNsGq+M966OSnit3wKBgQDGn2c1pvXa0uURcotcAR0DiMd1BPUMLp3B\\nNpQ7z6uKeR8fDhJCtVmHnRmjXhU+WQvNbaj/cME0VDLkpBQRyVrRh/6DyTN8oJsY\\nN0mkMY9ZtrmSklC0y1xlj/1uTgc2PAFnqrZoMCm9kad2YxV9VXHiddz1nyS0lsAs\\nNvufsKdHqQKBgEaLAQV3svTFGxW13/MzGzG+3JWjjyT0A4qHMP6Cpobcifh83HIj\\n8AQHMCfZl1V1hl0SX+KTd4q/V9RMOaH+t5UHOkwnYEXT3MesFQR5TdlBtgs8IZrp\\n7XP7iX+zxLjndZ4ynVyiWKucfq2GPDi4Cf6xGCP41Pu1ZP+sO1tGvKqHAoGAOxPM\\nmgu4EscptR/RzaLTI2r6kJbR4Da5W2/FdWjWiBBwrkb8LXVNVOXoa0wfm3TW6tpU\\nKu45uTvWC2W2RngSmEIP9NjShJ4so25GbB0Fm1SbK7pzYOMf72F9quJwiLQeYtxG\\n9wRkVetN2Qutp6LThd0yxKf6PfCc5knQW/YqO0kCgYAUCTBILIQKeRtfkTEioOaK\\nH7DozPqd7UrYJbGc5i0waVUx6s40W8ULvr8M5+b1Q8YUXbCkmXPsfvPBl7Smz4DD\\nTAe2lbp+TeO5jfIvw9z6TyUpK4EKFMaSp3lUyV/Zanp0N2BxKG49dqucZrFRONM8\\n8RJ77lyox1WfBJjdYYdfMw==\\n-----END PRIVATE KEY-----\\n\",\"client_email\":\"firebase-adminsdk-1lpub@oneplus-community.iam.gserviceaccount.com\",\"client_id\":\"103182851820016018050\",\"auth_uri\":\"https://accounts.google.com/o/oauth2/auth\",\"token_uri\":\"https://oauth2.googleapis.com/token\",\"auth_provider_x509_cert_url\":\"https://www.googleapis.com/oauth2/v1/certs\",\"client_x509_cert_url\":\"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-1lpub%40oneplus-community.iam.gserviceaccount.com\"}";

    /**
     * firebaseApp初始化
     */
    private void initFirebaseSDK() {
        if (!FireBaseUtil.isInit(appName)) {
            try {
//                FireBaseUtil.initSDKFromPath(jsonPath, dataUrl, appName);
                FireBaseUtil.initSDKFromConfig(configJson, dataUrl, appName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testPushSingle() {
        initFirebaseSDK();
        log.info("firebaseApp已经初始化了");
        String token = "fQkClKq3S8SbFVcWYRPVKI:APA91bE_OxrF-WtWayeLOtba6XxaGP1Q0gV48y69lAQ9h5gsJccSQaZ9xO2BfnlR2ctmWbMEBaxKGD55wmTNeo2dEv0WVddhiuTV2Q4B2Y0DafoC6fN9QhS7e5aAKAgHsn_o4hZXD9Uh";
        FireBaseUtil.pushSingle(appName, token, PushMsgEnum.PushConfig.NEW_FOLLOWER);
    }

    @Test
    public void testSendMulticast() {
        initFirebaseSDK();
        log.info("firebaseApp已经初始化了");
        List<String> tokenList = Arrays.asList(
                "d220mWb-Tx-Axj2E9_rSD0:APA91bFBnB3QAQfC42j_vl_WPFERj3m3Exx8IYwo7XVU4Bf83vAkW4kHe-Xlu5jeFviJeZkZXiU1TvFSySxngnGGIgRAc0BUWbINrqWUejg-ap5pBSpVPVCU2WQdSsP8Z8earNCxS1se",
                "adfsasdgfaywerywrtywrt",
                "ePsIp0x4T7-rCNhuXzcBDS:APA91bHZhtBT32mlFMB_r3xxAG13ZFlCaKz9U6Li7ziF-t7uabxbRkf0xT2OQ5iac3aRlX5_2EL-29KDWMkZJKsS-dKcPsPNFTQXWPX3W94wP0g4e54TKNhSweQ-B9XVN79BqLbfCc1J",
                "asdfhetyiuryuityuoitu",
                "fsdgsdfyertytreytr",
                "tqwertw1e6t5r4we6tr465"
        );
        String title = "Survey Time! Share your favorite OnePlus memories";
        String content = "we've started the behind-the-scenes work to prepare the 8th Anniversary celebrations.";
        FireBaseUtil.sendMulticast(appName, tokenList, title, content);
    }
}
