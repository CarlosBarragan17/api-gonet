package com.gonet.aeromexico.api.utils;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Utility {
	
	private static final Logger logger = LoggerFactory.getLogger(Utility.class);
	
	public static String getUserFromToken(HttpServletRequest request) {
		String result = "";
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String[] token = request.getHeader("Authorization").replace("Bearer ", "").split("\\.");
			result = objectMapper.readTree(new String(Base64.getDecoder().decode(token[1]))).get("username").asText();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}

}
