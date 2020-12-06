package pack.yj.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.scribejava.core.model.OAuth2AccessToken;

import pack.lcg.controller.GogekBean;
import pack.lcg.model.GogekDto;
import pack.lcg.model.GogekInter;


/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController2 {
	@Autowired
	@Qualifier("gogekImpl")
	private GogekInter gogekInter;
	
	/* NaverLoginBO */
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;
	
	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}

	//로그인 첫 화면 요청 메소드
	@RequestMapping(value = "/login222", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(Model model, HttpSession session) {
		
		/* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		
		//https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
		//redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
		System.out.println("네이버:" + naverAuthUrl);
		
		//네이버 
		model.addAttribute("url", naverAuthUrl);

		/* 생성한 인증 URL을 View로 전달 */
		return "login";
	}

	//네이버 로그인 성공시 callback호출 메소드
	@RequestMapping(value = "/naverSuccess", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ModelAndView callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session)
			throws IOException, ParseException {
		System.out.println("여기는 callback");
		OAuth2AccessToken oauthToken;
        oauthToken = naverLoginBO.getAccessToken(session, code, state);
        //로그인 사용자 정보를 읽어온다.
	    apiResult = naverLoginBO.getUserProfile(oauthToken);
	    
	    JSONParser parser = new JSONParser();
	    Object obj = parser.parse(apiResult);
	    JSONObject jsonObj = (JSONObject) obj;
	    
	    //3. 데이터 파싱
	    //Top레벨 단계 _response 파싱
	    JSONObject response_obj = (JSONObject)jsonObj.get("response");
	    //response의 nickname값 파싱
	    String naver_id = (String)response_obj.get("id");
	    String naver_email = (String)response_obj.get("email");
	    GogekBean bean = new GogekBean();
	    bean.setGogek_id(naver_id);
	    bean.setGogek_email(naver_email);
	    
	    GogekDto dto = gogekInter.selectDataNaver(naver_id);
//	    System.out.println(id);
	    //4.파싱 닉네임 세션으로 저장
	    if(dto != null) {
	    	  session.setAttribute("gogek_id",naver_id); //세션 생성
	    	  
	    	session.setAttribute("image", dto.getGogek_image());
	  		model.addAttribute("result", apiResult);
//	  		session.setAttribute("naverid", apiResult);

	          /* 네이버 로그인 성공 페이지 View 호출 */
	  		return new ModelAndView("redirect:/index","result",apiResult);
	    }else {
	    	boolean b = gogekInter.insertDataNaver(bean);
	    	 session.setAttribute("gogek_id",naver_id); //세션 생성

	    		session.setAttribute("image", dto.getGogek_image());
		  		model.addAttribute("result", apiResult);
//		  		session.setAttribute("naverid", apiResult);

		          /* 네이버 로그인 성공 페이지 View 호출 */
		  		
		  		return new ModelAndView("redirect:/index","result",apiResult);
	    }
	  
	}
}



