package hospital.pojos;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1798339578536720273L;
	@Id
	@GeneratedValue(generator = "users")
	@TableGenerator(name = "users", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "users")
	private Integer id;
	private String email;// poner el email en la tabla de doctors tambi�n y buscar un link entre ellos
	@Lob
	private byte[] password;
	@ManyToOne
	@JoinColumn(name = "roleId")
	private Role role;

	public User() {
		super();
	}
	
	public User(String email, byte[] password) {
		super();
		this.email=email;
		this.password=password;
	}

	public Integer getId() {
		return id;
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

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + Arrays.toString(password) + ", role="
				+ role.getName() + "]";
	}
}
