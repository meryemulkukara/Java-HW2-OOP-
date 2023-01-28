
public class Director extends Artist {

	private String agent;
	
	//five-argument constructor
	public Director(String id,String nName,String sSurname,String cCountry,String agent) {
		// TODO Auto-generated constructor stub
		super(id,nName,sSurname,cCountry);
		this.agent=agent;
	}//end method five-argument constructor

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	
}
