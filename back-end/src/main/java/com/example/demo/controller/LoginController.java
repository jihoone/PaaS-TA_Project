package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.JwtService;
import com.example.demo.service.KakaoService;
import com.example.demo.service.NaverService;
import com.example.demo.service.UserService;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/*
    Kakao와 Naver API를 사용해 로그인 구현

    Kakao 로그인 : Kakao 로그인 시 Application에서 Kakao Server로 인증 코드 요청을 하고 Kakao Server는 인증코드를 전달해준다.
                  받은 인증 코드를 가지고 토큰을 요청하고 Kakao Server에서 토큰을 전달 받는다.
                  App에서 토큰을 통해 API를 호출하고 Server에서 응답을 전달 받는다.

                  로그인을 실행하면 사용자 정보 및 기능 활용 동의를 받게 되는데,
                  이때 사용자의 프로필 정보(필수 동의)와 카카오계정 이메일(선택 동의)을 동의 받게 된다.
                  로그인이 완료된 후 Access token을 통해 받은 사용자의 고유 아이디와 이메일은 DataBase의 User table에 저장된다.

                  로그인 버튼 클릭시 작성된 Application의 REST API 키와 설정해둔 Redirect URL을 통해 kcallback된다.

                  로그아웃 버튼 클릭시 사용자의 토큰을 만료시켜 해당 사용자 정보로 Kakao API를 호출할 수 없도록 한다.


    Naver 로그인 : Naver 로그인 시 사용자 정보 제공에 동의하는 화면으로 이동하게 되고, 동의 완료 시 callback URL로 application에
                  접근 토큰(Access token)이 발급된다. 또한 발급된 토큰을 활용하여 사용자 정보를 얻어 온다.

    첫 회원가입   : 코로나 감염에 대한 위험성 알람이 사용자의 핸드폰 문자로 알림이 보내지므로 이때 휴대폰 번호가 필요하다.
                  그래서 로그인이 완료된 후 DataBase에서 아이디를 찾아보고 휴대폰 번호가 null일때 핸드폰 번호를 입력 받게 된다.
                  입력 받은 핸드폰 번호는 사용자의 아이디의 핸드폰번호로 DataBase에 저장된다.
                  만약 이미 DataBase에 번호가 입력이 되어 있다면 QR코드 선택 화면으로 넘어가게 된다.

    로그인 완료 : Kakao나 Naver롤 통해 로그인이 완료되면 QR코드를 선택할 수 있는 창으로 넘어가게 된다.
 */

@Controller
//@RequestMapping("/")
public class LoginController {

    @Autowired
    private NaverService naverService;

    @Autowired
    private KakaoService kakaoService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/kakao")
    public String kakao()
    {
        return "kakao";
    }

    @GetMapping("/kcallback")
    public RedirectView kcallback(@RequestParam("code") String code, HttpServletResponse res,HttpSession session) {
        String access_Token = kakaoService.getAccessToken(code);
        System.out.println("controller access_token : " + access_Token);

        Map<String,Object> resultMap=new HashMap<>();
        HashMap<String ,String > map=new HashMap<>();
        String social="kakao";

        String url="http://localhost:3000/qrcheckin?id=";

        HashMap<String, Object> userInfo = kakaoService.getUserInfo(access_Token);
        System.out.println("login Controller : " + userInfo);
        HttpStatus status=null;

        String id=userInfo.get("id").toString();
        String email=userInfo.get("email").toString();

        if (userInfo.get("userId") != null) {
            session.setAttribute("userId", userInfo.get("nickname"));
            session.setAttribute("access_Token", access_Token);
        }

        map.put("id",id);
        map.put("email",email);
        map.put("social",social);

        User user=userService.signin(map);
        if(user.getPhone()==null)
        {
            String registerUrl = "http://localhost:3000/phoneregister?id=";
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl(registerUrl+id);
            return redirectView;
        }

        status=HttpStatus.ACCEPTED;

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(url+id);

        return redirectView;
    }

    @RequestMapping(value="/logout")
    public String logout(HttpSession session) {
        kakaoService.kakaoLogout((String)session.getAttribute("access_Token"));
        session.removeAttribute("access_Token");
        session.removeAttribute("userId");
        return "Login";
    }

    @GetMapping("naver")
    public String naver()
    {
        return "naver";
    }

    @GetMapping("/ncallback")
    public RedirectView ncallback(@RequestParam("code") String code,@RequestParam("state") String state,HttpServletResponse res) throws ParseException, UnsupportedEncodingException {

        String social="naver";
        String url="http://localhost:3000/qrcheckin?token=";
        RedirectView redirectView = new RedirectView();
            HashMap<String,Object> token_map=naverService.getToken(code,state);

//            if(token_map.get("status").equals(true))
//            {
            String response=token_map.get("res").toString();
            System.out.println("naver response"+response);

            JSONParser parser=new JSONParser();
            JSONObject obj_token=(JSONObject)parser.parse(response);
            String token=obj_token.get("access_token").toString();
            System.out.println("naver token"+token);

            String profile=naverService.getProfile(token);

            JsonParser jsonparser=new JsonParser();
            JsonElement element = jsonparser.parse(profile);

            JsonObject res_profile = element.getAsJsonObject().get("response").getAsJsonObject();

            String id=res_profile.getAsJsonObject().get("id").getAsString();
            String email=res_profile.getAsJsonObject().get("email").getAsString();

            HashMap<String,String> map=new HashMap<>();

            map.put("id",id);
            map.put("email",email);
            map.put("social",social);
            System.out.println(profile);

            User user=userService.signin(map);
            if(user.getPhone()==null){
                String registerUrl = "http://localhost:3000/phoneregister?id=";
                RedirectView redirectView2 = new RedirectView();
                redirectView2.setUrl(registerUrl+id);
                return redirectView2;
            }
//
//            String jwt_token=jwtService.create(user);
//            res.setHeader("jwt-auth-token",jwt_token);

        redirectView.setUrl(url+id);
        return redirectView;

    }
}
