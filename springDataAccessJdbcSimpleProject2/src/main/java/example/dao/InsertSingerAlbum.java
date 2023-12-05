package example.dao;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.BatchSqlUpdate;
import org.springframework.stereotype.Component;

@Component
public class InsertSingerAlbum extends BatchSqlUpdate
{
	public static final int BATCH_SIZE = 10;
	public static final String INSERT_SINGER_ALBUM = "insert into album (singer_id, title, release_date) " +
	"values (:singer_id, :title, :release_date)";
	
	public InsertSingerAlbum(DataSource dataSource)
	{
		super(dataSource, INSERT_SINGER_ALBUM);
		declareParameter(new SqlParameter("singer_id", Types.INTEGER));
		declareParameter(new SqlParameter("title", Types.VARCHAR));
		declareParameter(new SqlParameter("release_date", Types.DATE));
		setBatchSize(BATCH_SIZE);
	}
}
