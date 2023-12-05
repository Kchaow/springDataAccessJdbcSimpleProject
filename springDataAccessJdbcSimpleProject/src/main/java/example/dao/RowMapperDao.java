package example.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import example.model.Album;
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
	
	@Override
	public Set<Singer> findAllWithAlbums() 
	{
		var sqlQuery = "SELECT s.id, s.first_name, s.last_name, s.bith_date, "
				+ "a.id, AS album_id, a.title, a.release_date "
				+ "from SINGER s "
				+ "left join ALBUM a on s,id = a.singer_id";
		return namedTemplate.query(sqlQuery, new SingerWithAlbumsExtractor());
	}
	
	static class SingerWithAlbumsExtractor implements ResultSetExtractor<Set<Singer>>
	{
		@Override
		public Set<Singer> extractData(ResultSet rs) throws SQLException, DataAccessException
		{
			Map<Long, Singer> map = new HashMap<>();
			Singer singer;
			while (rs.next())
			{
				Long id = rs.getLong("id");
				singer = map.get(id);
				if (singer == null)
				{
					singer = new Singer(id,rs.getString("first_name"),
							rs.getString("last_name"),
							rs.getDate("birth_date").toLocalDate(),
							new HashSet<>());
					map.put(id, singer);
				}
				
				var albumId = rs.getLong("album_id");
				
				if (albumId > 0)
				{
					Album album = new Album(albumId,
							id,
							rs.getString("title"),
							rs.getDate("release_date").toLocalDate());
					singer.getAlbums().add(album);
				}
			}
			return new HashSet<>(map.values());
		}
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
