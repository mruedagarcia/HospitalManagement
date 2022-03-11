package hospital.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Medicine implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = -4337318150581789480L;
	private Integer id;
	private String name;

	public Medicine() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
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
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
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

}
