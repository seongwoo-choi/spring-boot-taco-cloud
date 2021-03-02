package tacos;


import java.util.List;

import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NonNull;

//타코 디자인을 정의하는 도메인 객체
@Data
public class Taco {

	// 유효성 검사 규칙 선언하기 
	// name 속성에는 값이 null 아이어야 한다는 규
	// 최소 크기는 5글자이고 이 이하가 오면 아래 message가 출력된다.
	@NonNull
	@Size(min=5, message="Name must be at least 5 chracters long")
	private String name;
	
	@Size(min=1, message="You must choose at least 1 ingredient")
	private List<String> ingredients;
}
