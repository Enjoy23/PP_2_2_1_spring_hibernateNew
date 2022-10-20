package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.TypedQuery;
import java.util.*;

@Repository
public class UserDaoImp implements UserDao {

   private final SessionFactory sessionFactory;

   @Autowired
   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public List<User> listUsers() {
      try(Session session = sessionFactory.openSession()) {
         TypedQuery<User> query = session.createQuery("from User", User.class);
         return query.getResultList();
      } catch (Exception e) {
         System.out.println("Произошла ошибка при вызове списка юзеров");
         return new ArrayList<>();
      }
   }

   @Override
   public User getUserByModelAndSeries(String modelCar, int seriesCar) {
      String hql = "FROM User where car.model = :model AND car.series = :series";
      try (Session session = sessionFactory.openSession()) {
         TypedQuery<User> query = session.createQuery(hql, User.class);
         query.setParameter("model", modelCar);
         query.setParameter("series", seriesCar);
         return query.getSingleResult();
      } catch (Exception e) {
         System.out.println("Нет подходящего юзера");
         return new User();
      }
   }
}
