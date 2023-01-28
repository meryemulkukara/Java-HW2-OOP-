import java.util.ArrayList;

public class TVSeries extends Film  {

	ArrayList<String> genres;
	ArrayList<String> writerIDs;
	private String startDate;
	private String endDate;
	private String seasons;
	private String episodes;
	
	
	public TVSeries( String id,	String title, String language,	ArrayList<String> directorIDs, String length,
			String country,  ArrayList<String> performedIDs,  ArrayList<String> genres,	ArrayList<String> writerIDs,
			String startDate, String endDate, String seasons, String episodes) {
		// TODO Auto-generated constructor stub
		
		super(id, title, language,	directorIDs, length, country, performedIDs);
		this.genres=genres;
		this.writerIDs=writerIDs;
		this.startDate=startDate;	
		this.endDate=endDate;
		this.seasons=seasons;
		this.episodes=episodes;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public String getSeasons() {
		return seasons;
	}


	public void setSeasons(String seasons) {
		this.seasons = seasons;
	}


	public String getEpisodes() {
		return episodes;
	}


	public void setEpisodes(String episodes) {
		this.episodes = episodes;
	}

	
}
