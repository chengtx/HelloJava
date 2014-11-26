/**
 *
 */
package me.chengtx.webapp.jpa;

import me.chengtx.webapp.dao.UserStore;
import me.chengtx.webapp.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chengt4
 */
@Repository
public class UserStoreImpl extends AbstractStore implements UserStore {

    @Override
    @Transactional
    public List<User> getAllUsers() {
        Query q = entityManager.createQuery("SELECT u FROM User u");
        List<?> userList = q.getResultList();

        List<User> result = new ArrayList<>();
        userList.forEach(s -> result.add((User) s));

        return result;
    }

    @Override
    @Transactional
    public boolean createUser() {
        User user = new User();
        user.setUid("chengtx" + new Date());
        user.setName("Tingxian Cheng");
        user.setEmail("chengtx@me.com");
        entityManager.persist(user);
        return true;
    }

}
