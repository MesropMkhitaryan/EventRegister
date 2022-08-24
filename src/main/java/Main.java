import command.Commands;
import enums.EventType;
import manager.EventManager;
import manager.UserManager;
import model.Event;
import model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main implements Commands {
    private static Scanner scanner = new Scanner(System.in);
   private static UserManager userManager = new UserManager();
   private static EventManager eventManager = new EventManager();

    public static void main(String[] args) throws SQLException {
        boolean run = true;
        while (run){
            Commands.printComands();

            int command = 0;
            try {
                command = Integer.parseInt(scanner.nextLine());
            }
            catch (NumberFormatException e){
                command = -1;
            }
            switch (command){
                case EXIT:
                    run =false;
                    break;
                case ADD_EVENT:
                    addEvents();
                    break;
                case ADD_USER:
                    addUser();
                    break;
                case SHOW_EVENTS:
                    showEvents();
                    break;
                case SHOW_USERS:
                    showUsers();
                    break;
                default:
                    System.out.println("invalid number");

            }
        }
    }

    private static void showUsers() throws SQLException {
        List<User> userList = userManager.getAllUsers();
        for (User user : userList) {

                System.out.println(user);

        }
    }

    private static void showEvents() throws SQLException {
        List<Event> eventList = eventManager.getAllEvents();
        for (Event event : eventList) {

                System.out.println(event);

        }
    }

    private static void addEvents() {
        System.out.println("please input event name");
        String name = scanner.nextLine();
        System.out.println("please input events place");
        String place = scanner.nextLine();
        System.out.println("please input true if this event is online or false if its not ");
        boolean isOnline = Boolean.parseBoolean(scanner.nextLine());
        System.out.println("please input event price");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.println("please choose event type");
        try {
            System.out.println("CONCERT, SPORT EVENT");
            EventType eventType = EventType.valueOf(scanner.nextLine());
            if (eventType.equals(EventType.CONCERT) || eventType.equals(EventType.SPORT_EVENT)){
                Event event = new Event(name,place,isOnline,price,eventType);
                try {
                    eventManager.addEvent(event);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                System.out.println("there is no such a event type please try again");
                addEvents();
            }
        }catch (IllegalArgumentException e) {
            System.out.println("there is no such a event type please try again");
            addEvents();
        }
    }

    private static void addUser() {
        System.out.println("please input users name");
        String name = scanner.nextLine();
        System.out.println("please input users surname");
        String surname = scanner.nextLine();
        System.out.println("please input users email");
        String email = scanner.nextLine();
        System.out.println("please input event id ");
        List<Event> eventList = null;
        try {
            eventList = eventManager.getAllEvents();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Event event : eventList) {

                System.out.println(event);

        }
        int eventId = Integer.parseInt(scanner.nextLine());
        User user = new User(name,surname,email,eventId);
        try {
            userManager.addUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
