import java.util.ArrayList;

public class Rate {

	private String userId;
	ArrayList<String> filmId;
	ArrayList <String> ratingPoint;
	public Rate( String userId, ArrayList <String> filmId, ArrayList <String> ratingPoint) {
		// TODO Auto-generated constructor stub
		this.userId=userId;
		this.filmId=filmId;
		this.ratingPoint=ratingPoint;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
