package org.ict.controller;

import java.util.ArrayList;

import org.ict.domain.BaseVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
// 기본 url(localhost:8181/ 뒤에 spring/모든패턴이 추가됨.
@RequestMapping("/spring/*")
public class SpringController {
	
	@RequestMapping("")
	public void base() {
		
		System.out.println("기본 url로 접속했습니다.");
	}
	
	// @RequestMapping은 여러 파라미터를 가짐.
	// value 파라미터는 필수이고, url 패턴을 기술함.
	// method 파라미터는 생략이 가능하고, 처리할 접속방식을 정의함.
	// return 자료형이 void인 메서드는, 주소.jsp 파일을 화면으로 자동 지정.
	// 경로는 기본주소 이후에 추가로 붙은 주소를 경로로 함.
	// 접속 url : localhost:8181/spring/base
	@RequestMapping(value="/base", method= { RequestMethod.GET, RequestMethod.POST })
	public void baseGet() {
		
		System.out.println("base get post");
	}
	
	// GetMapping은 오로지 get방식 접속만 정의할 때 사용.
	// 접속 url : localhost:8181/spring/baseGet
	@GetMapping("/baseGet")
	public void baseGet2() {
		
		System.out.println("base get!");
	}
	
	// PostMapping은 오로지 post방식 접속만 정의할 때 사용.
	@PostMapping("/basePost")
	public void basePost() {
		
		System.out.println("그냥 접속할 수 없는 post!");
	}
	
	@GetMapping("/letsGet")
	public void letsGet() {
		
		System.out.println("Get방식으로 호출");
	}
	
	// BaseVO의 멤버변수들을 파라미터로 처리할 수 있는 메서드
	// 메서드의 파라미터에 Class를 선언하면 Getter와 Setter가 존재 시
	// 컨트롤러 내부적으로 파라미터를 객체에게 전달할 수 있다.
	// 단, 이 경우는 클래스 내의 멤버변수와 파리미터 이름이 일치해야한다.
	// return타입이 String인 경우는 url 주소를 무시하고
	// 곧바로 리턴된 문자열.jsp를 views 폴더 하위에 배치해야 한다.
	@GetMapping("/vo")
	public String vo01(BaseVO vo) {
		
		System.out.println("" + vo);
		return "vo01";
	}
	
	// views가 아닌 더 하위 폴더에 담고 싶다면,
	// return에 폴더 경로를 함께 지정해줌.
	// 스프링에서는 Model이라는 객체를 이용해 컨트롤러의 데이터를
	// 뷰(.jsp)로 보내줌.
	// 메서드의 파라미터 선언부에 Model객체를 선언함.
	// 1. 메서드의 파라미터 선언부에 추가로 Model객체를 선언.
	// 2. model.addAttribute("보낼이름", "보낼자료"); 구문 작성
	// 3. .jsp에서는 ${보낸이름} 으로 처리 가능
	@GetMapping("/vo2")
	public String vo02(Model model, BaseVO vo) {
		
		System.out.println("" + vo);
		model.addAttribute("BaseVO", vo);
		return "spring/vo02";
	}
	
	// 참조형 변수는 사실 model.addAttribute를 사용하지 않아도
	// 자동으로 전달을 해줌.
	// 이 때에는 자료형의 맨 앞글자만 소문자로 바꿔서 자동 전달됨.
	// 반면 기본형 변수는 자동 전달이 이루어지지 않는데,
	// 이 때 model.addAttribute를 쓸 수도 있지만
	// 대신 @ModelAttribute를 써서 전달할 수 있음.
	// 파라미터 선언부에 기본형 자료 선언시 반드시 자료가 전달되어야 함.
	@GetMapping("/vo3")
	public String vo3(BaseVO vo,@ModelAttribute("num") int num, Model model) {
		
		System.out.println("비밀번호 : " + num);
//		model.addAttribute("memNum", num);
		return "spring/vo03";
	}
	
	// 특정 주소 접속시 redirect를 수행시키고 싶다면
	// return하는 문자열 앞에 redirect: 를 추가해줌.
	// url에서 가장 왼쪽에 적는 /는
	// 기본 주소(localhost:8181/)를 의미함.
	@GetMapping("/qwer")
	public String redirectTest() {
		System.out.println("/base로 redirect");
		return "redirect:/spring/";
	}
	
	// 파일 업로드 페이지로 연결해주는 메서드
	@GetMapping("/exUpload")
	public void exUpload() {
		System.out.println("/exupload....");
	}
	
	@PostMapping("/exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {
		files.forEach(file -> {
			System.out.println("--------------------");
			System.out.println("name: " + file.getOriginalFilename());
			System.out.println("size: " + file.getSize());
		});
	}
}
