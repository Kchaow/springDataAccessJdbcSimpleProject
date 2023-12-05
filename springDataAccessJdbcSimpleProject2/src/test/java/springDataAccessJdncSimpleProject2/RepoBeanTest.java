package springDataAccessJdncSimpleProject2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import example.Application;
import example.BasicDataSourceCfg;
import example.dao.SingerDao;
import example.dao.SingerJdbcRepo;
import example.model.Album;
import example.model.Singer;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@Sql({"classpath:init.sql"})
@SpringJUnitConfig(classes = {Application.class, BasicDataSourceCfg.class})
public class RepoBeanTest 
{
	@Container
	static PostgreSQLContainer<?> postgreSql = new PostgreSQLContainer<>("postgres:16.1");
	
	@DynamicPropertySource
	static void setUp(DynamicPropertyRegistry registry)
	{
		registry.add("jdbc.driverClassName", postgreSql::getDriverClassName);
		registry.add("jdbc.url", postgreSql::getJdbcUrl);
		registry.add("jdbc.username", postgreSql::getUsername);
		registry.add("jdbc.password", postgreSql::getPassword);
	}
	
	@Autowired
	SingerJdbcRepo singerRepo;
	
	@Test
	void testFindAllWithMappingSqlQuery()
	{
		//AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Application.class);
		
		//SingerDao singerRepo = ctx.getBean("singerJdbcRepo", SingerDao.class);
		assertNotNull(singerRepo);
		
		Singer singer = new Singer(10L, "John Clayton", "Mayer",
				LocalDate.of(1977, 9, 16),
				Set.of());
		singerRepo.update(singer);
		List<Singer> singers = singerRepo.findByFirstName("John Clayton");
		assertEquals(1, singers.size());
		
		singer = new Singer(null,"Ed","Sheeran", LocalDate.of(1991, 1, 17), 
				Set.of());
		Long id = singerRepo.insert(singer);
		
		singer.setAlbums(new HashSet<>());
		Album album = new Album(null, null,"My Kind of Blues", LocalDate.of(1961, 7, 18));
				singer.getAlbums().add(album);
		album = new Album(null, null, "A Heart Full of Blues", LocalDate.of(1962, 3, 20));
				singer.getAlbums().add(album);
				
		singers = singerRepo.findByFirstName("Ed");
		assertEquals(1, singers.size());
		
//		String name = singerRepo.findFirstNameById(id).get();
//		assertNotNull(name);
		
//		ctx.close();
	}
}
