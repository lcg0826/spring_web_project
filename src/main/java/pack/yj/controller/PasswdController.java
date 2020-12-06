package pack.yj.controller;

import java.io.File;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pack.lcg.model.GogekDto;
import pack.lcg.model.GogekInter;

@Controller
public class PasswdController {
	@Autowired
	@Qualifier("gogekImpl")
	private GogekInter gogekInter;
	
	@RequestMapping(value="/passwd",method=RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView view = new ModelAndView();
		
		view.setViewName("gogek/passwd");
		return view;
	}

	

//	public boolean sendPwd(GogekDto dto, @RequestParam("gogek_id") String id) {
//
//		boolean b = false;
//		GogekDto map = gogekInter.getMail_Pwd(id);
//		System.out.println(id);
//		if (map.getGogek_id() != null) {
//			b = true;
//			String setfrom = "dbswls1999@gmail.com";
//			String tomail = (String) map.getGogek_email(); // 받는 사람 이메일
//			String title = "BAEFLIX 비밀번호 찾기 메시지 입니다."; // 제목
//			// System.out.println(tomail);
//			MimeMessage message = mailSender.createMimeMessage();
//			System.out.println(message);
//			try {
//				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
//				messageHelper.setFrom(setfrom); // 보내는사람 생략하거나 하면 정상작동을 안함
//				messageHelper.setTo(tomail); // 받는사람 이메일
//				messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
//				String text = "고객님의 비밀번호는 " + (String) dto.getGogek_passwd() + "입니다.";
//				messageHelper.setText(text, true);
//				mailSender.send(message);
//				System.out.println("보내짐?");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//		}
//		return b;
//	}
	 @ResponseBody
	   @RequestMapping(value = "idCheck2", method =  RequestMethod.POST)
	   public Map<String, String> IdCheck(@RequestParam("gogek_id") String id){
	      Map<String, String> IdCheckResult = null;
	      IdCheckResult = new HashMap<String, String>(); 
	      
	      //	System.out.println(gogekInter.idCheck(id));
	      	
	         if(gogekInter.idCheck(id) > 0) {
	            IdCheckResult.put("idCheckResult", "true");
	            System.out.println(IdCheckResult);
	         }
	         else {
	            IdCheckResult.put("idCheckResult", "false");
	         }
	         return IdCheckResult;
	      
	   }
	@Autowired
	private JavaMailSender mailSender;

	@RequestMapping(value = "/passwd" , method=RequestMethod.POST) 
	public String sendMail(GogekDto dto, @RequestParam("gogek_id") String id) { 
		GogekDto map = gogekInter.getMail_Pwd(id);
		
		if (map.getGogek_id() != null) {
			String setfrom = "dbswls1999@gmail.com";
			String tomail = (String) map.getGogek_email(); // 받는 사람 이메일
			String title = "BAEFLIX 비밀번호 찾기 메시지 입니다."; 
		
		try { 
			MimeMessage message = mailSender.createMimeMessage(); 
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setTo(tomail); 
			String text = "<html><body><br><br><h1>고객님의 비밀번호는 " + (String)map.getGogek_passwd() + "입니다.</h1></body></html>";
			//messageHelper.setText(text); 
			messageHelper.setFrom(setfrom); 
			messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
			
			String contents =  "<img src=\'cid:logo.png\'>"; 
			messageHelper.setText(contents + text, true);
			FileSystemResource file = new FileSystemResource(new File("C:/work/logo.png")); 
			messageHelper.addInline("logo.png", file);

			
			mailSender.send(message);  
		}catch(Exception e){ 
			
				} 

		return "gogek/success";
			}else {
				return "gogek/error";
			}
		
	}
}
	


