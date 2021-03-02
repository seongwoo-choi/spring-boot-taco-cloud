package tacos;


import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

//Taco 처럼 Order는 주문 정보를 갖는 간단한 클래스다.  -> like a DAO
@Data
public class Order {
	
	// 모든 유효성 검사 어노테이션은 message 속성을 갖고 있다.
	
	@NotBlank(message = "Name is required")
	private String deliveryName;
	
	@NotBlank(message = "Street is required")
	private String deliveryStreet;
	
	@NotBlank(message = "City is required")
	private String deliveryCity;
	
	@NotBlank(message = "State is required")
	private String deliveryState;
	
	@NotBlank(message = "Zip code is required")
	private String deliveryZip;
	
	// 이 어노테이션은 속성의 값이 Luhn(룬) 알고리즘 검사에 합격한 유효한 신용 카드 번호이어야 한다는 것을 선언한다.
	// 이 알고리즘 검사는 사용자의 입력 실수나 고의적인 악성 데이터를 방지해 준다. 그러나 입력된 신옹 카드 번호가 실제로 존재하는 것인지, 또는 대금 지불에 사용될 수 있는 것인지는 알 수 없다.
	@CreditCardNumber(message = "Not a valid credit card number")
	private String ccNumber;
	
	// 이 어노테이션을 이용하여 정규 표현식을 지정했고 아래와 같은 패턴을 따르는지 확인하였다.
	@Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
			message = "Must be formatted MM/YY")
	private String ccExpiration;
	
	// 이 어노테이션을 이용하면 입력 값이 정확하게 세자리 숫자인지 검사한다.
	@Digits(integer=3, fraction=0, message="Invalid CVV")
	private String ccCVV;
}
