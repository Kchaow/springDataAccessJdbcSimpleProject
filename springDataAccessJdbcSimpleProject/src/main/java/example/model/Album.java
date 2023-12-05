package example.model;

import java.time.LocalDate;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Album 
{
	private Long id;
	private Long singerId;
	private String title;
	private LocalDate releaseDate;
}
