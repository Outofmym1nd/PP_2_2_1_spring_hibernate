package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUserByCar(Car car) {
        try (Session session = sessionFactory.openSession()) {
            String model = car.getModel();
            Long series = car.getSeries();
            Query<User> query = session.createQuery(
                    "SELECT u FROM User u " +
                            "WHERE u.car.model = :model AND u.car.series = :series",
                    User.class);
            query.setParameter("model", model);
            query.setParameter("series", series);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}