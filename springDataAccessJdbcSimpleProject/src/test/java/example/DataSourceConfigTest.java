package example;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.TestPropertySource;

import example.dao.RowMapperDao;
import example.dao.SingerDao;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//@TestPropertySource("/jdbc.properties")
public class DataSourceConfigTest 
{
	private static Logger logger = LoggerFactory.getLogger(DataSourceConfigTest.class);
	
	@Test
	void testDataSource() throws SQLException
	{
		var ctx = new AnnotationConfigApplicationContext(SpringDataSourceCfg.class);
		var dataSource = ctx.getBean("dataSource", DataSource.class);
		assertNotNull(dataSource);
		testWorkDataSource(dataSource);
		var singerDao = ctx.getBean("singerDao", SingerDao.class);
		var rowMapperDao = ctx.getBean("rowMapperDao", RowMapperDao.class);
		assertEquals("Michael Jackson", singerDao.findNameById(10L));
		var singers = rowMapperDao.findAll();
		singers.forEach((x) -> logger.info(x.toString()));
		assertEquals(1, singers.size());
		ctx.close();
	}
	
	private void testWorkDataSource(DataSource dataSource) throws SQLException
	{
		try (var connection = dataSource.getConnection();
			 var statement = connection.prepareStatement("SELECT 1");
			 var resultSet = statement.executeQuery())
		{
			while(resultSet.next())
			{
				int mockVal = resultSet.getInt("1");
				assertEquals(1, mockVal);
			}
		}
		catch (Exception e)
		{
			logger.debug("Something unexpected happened.", e);
		}
	}
	
}
