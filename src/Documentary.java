import java.util.ArrayList;

public class Documentary extends Film {

	private String releaseDate;
	
	
	public Documentary( String id,	String title, String language,	ArrayList<String> directorIDs, String length,
			String country,  ArrayList<String> performedIDs,	 String releaseDate) {
		// TODO Auto-generated constructor stub
		super(id, title, language,	directorIDs, length, country, performedIDs);
		this.releaseDate=releaseDate;
		
	}

	public String getReleaseDate() {
		return releaseDate;
	}


	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	
}
