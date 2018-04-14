package com.ziv.urlshortener.controller;

import com.ziv.urlshortener.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class UrlShortenerController {

	@Autowired
	private UrlShortenerService urlShortenerService;

	@PostMapping("post/to/short")
	public String toShort(HttpServletRequest request, @RequestBody String url) {
		System.out.println("url: " + url);
		try {
			String longUrl = URLDecoder.decode(url, Charset.defaultCharset().name()).replaceAll("=$", "");
			System.out.println("longUrl: " + longUrl);
			return toShort(request, request.getHeader("host"), longUrl);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/long/to/short")
	public String toShort(HttpServletRequest request, @RequestHeader("host") String host, @RequestParam("url") String longUrl) {
		if (null == longUrl || longUrl.isEmpty()) {
			System.err.println("empty longUrl");
			return null;
		}

		String protocol = request.getProtocol().split("/")[0].toLowerCase();
		String contextPath = null == request.getContextPath() ? "" : request.getContextPath();
		String shortCode = urlShortenerService.long2short(longUrl);
		String shortUrl = String.format("%s://%s%s/%s", protocol, host, contextPath, shortCode);
		System.out.println(String.format("转换长url为短url：'%s' -> '%s'", longUrl, shortUrl));
		return shortUrl;
	}

	@RequestMapping(value = "/s", method = RequestMethod.GET)
	public String queryLong(HttpServletRequest request, @RequestParam("q") String shortUrl) {
		if (null == shortUrl || shortUrl.isEmpty()) {
			return null;
		}

		System.out.println("查询长url");
		String shortCode = null;
		Pattern pattern = Pattern.compile("https?://.*/([A-Za-z0-9])");
		Matcher matcher = pattern.matcher(shortUrl);
		if (matcher.find()) {
			shortCode = matcher.group(1);
		}
		if (null != shortCode) {
			String longUrl = urlShortenerService.short2long(shortCode, request.getRemoteAddr());
			System.out.println(String.format("short to long: '%s' -> '%s'", shortCode, longUrl));
			return longUrl;
		}
		return null;
	}

	@RequestMapping(value = "/{shortCode}", method = RequestMethod.GET)
	public void toLong(HttpServletRequest request, HttpServletResponse response, @PathVariable String shortCode) {
		if (null == shortCode || shortCode.isEmpty()) {
			return;
		}

		try {
			String longUrl = urlShortenerService.short2long(shortCode, request.getRemoteAddr());
			System.out.println(String.format("short to long: '%s' -> '%s'", shortCode, longUrl));
			response.sendRedirect(longUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
