package tacos.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.validation.Errors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import tacos.Taco;
import tacos.Ingredient;
import tacos.Ingredient.Type;

@Slf4j // Lombok에 제공되며, 이 클래스에 자동으로 Simple Logging Facade, https://slf4j.org/ Logger를 생성한다.
@Controller
@RequestMapping("/design") // design 으로 시작하는 경로의 요청을 처리함을 나타낸다.
public class DesignTacoController {

	@GetMapping
	  public String showDesignForm(Model model) {
	    List<Ingredient> ingredients = Arrays.asList( // 식자재를 나타내는 Ingredient 객체를 저장하는 List를 생성한다.
	      new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
	      new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
	      new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
	      new Ingredient("CARN", "Carnitas", Type.PROTEIN),
	      new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
	      new Ingredient("LETC", "Lettuce", Type.VEGGIES),
	      new Ingredient("CHED", "Cheddar", Type.CHEESE),
	      new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
	      new Ingredient("SLSA", "Salsa", Type.SAUCE),
	      new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
	    ); 

	    // 식자재의 유형(고기, 치즈, 소스 등)을 List에서 필터링(fillterByType 메서드)한 후 showDesignForm()의 인자로 전달되는 Model 객체의 속성으로 추가한다.
	    // 모델은 컨트롤러와 데이터를 보여주는 뷰 사이에서 데이터를 운반하는 객체이다.
	    Type[] types = Ingredient.Type.values();
	    for (Type type : types) {
	      model.addAttribute(type.toString().toLowerCase(),
	          filterByType(ingredients, type));
	    }

	    // Model 객체의 속성에 있는 데이터는 뷰가 알 수 있는 서블릿 요청 속성들로 복사된다.
	    model.addAttribute("taco", new Taco());

	    // design을 반환하는데 이것은 모델 데이터를 브라우저에 나타내는 데 사용될 뷰의 논리적인 이름이다.
	    return "design";
	  }
	
	  private List<Ingredient> filterByType(
	      List<Ingredient> ingredients, Type type) {
	    return ingredients
	              .stream()
	              .filter(x -> x.getType().equals(type))
	              .collect(Collectors.toList());
	  }
	  
	  @PostMapping // design 경로의 POST 요청을 처리함을 나타낸다. 따라서 타코를 디자인하는 사용자가 제출한 것을 여기에서 처리해야 한다.
	  public String processDesign(@Valid Taco design, Errors errors) { 
		  // Valid 어노테이션은 제출된 Taco 객체의 유효성 검사를 수행(제출된 폼 데이터와 Taco 객체가 바인딩된 후, 그리고 processDesign() 메서드의 코드가 실행되기 전에)하라고 스프링 MVC에게 알려준다.
		  // 만일 어떤 검사 에러라도 있으면 에러의 상세 내역이 Errors 객체에 저장되어 processDesign() 으로 전달된다.
		  // processDesign()의 처음 세 줄의 코드에서는 Errors 객체의 hasErrors() 메서드를 호출하여 검사 에러가 있는지 확인한다.
		  if(errors.hasErrors()) {
			  return "design";
		  }
		  
		  // 이 지점에서 타코 디자인(선택된 식자재 내역)을 저장한다.
		  log.info("Processing design: "+design);
		  
		  return "redirect:/orders/current";
	  }
}
