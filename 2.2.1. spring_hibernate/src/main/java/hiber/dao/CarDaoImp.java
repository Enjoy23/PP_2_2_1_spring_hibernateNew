package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;

@Repository
public class CarDaoImp implements CarDao{
    private final SessionFactory sessionFactory;

    @Autowired
    public CarDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Car car) { sessionFactory.getCurrentSession().save(car); }
    @Override
    public Car getCarByUserFirstName(String firstName) {
        String hql = "FROM Car where user.firstName = :firstName";
        Session session = sessionFactory.getCurrentSession();
            TypedQuery<Car> query = session.createQuery(hql);
            query.setParameter("firstName",firstName);
            return query.getSingleResult();
    }

}
