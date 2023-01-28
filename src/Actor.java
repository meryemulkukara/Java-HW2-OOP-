
public class Actor extends Performer{

	private String height;
	
	public Actor(String id,String nName,String sSurname,String cCountry,String hHeight) {
		// TODO Auto-generated constructor stub
		super(id,nName,sSurname,cCountry);
		this.height=hHeight;
		
	}//end method five-argument constructor

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	
}
