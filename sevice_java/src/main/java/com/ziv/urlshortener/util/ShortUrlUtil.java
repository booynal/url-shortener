package com.ziv.urlshortener.util;

import com.ziv.urlshortener.constant.UrlShortenerConstant;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 包名称:  com.ziv.urlshortener.util
 * 类描述:  短URL生成于解析工具
 * 创建人:  张黄江
 * 创建时间: 2018/4/14 22:59
 * 修改人:
 * 修改时间:
 * 修改备注:
 */
public class ShortUrlUtil {

	public static String generateShortUrl(@RequestHeader("host") String host, String protocol, String contextPath, String shortCode) {
		return String.format("%s://%s%s/%s%s", protocol, host, contextPath, UrlShortenerConstant.SHORT_CODE_PREFIX, shortCode);
	}

	public static String parseShortCode(@RequestParam("q") String shortUrl) {
		String shortCode = null;
		Pattern pattern = Pattern.compile("https?://.*/" + UrlShortenerConstant.SHORT_CODE_PREFIX + "([A-Za-z0-9])");
		Matcher matcher = pattern.matcher(shortUrl);
		if (matcher.find()) {
			shortCode = matcher.group(1);
		}
		return shortCode;
	}
}
