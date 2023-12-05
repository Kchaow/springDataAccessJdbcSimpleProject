package example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("example")
//@Configuration
public class Application 
{
	public static void main(String...args)
	{
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Application.class);
	}
}
	
