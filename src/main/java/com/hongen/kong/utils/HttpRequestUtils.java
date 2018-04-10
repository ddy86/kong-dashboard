package com.hongen.kong.utils;

import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ddy on 2018/4/10.
 */
public class HttpRequestUtils {
    protected static final Logger logger = LoggerFactory.getLogger(HttpRequestUtils.class);
    public static String post(String url,String json){
        logger.info("post url : {}, json:{}",url,json);
        String result = null;
        try {
            result = HttpClientUtil.post(
                    HttpConfig.custom().url(url).json(json)
                            .headers(new Header[]{new BasicHeader("Content-Type", "application/json")})
            );
        } catch (HttpProcessException e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    public static String patch(String url,String json){
        logger.info("patch url : {}, json:{}",url,json);
        String result = null;
        try {
            result = HttpClientUtil.patch(
                    HttpConfig.custom().url(url).json(json)
                            .headers(new Header[]{new BasicHeader("Content-Type", "application/json")})
            );
        } catch (HttpProcessException e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    public static String get(String url){
        logger.info("get url : {}",url);
        String result = null;
        try {
            result = HttpClientUtil.get(
                    HttpConfig.custom().url(url)
            );
        } catch (HttpProcessException e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    public static String delete(String url){
        logger.info("delete url : {}",url);
        String result = null;
        try {
            result = HttpClientUtil.delete(
                    HttpConfig.custom().url(url)
            );
        } catch (HttpProcessException e) {
            logger.error(e.getMessage());
        }
        return result;
    }
}
