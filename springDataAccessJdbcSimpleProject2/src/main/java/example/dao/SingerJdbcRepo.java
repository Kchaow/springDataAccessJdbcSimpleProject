package example.dao;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import example.model.Album;
import example.model.Singer;

@Repository
public class SingerJdbcRepo implements SingerDao
{
	@Autowired
	private SelectAllSingers selectAllSingers;
	@Autowired
	private SelectSingerByFirstName selectSingerByFirstName;
	@Autowired
	private UpdateSinger updateSinger;
	@Autowired
	private InsertSinger insertSinger;
	@Autowired
	private InsertSingerAlbum insertSingerAlbum;
	@Autowired
	private StoredFunctionFirstNameById storedFunctionFirstNameById;
	
	@Override
	public List<Singer> findAll() 
	{
		return selectAllSingers.execute();
	}

	@Override
	public List<Singer> findByFirstName(String firstName) 
	{
		return selectSingerByFirstName.executeByNamedParam(Map.of("first_name", firstName));
	}

	@Override
	public void update(Singer singer) 
	{
		updateSinger.updateByNamedParam(
				Map.of("first_name", singer.getFirstName(),
						"last_name", singer.getLastName(),
						"birth_date", singer.getBirthDate(),
						"id", singer.getId()));
		
	}

	@Override
	public Long insert(Singer singer) 
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		insertSinger.updateByNamedParam(Map.of("first_name", singer.getFirstName(),
				"last_name", singer.getLastName(),
				"birth_date", singer.getBirthDate()), keyHolder);
		Long generatedId = keyHolder.getKey().longValue();
		return generatedId;
	}

	@Override
	public Long insertWithAlbum(Singer singer) 
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		insertSinger.updateByNamedParam(Map.of("first_name", singer.getFirstName(),
				"last_name", singer.getLastName(),
				"birth_date", singer.getBirthDate()), keyHolder);
		Long generatedId = Objects.requireNonNull(keyHolder.getKey().longValue());
		
		Set<Album> albums = singer.getAlbums();
		if (albums != null)
		{
			for (Album album : albums)
			{
				insertSingerAlbum.updateByNamedParam(Map.of("singer_id", generatedId,
						"title", album.getTitle(),
						"release_date", album.getReleaseDate()));
			}
		}
		insertSingerAlbum.flush();
		return generatedId;
	}

	@Override
	public Optional<String> findFirstNameById(Long id) 
	{
		String result = storedFunctionFirstNameById.execute(id).get(0);
		return result != null ? Optional.of(result): Optional.empty();
	}
	
	
}
