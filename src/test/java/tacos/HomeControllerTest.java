package tacos;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(HomeController.class) //HomeController의 웹 페이지 테스트 
class HomeControllerTest {

	@Autowired
	private MockMvc mockMvc; // MockMvc를 주입(연결)한다.
	
	@Test
	void testHomePage() throws Exception{
		mockMvc.perform(get("/")) // GET /을 수행한다.
		
		.andExpect(status().isOk()) // HTTP 2000이 되어야 한다.
		
		.andExpect(view().name("home")) // home 뷰가 있어야 한다.
		
		.andExpect(content().string(
				containsString("Welcome to..."))); // 콘텐츠에 "Welcome to..."가 포함되어야 한다.
	}

}
