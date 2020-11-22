package com.personal.kakaoLogin.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.personal.kakaoLogin.service.KakaoAPI;

@Controller
public class HomeController {
	
    @Autowired
    private KakaoAPI kakao;
	
    @RequestMapping(value="/")
    public String index() {
        
        return "index";
    }
    
    @RequestMapping(value="/login")
    public String login(@RequestParam("code") String code, HttpSession session) {
        String access_Token = kakao.getAccessToken(code);
        HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
        System.out.println("login Controller : " + userInfo);
        
        //    클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
        if (userInfo.get("email") != null) {
            session.setAttribute("userId", userInfo.get("email"));
            session.setAttribute("access_Token", access_Token);
        }
        return "index";
    }

    @RequestMapping(value="/logout")
    public String logout(HttpSession session) {
        kakao.kakaoLogout((String)session.getAttribute("access_Token"));
        session.removeAttribute("access_Token");
        session.removeAttribute("userId");
        return "index";
    }
    
    @RequestMapping(value="/testLogin")
    public String naver(HttpSession session)
    {
        return "index";
    }

    @RequestMapping(value="/callback")
    public String callback(HttpServletRequest request)throws Exception
    {
        return "callback";
    }
    
    @RequestMapping(value="/personalInfo")
    public void personalInfo(HttpServletRequest request) throws Exception{
    	String token = "AAAAO0RfCVS-hyN7yT8T3MvXfp40XhtJXVZEpbbGywb7i7QJ-eZpQzaes6H77Met1Oh754XCB_ZIUV_SUD5HrRwYJlI";
    	String header = "Bearer" + token;
    	
    	try {
    		String apiURL = "https://openapi.naver.com/v1/nid/me";
    		URL url = new URL(apiURL);
    		HttpURLConnection con = (HttpURLConnection)url.openConnection();
    		con.setRequestMethod("GET");
    		con.setRequestProperty("Authorization", header);
    		int responseCode = con.getResponseCode();
    		BufferedReader br;
    		if(responseCode==200) {
    			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
    		}
    		else {
    			br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
    		}
    		String inputLine;
    		StringBuffer response = new StringBuffer();
    		while((inputLine = br.readLine()) != null) {
    			response.append(inputLine);
    		}
    		br.close();
    		System.out.println(response.toString());
    	}
    	catch(Exception e) {
    		System.out.println(e);
    	}
    }
}