package example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class SpringDataAccessJdbcSimpleProjectApplication 
{
	public static void main(String[] args) 
	{
		//SpringApplication.run(SpringDataAccessJdbcSimpleProjectApplication.class, args);
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringDataAccessJdbcSimpleProjectApplication.class);
	}

}
