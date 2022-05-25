package hospital.pojos;

import java.io.Serializable;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Patient")
@XmlType(propOrder = { "dob", "address", "salary" })

public class Patient implements Serializable {

	private static final long serialVersionUID = -1080532239335630835L;
	private Integer id;
	private String email;
	private Date dob;
	private Integer phone;
	private String name;
	private boolean severe;
	private Nurse nurse;
	private List<Doctor> doctors;
	// @Transient si no funciona pondremos esto en cada clase que NO utilice JPA
	private List<Disease> diseases;

	private List<Medicine> medicines;
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
