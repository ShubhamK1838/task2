package com.zestindia.t2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class Task2ApplicationTests {

	enum  names{
		ONE,TWO
	}
	@Test
	void contextLoads() {
		names nm=names.ONE;
		String first=nm.name();

		System.out.println(first);
	}

}
