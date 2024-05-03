package cr.ac.una.proyecto_progra4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestProyectoProgra4Application {

	public static void main(String[] args) {
		SpringApplication.from(ProyectoProgra4Application::main).with(TestProyectoProgra4Application.class).run(args);
	}

}
