
public class Writer extends Artist {

	private String writing_type;
	
	//five-argument constructor
	public Writer(String id,String nName,String sSurname,String cCountry,String writing_type) {
		// TODO Auto-generated constructor stub
		super(id,nName,sSurname,cCountry);
		this.writing_type=writing_type;
	}//end method five-argument constructor

	public String getWriting_style() {
		return writing_type;
	}

	public void setWriting_style(String writing_style) {
		this.writing_type = writing_style;
	}
	
	

	

}
