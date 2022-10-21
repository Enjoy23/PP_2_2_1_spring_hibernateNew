package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);



      userService.add(new User(new Car("BMW",5),"Наиль","Алишев","nail.gmail.com"));
      userService.add(new User(new Car("Mazda",3),"Заур","Трегулов","zaurchik@yandex.ru"));
      userService.add(new User(new Car("Mercedes",63),"Герман","Севостьянов","kata@family.com"));


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user);
         System.out.println();
      }

      System.out.println(userService.getUserByModelAndSeries("Mazda",3));
      System.out.println(userService.getCarByUserFirstName("Герман"));

      context.close();
   }
}
