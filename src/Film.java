import java.util.ArrayList;

public class Film {

	private String id;
	private String title;
	private String language;
	ArrayList<String> directorIDs;
	private String length;
	private String country;
	ArrayList<String> performedIDs;

	
	
	
		public Film( String id,	String title, String language,	ArrayList<String> directorIDs, String length,
					String country,  ArrayList<String> performedIDs) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.title=title;
		this.language=language;
		this.directorIDs=directorIDs;
		this.length=length;
		this.country=country;
		this.performedIDs=performedIDs;
		
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getLanguage() {
		return language;
	}


	public void setLanguage(String language) {
		this.language = language;
	}


	public String getLength() {
		return length;
	}


	public void setLength(String length) {
		this.length = length;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	
	

}
