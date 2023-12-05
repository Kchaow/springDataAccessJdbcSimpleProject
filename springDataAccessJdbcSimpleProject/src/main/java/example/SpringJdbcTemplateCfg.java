package example;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class SpringJdbcTemplateCfg {

	@Autowired
	DataSource dataSource;
	
	@Bean
	JdbcTemplate jdbcTemplate()
	{
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);
		return jdbcTemplate;
	}
	
	@Bean
	NamedParameterJdbcTemplate namedTemplate()
	{
		NamedParameterJdbcTemplate namedTemplate = new NamedParameterJdbcTemplate(dataSource);
		return namedTemplate;
	}

}
