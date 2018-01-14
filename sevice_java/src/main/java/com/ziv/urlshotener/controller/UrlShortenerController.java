package com.ziv.urlshotener.controller;

import com.ziv.urlshotener.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Map;

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
        System.out.println(String.format("转换长url为短url：'%s' <- '%s'", shortUrl, longUrl));
        return shortUrl;
    }

    @RequestMapping(value = "/{shortCode}", method = RequestMethod.GET)
    public String toLong(HttpServletRequest request, @PathVariable String shortCode) {
        if (null == shortCode || shortCode.isEmpty()) {
            return null;
        }

        String fromIp = request.getRemoteAddr();
        return urlShortenerService.short2long(shortCode, fromIp);
    }
}
