package hospital.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Patient implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1080532239335630835L;
	private Integer id;
	private String email;
	private Date dob;
	private Integer phone;
	private String name;
	private String status;
	//Many to one relationship
	private Nurse nurse;
	//many to many relationship
	private List<Disease> diseases;
	private List<Doctor> doctors;
	private List<Medicine> medicines;
	private List<Symptom> symptoms;
	
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
		diseases = new ArrayList<Disease>();
		doctors = new ArrayList<Doctor>();
		medicines = new ArrayList<Medicine>();
		symptoms = new ArrayList<Symptom>();
	}
	
	
	public Integer getId() {
		return id;
	}
	
	//equals and hashcode
	
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
				+ ", status=" + status + ", diseases=" + diseases + ", doctors=" + doctors + ", medicines=" + medicines
				+ ", symptoms=" + symptoms + "]";
	}
	

}
