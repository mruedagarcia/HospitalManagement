package hospital.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Doctor implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = 4406537107496347768L;
	private String name;
	private String speciality;
	private Integer id;

	@Override
	public int hashCode() {
		return Objects.hash(id, name, speciality);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(speciality, other.speciality);
	}

	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
