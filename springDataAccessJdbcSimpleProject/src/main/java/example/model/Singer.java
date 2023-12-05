package example.model;

import java.time.LocalDate;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Singer
{
	Long id;
	String firstName;
	String lastName;
	LocalDate birthDate;
	Set<Album> albums;
}
