package example.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import example.model.Singer;

public class RowMapperDao implements SingerDao
{
	private NamedParameterJdbcTemplate namedTemplate;
	
	public void setNamedTemplate(NamedParameterJdbcTemplate namedTemplate)
	{
		this.namedTemplate = namedTemplate;
	}
	
	@Override
	public Set<Singer> findAll()
	{
		return new HashSet<>(namedTemplate.query("SELECT * FROM singer", new SingerMapper()));
	}
	
	static class SingerMapper implements RowMapper<Singer>
	{
		@Override
		public Singer mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			return new Singer(rs.getLong("id"),
					rs.getString("first_name"),
					rs.getString("last_name"),
					rs.getDate("birth_date").toLocalDate(),
					Set.of());
		}
	}

	@Override
	public String findNameById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
