import java.util.ArrayList;

public class StuntPerformer extends Performer {

	
	ArrayList<String>ActorsIDs;
	private String height;
	
	public StuntPerformer(String id,String nName,String sSurname,String cCountry,String hHeight,ArrayList<String>ActorsIDs) {
		// TODO Auto-generated constructor stub
		super(id,nName,sSurname,cCountry);
		this.height=hHeight;
		this.ActorsIDs=ActorsIDs;
		
	}//end method five-argument constructor

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	
	
}
