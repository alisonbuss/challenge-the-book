package com.project.service.webapi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
@DisplayName("Applying test in the class: Application")
public class ApplicationTests {

	@BeforeAll
	@DisplayName("@BeforeAll has to be a static method and it will run once before any test is run.")
	public static void onBeforeAll() {
		System.out.println("Executed once before any test runs...");
	}

	@BeforeEach
	@DisplayName("@BeforeEach will run once before every test method annotated with @Test.")
	public void doBeforeEach() {
	    System.out.println("Executed once before each test method annotated with @Test...");
	}

	@AfterAll
	@DisplayName("Similar to @BeforeAll, but it runs after all the tests are run.")
	public static void doAfterAll() {
	    System.out.println("Executed after running all tests...");
	}

	@AfterEach
	@DisplayName("This will run once after each test is run.")
	public void doAfterEach() {
	    System.out.println("Executed once after each test run...");
	}

	@Test
	@DisplayName("Testing the execution of bootstrap to Spring Boot:")
	public void testMain() {
		Application.main(new String[] {});
	}

}
