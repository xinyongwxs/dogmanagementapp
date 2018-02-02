package com.ws.dog.management.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;

import javax.servlet.http.Cookie;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ws.dog.management.constants.CookieConstants;
import com.ws.dog.management.services.impl.JwtService;

import io.jsonwebtoken.Claims;

public class UserInfoValidationInterceptor extends HandlerInterceptorAdapter {
	private static final Logger log = LoggerFactory.getLogger(UserInfoValidationInterceptor.class);
    @Autowired
    private JwtService jwtService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		boolean success = false;
		String accessToken = this.getAccessToken(request);
		if (StringUtils.isNotBlank(accessToken)) {
			success = this.validateUserInfoFromAccessToken(accessToken);
			if (!success) {
				log.info("Invalid token found, clear client cookie.");
				Cookie invalidCookie = new Cookie(CookieConstants.ACCESS_TOKEN, null);
				invalidCookie.setMaxAge(0);
				invalidCookie.setPath("/");
				response.addCookie(invalidCookie);
			}
		}
		
		if (!success) {
			log.warn("Missing tenant info in request, return 401");
		    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		    return false;
		}
		
		return success;
	}
	
	private String getAccessToken(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		String token = null;
		if (cookies != null) {
			for (Cookie cookie: cookies) {
				if (cookie.getName().equals(CookieConstants.ACCESS_TOKEN)) {
					token = cookie.getValue();
					break;
				}
			}
		}
		
		return token;
	}
	
	private boolean validateUserInfoFromAccessToken(String accessToken) {
		Claims claims = jwtService.parseToken(accessToken);
		if (claims != null) {
			if (claims.getExpiration().before(new Date())) {
				return false;
			}
			return true;
		} else {
			return false;
		}
	}
}
