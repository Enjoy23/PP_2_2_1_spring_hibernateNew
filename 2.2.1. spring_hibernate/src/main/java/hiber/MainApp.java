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
      CarService carService = context.getBean(CarService.class);


      User user1 = new User("Наиль","Алишев","nail.gmail.com");
      User user2 = new User("Заур","Трегулов","zaurchik@yandex.ru");
      User user3 = new User("Герман","Севостьянов","kata@family.com");

      Car car1 = new Car("BMW",5);
      Car car2 = new Car("Mazda",3);
      Car car3 = new Car("Mercedes",63);


      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
                                       //ИСПОЛЬЗОВАЛ СЕТТЕРЫ ДЛЯ ТОГО ЧТОБ РАБОТАЛ
      car1.setUser(user1);                   //ВТОРОЙ МЕТОД КОТОРЫЙ ДОСТАЁТ МОДЕЛЬ МАШИНЫ ПО ИМЕНИ ЮЗЕРА
      car2.setUser(user2);
      car3.setUser(user3);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);

      /* Здесь написан код если всё же нужно внедрить зависимостость через конструктор
      но в этом случае нужно закомментировать метод carService и 5 блоков выше

      userService.add(new User(new Car("BMW",5),"Наиль","Алишев","nail.gmail.com"));
      userService.add(new User(new Car("Mazda",3),"Заур","Трегулов","zaurchik@yandex.ru"));
      userService.add(new User(new Car("Mercedes",63),"Герман","Севостьянов","kata@family.com"));

      */


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println();
      }


      System.out.println();
      System.out.println(userService.getUserByModelAndSeries("Mazda",3).getFirstName());

      System.out.println(carService.getCarByUserFirstName("Герман").getModel());



      context.close();
   }
}
