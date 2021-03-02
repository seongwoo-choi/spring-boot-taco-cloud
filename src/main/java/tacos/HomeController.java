package tacos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 컨트롤러는 HTTP 요청을 처리하고, 브라우저에 보여줄 HTML을 뷰에 요청하거나 또는 REST 형태의 응답 몸체에 직접 데이터를 추가한다.
// 여기서는 웹브라우저의 콘텐츠를 생성하기 위해 뷰를 사용하는 컨트롤러에 초점을 둘 것이다.
@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "home";
	}
}
