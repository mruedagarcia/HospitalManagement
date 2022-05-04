package hospital.pojos;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
@Table (name="medicines")
public class Medicine implements Serializable {
	
	private static final long serialVersionUID = -4337318150581789480L;
	private Integer id;
	private String name;
	//many to many relationship
	private List<Patient> patients;
	private List<Disease> diseases;

	public Medicine() {
		super();
		// TODO Auto-generated constructor stub
		patients = new ArrayList<Patient>();
	}

	public Medicine(Integer id2, String name2) {
		this.id = id2;
		this.name = name2;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medicine other = (Medicine) obj;
		return Objects.equals(id, other.id);
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	@Override
	public String toString() {
		return "Medicine [id=" + id + ", name=" + name + ", patients=" + patients + "]";
	}

}
