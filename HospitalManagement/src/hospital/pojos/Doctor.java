package hospital.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Doctor")
@XmlType(propOrder = { "name", "specialty", "email"}) 

public class Doctor implements Serializable {

	private static final long serialVersionUID = 4406537107496347768L;
    @XmlElement
	private String name;
    @XmlElement
	private String specialty;
    @XmlElement
	private String email;
    @XmlAttribute
	private Integer id;
    @XmlTransient
	private List<Patient> patients;

	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
		patients = new ArrayList<Patient>();
	}

	public Doctor(Integer id, String name, String specialty, String email) {
		super();
		this.name = name;
		this.specialty = specialty;
		this.id = id;
		this.email = email;
	}

	public Doctor(String name, String specialty, String email) {
		this.name = name;
		this.specialty = specialty;
		this.email=email;
	}
	public Doctor (Integer id,String name,String speciality) {
		this.name = name;
		this.id = id;
		this.specialty = speciality;
	}
	public Doctor (String name) {
		this.name = name;
		
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
		Doctor other = (Doctor) obj;
		return Objects.equals(id, other.id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpeciality(String specialty) {
		this.specialty = specialty;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	@Override
	public String toString() {
		return "Doctor [name=" + name + ", specialty=" + specialty + ", id=" + id + ", patients=" + patients + "]";
	}

}
