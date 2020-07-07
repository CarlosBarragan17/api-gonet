package com.gonet.aeromexico.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gonet.aeromexico.api.controllers.MovieController;

@RunWith(SpringRunner.class)
@SpringBootTest
class ApiGonetAeromexicoApplicationTests {
	
	@Autowired
	private MovieController movieController;

	@Test
	void contextLoads() {
		assertThat(movieController).isNotNull();
	}

}
