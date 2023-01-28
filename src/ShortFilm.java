import java.util.ArrayList;

public class ShortFilm extends Film {

	ArrayList<String> genres;
	private String releaseDate;
	ArrayList<String> writerIDs;
		
	public ShortFilm( String id,	String title, String language,	ArrayList<String> directorIDs, String length,
			String country,  ArrayList<String> performedIDs,  ArrayList<String> genres,	 String releaseDate,
			ArrayList<String> writerIDs) {
		// TODO Auto-generated constructor stub
		super(id, title, language,	directorIDs, length, country, performedIDs);
		this.genres=genres;
		this.releaseDate=releaseDate;
		this.writerIDs=writerIDs;
	}


	public String getReleaseDate() {
		return releaseDate;
	}


	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	

}
