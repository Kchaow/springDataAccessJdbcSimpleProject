package example.dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;

public class JdbcSingerDao implements SingerDao, InitializingBean
{
	private static Logger logger = LoggerFactory.getLogger(JdbcSingerDao.class);
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
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
		var result = "";
		try (var connection = dataSource.getConnection();
			 var statement = connection.prepareStatement("SELECT first_name, last_name FROM singer WHERE id=" + id);
			 var resultSet = statement.executeQuery())
		{
			while (resultSet.next())
			{
				return resultSet.getString("first_name") + " " + resultSet
						.getString("last_name");
			}
		}
		catch (SQLException ex)
		{
			logger.error("Problem when executing SELECT!", ex);
		}
		return result;
	}
}
