package example;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import example.dao.JdbcSingerDao;
import example.dao.SingerDao;

@Configuration
@Import(BasicDataSourceCfg.class)
public class SpringDataSourceCfg 
{
	@Autowired
	DataSource dataSource;
	
	@Bean
	SingerDao singerDao()
	{
		JdbcSingerDao dao = new JdbcSingerDao();
		dao.setDataSource(dataSource);
		return dao;
	}

}
