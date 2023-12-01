package example;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import example.dao.JdbcSingerDao;
import example.dao.RowMapperDao;
import example.dao.SingerDao;

@Configuration
@Import( {BasicDataSourceCfg.class, SpringJdbcTemplateCfg.class} )
public class SpringDataSourceCfg 
{
	@Autowired
	DataSource dataSource;
	@Autowired
	NamedParameterJdbcTemplate namedTemplate;
	
	@Bean
	SingerDao singerDao()
	{
		JdbcSingerDao dao = new JdbcSingerDao();
		dao.setDataSource(dataSource);
		return dao;
	}
	
	@Bean
	RowMapperDao rowMapperDao()
	{
		RowMapperDao rowMapperDao = new RowMapperDao();
		rowMapperDao.setNamedTemplate(namedTemplate);
		return rowMapperDao;
	}

}
