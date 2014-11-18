/**
 * 
 */
package me.chengtx.webapp.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import me.chengtx.webapp.model.User;

/**
 * @author chengt4
 *
 */
public class UserDAO {

	private static final String PERSISTENCE_UNIT_NAME = "appDB";
	private static final EntityManagerFactory emf = Persistence
			.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	private static final EntityManager em = emf.createEntityManager();

	public List<User> getAllUsers() {
		Query q = em.createQuery("SELECT u FROM User u");
		List<?> userList = q.getResultList();

		List<User> result = new ArrayList<>();
		for (Object o : userList) {
			result.add((User) o);
		}
		return result;
	}

	public boolean createUser() {
		em.getTransaction().begin();
		User user = new User();
		user.setUid("chengtx");
		user.setName("Tingxian Cheng");
		user.setEmail("chengtx@me.com");
		em.persist(user);
		em.getTransaction().commit();
		em.close();
		return true;
	}

}
