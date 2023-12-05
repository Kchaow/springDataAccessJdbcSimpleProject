package example.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import example.model.Singer;

@Repository
public interface SingerDao 
{
	List<Singer> findByFirstName(String firstName);
	List<Singer> findAll();
	Optional<String> findFirstNameById(Long id);
	void update(Singer singer);
	Long insert(Singer singer);
	Long insertWithAlbum(Singer singer);
}
