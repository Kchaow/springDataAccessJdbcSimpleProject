package example.dao;

import java.sql.SQLException;
import java.util.Set;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;

import example.model.Singer;

public class JdbcSingerDao implements SingerDao, InitializingBean
{
	private static Logger logger = LoggerFactory.getLogger(JdbcSingerDao.class);
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
		var jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception
	{
		if (dataSource == null)
			throw new BeanCreationException("Must set dataSource on SingerDao");
	}
	
	@Override
	public String findNameById(Long id)
	{
		return jdbcTemplate
				.queryForObject("SELECT CONCAT(first_name, ' ', last_name) FROM singer WHERE id = ?", String.class, id);
		
//		var result = "";
//		try (var connection = dataSource.getConnection();
//			 var statement = connection.prepareStatement("SELECT first_name, last_name FROM singer WHERE id=" + id);
//			 var resultSet = statement.executeQuery())
//		{
//			while (resultSet.next())
//			{
//				return resultSet.getString("first_name") + " " + resultSet
//						.getString("last_name");
//			}
//		}
//		catch (SQLException ex)
//		{
//			logger.error("Problem when executing SELECT!", ex);
//		}
//		return result;
	}

	@Override
	public Set<Singer> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
