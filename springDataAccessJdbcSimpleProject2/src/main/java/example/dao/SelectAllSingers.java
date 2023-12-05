package example.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

import example.model.Singer;

@Component
public class SelectAllSingers extends MappingSqlQuery<Singer>
{
	public SelectAllSingers(DataSource dataSource)
	{
		super(dataSource, "SELECT * FROM singer");
	}
	
	@Override
	protected Singer mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		return new Singer(rs.getLong("id"),
				rs.getString("first_name"),
				rs.getString("last_name"),
				rs.getDate("birth_date").toLocalDate(),
				Set.of());
	}
}
