import java.util.ArrayList;

public class FeatureFilm extends Film {

	ArrayList<String> genres;
	private String releaseDate;
	ArrayList<String> writerIDs;
	String budget;
	
	public FeatureFilm(String id,	String title, String language,	ArrayList<String> directorIDs, String length,
			String country,  ArrayList<String> performedIDs,  ArrayList<String> genres,	 String releaseDate,
			ArrayList<String> writerIDs,String budget) {
		// TODO Auto-generated constructor stub
		super(id, title, language,	directorIDs, length, country, performedIDs	);
		this.genres=genres;
		this.releaseDate=releaseDate;
		this.writerIDs=writerIDs;
		this.budget=budget;		
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}
	
	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

}
