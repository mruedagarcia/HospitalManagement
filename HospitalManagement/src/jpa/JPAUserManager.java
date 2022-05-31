package jpa;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import hospital.pojos.*;
import ifaces.UserManager;

public class JPAUserManager implements UserManager {

	private EntityManager em;

	public JPAUserManager() {
		this.connect();
	}

	private void connect() {
		em = Persistence.createEntityManagerFactory("HospitalManagement-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		// Insert the roles needed only if they are not there already
		if (this.getRoles().isEmpty()) {
			Role doctor = new Role("doctor");
			Role patient = new Role("patient");
			Role nurse = new Role("nurse");
			this.createRole(doctor);
			this.createRole(patient);
			
			this.createRole(nurse);
		}
	}

	@Override
	public void disconnect(){
		em.close();
	}

	@Override
	public void createUser(User u) {
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
	}

	private void createRole(Role r) {
		em.getTransaction().begin();
		em.persist(r);
		em.getTransaction().commit();
	}

	@Override
	public void deleteUser(User u) {

		em.getTransaction().begin();
		em.remove(u);
		em.getTransaction().commit();
	}

	@Override
	public void deleteRole(Role r) {

		em.getTransaction().begin();
		em.remove(r);
		em.getTransaction().commit();
	}

	@Override
	public void updateUser(User u,byte[] password) {
		em.getTransaction().begin();
		u.setPassword(password);
		em.getTransaction().commit();

	}

	@Override
	public void updateRole(Role r,String name) {
		em.getTransaction().begin();
		r.setName(name);
		em.getTransaction().commit();
		}

	// READ METHODS
	@Override
	public Role getRole(String name) { // read only one Role
		Query q = em.createNativeQuery("SELECT * FROM roles WHERE name = ?", Role.class);
		q.setParameter(1, name);// we are changing the name of the Role
		Role r = (Role) q.getSingleResult();
		return r;
	}

	@Override
	public List<Role> getRoles() { // read all
		Query q = em.createNativeQuery("SELECT * FROM roles", Role.class);
		List<Role> roles = (List<Role>) q.getResultList();
		return roles;
	}

	@Override
	public User checkPassword(String email, String passwd) {
		// null user if match not found
		User u = null;
		Query q = em.createNativeQuery("SELECT * FROM users WHERE email = ? AND password = ?", User.class);
		q.setParameter(1, email);
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(passwd.getBytes());
			byte[] digest = md.digest();
			q.setParameter(2, digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		try {
			u = (User) q.getSingleResult();
		} catch (NoResultException e) {
		}
		return u;
	}

}
