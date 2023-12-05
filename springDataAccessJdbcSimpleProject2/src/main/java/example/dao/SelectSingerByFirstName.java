package example.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

import example.model.Singer;

@Component
public class SelectSingerByFirstName extends MappingSqlQuery<Singer>
{
	static private String sqlQuery = "select id, first_name, last_name, birth_date from singer where first_name = :first_name";
	public SelectSingerByFirstName(DataSource dataSource)
	{
		super(dataSource, sqlQuery);
		super.declareParameter(new SqlParameter("first_name", Types.VARCHAR));
	}
	
	@Override
	protected Singer mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		return new Singer(
				rs.getLong("id"),
				rs.getString("first_name"),
				rs.getString("last_name"),
				rs.getDate("birth_date").toLocalDate(),
				Set.of());
	}
}
