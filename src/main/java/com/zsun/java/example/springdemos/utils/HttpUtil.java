package com.zsun.java.example.springdemos.utils;


import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * @author : zsun
 * @date : 2020/06/08 17:03
 */
public class HttpUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static final String CONTENT_TYPE_JSON_UTF8 = "application/json;charset=UTF-8";

    public static String get(String url, Map<String, String> headers) {
        logger.debug("get {} with param {}", url, headers);
        String result = "";
        try {
            URL urlObject = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
            connection.setRequestMethod("GET");

            headers.forEach((k, v) -> {
                connection.setRequestProperty(k, v);
            });
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                logger.warn("get url [{}] returns [{}], not 200", url, responseCode);
            }

            String line;
            StringBuffer sb = new StringBuffer();

            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
            }

            result = sb.toString();
        } catch (MalformedURLException e) {
            logger.error("create a url object failed: ", e);
            return result;
        } catch (IOException e) {
            logger.error("open url connection failed: ", e);
            return result;
        }

        return result;
    }

    public static String post(String url, Map<String, String> headers, JSONObject requestBody) {
        logger.debug("post {} with body {}", url, requestBody);
        String result = "";

        return result;
    }
}
