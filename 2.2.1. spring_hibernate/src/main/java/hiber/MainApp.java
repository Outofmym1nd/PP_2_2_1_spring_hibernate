package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("Ivan", "Ivanov", "ivan@mail.ru");
        User user2 = new User("Petr", "Petrov", "petr@mail.ru");
        User user3 = new User("Foma", "Fomin", "foma@mail.ru");
        User user4 = new User("Igor", "Igorev", "igor@mail.ru");

        Car car1 = new Car("BMW", 5L);
        Car car2 = new Car("Toyota", 30L);
        Car car3 = new Car("LADA", 12L);
        Car car4 = new Car("Mercedes", 63L);

        user1.setCar(car1);
        user2.setCar(car2);
        user3.setCar(car3);
        user4.setCar(car4);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car model = " + user.getCar().getModel());
            System.out.println("Car series = " + user.getCar().getSeries());
            System.out.println();
        }

        System.out.println(userService.getUserByCar(car1));
        System.out.println();
        System.out.println(userService.getUserByCar(car2));
        System.out.println();
        System.out.println(userService.getUserByCar(car3));
        System.out.println();
        System.out.println(userService.getUserByCar(car4));

        context.close();
    }
}
