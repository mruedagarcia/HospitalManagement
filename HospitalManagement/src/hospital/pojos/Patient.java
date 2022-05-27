package hospital.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

<<<<<<< HEAD
import xml.SQLDateAdapter;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Patient")
@XmlType(propOrder = { "email", "dob", "name", "nurse", "doctors", "diseases", "medicines", "symptoms" }) 

=======
>>>>>>> branch 'master' of https://github.com/mruedagarcia/HospitalManagement
public class Patient implements Serializable {

	private static final long serialVersionUID = -1080532239335630835L;
	@XmlAttribute
	private Integer id;
	@XmlElement
	private String email;
	@XmlElement
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date dob;
	@XmlAttribute
	private Integer phone;
	@XmlElement
	private String name;
	@XmlAttribute
	private boolean severe;
	@XmlElement
	private Nurse nurse;
	@XmlElement(name = "Doctor")
	private List<Doctor> doctors;
	@XmlElement(name = "Disease")
	private List<Disease> diseases;
	@XmlElement(name = "Medicine")
	private List<Medicine> medicines;
	@XmlElement(name = "Symptom")
	private List<Symptom> symptoms;

	public Patient() {
		super();

		diseases = new ArrayList<Disease>();
		doctors = new ArrayList<Doctor>();
		medicines = new ArrayList<Medicine>();
		symptoms = new ArrayList<Symptom>();
	}

	public Patient(Integer id, String name, String email, boolean severe, Integer phone, Date dob) {
		super();
		this.id = id;
		this.email = email;
		this.dob = dob;
		this.phone = phone;
		this.name = name;
		this.severe = severe;
	}

	public Patient(String name, String email, boolean severe, Integer phone, Date dob) {
		super();
		this.email = email;
		this.dob = dob;
		this.phone = phone;
		this.name = name;
		this.severe = severe;
	}

	public Integer getId() {
		return id;
	}

	// equals and hashcode

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
		Patient other = (Patient) obj;
		return Objects.equals(id, other.id);
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Nurse getNurse() {
		return nurse;
	}

	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getSevere() {
		return severe;
	}

	public void setSevere(boolean severe) {
		this.severe = severe;
	}

	public List<Disease> getDiseases() {
		return diseases;
	}

	public void setDiseases(List<Disease> diseases) {
		this.diseases = diseases;
	}

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}

	public List<Medicine> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<Medicine> medicines) {
		this.medicines = medicines;
	}

	public List<Symptom> getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(List<Symptom> symptoms) {
		this.symptoms = symptoms;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", email=" + email + ", dob=" + dob + ", phone=" + phone + ", name=" + name
				+ ", severe=" + severe + ", diseases=" + diseases + ", doctors=" + doctors + ", medicines=" + medicines
				+ ", symptoms=" + symptoms + "]";
	}

}
