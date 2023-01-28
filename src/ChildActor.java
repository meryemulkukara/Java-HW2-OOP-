
public class ChildActor extends Performer {

	private String age;
	
	public ChildActor(String id,String nName,String sSurname,String cCountry,String aAge) {
		// TODO Auto-generated constructor stub
		super(id,nName,sSurname,cCountry);
		this.age=aAge;
		
	}//end method five-argument constructor

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	
}
