package tacos.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import tacos.Order;

@Slf4j
@Controller
@RequestMapping("/orders") // /orders 경로의 요청을 이 컨트롤러의 요청 처리 메서드가 처리한다는 것을 알려주는 것이 클래스 수준의 @RequestMapping 어노테이션이다.
public class OrderController {
	// /Orders/current 경로의 HTTP GET 요청을 orderForm() 메서드가 처리한다는 것을 알려준다.
	@GetMapping("/current")
	public String orderForm(Model model) {
		model.addAttribute("order", new Order());
		return "orderForm";
	}
	
	// 제출된 주문을 처리하기 위해 processOrder()메서드가 호출될 때는 제출된 폼 필드와 바인딩된 속성을 갖는 Order 객체가 인자로 전달된다.
	@PostMapping
	public String processOrder(@Valid Order order, Errors errors) {
		if(errors.hasErrors()) {
			return "orderForm";
		}
		
		log.info("Order submitted: " + order);
		return "redirect:/";
	}
}
