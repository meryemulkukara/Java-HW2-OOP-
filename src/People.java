
public class People {
	 private String unique_id;
	 private String name;
	 private String surname;
	 private String country;
	 
	 //four argument constructor
	 public People(String id,String nName,String sSurname,String cCountry)
	 {
		 unique_id=id;
		 name=nName;
		 surname=sSurname;
		 country=cCountry;
	 }

	public String getUnique_id() {
		return unique_id;
	}

	public void setUnique_id(String unique_id) {
		this.unique_id = unique_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	 
}
