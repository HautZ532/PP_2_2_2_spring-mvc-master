package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void removeUser(long id) {
        entityManager.remove(getUser(id));
    }

    @Override
    public User editUser(User user) {
        entityManager.merge(user);
        entityManager.flush();
        return user;
    }

    @Override
    public User getUser(long id) {
        return entityManager.find(User.class, id);
    }


}
