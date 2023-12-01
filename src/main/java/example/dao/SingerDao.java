package example.dao;

import java.util.Set;

import example.model.Singer;

public interface SingerDao 
{
	String findNameById(Long id);
	Set<Singer> findAll();
	Set<Singer> findAllWithAlbums();
}
