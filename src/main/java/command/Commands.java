package command;

public interface Commands {
   int EXIT = 0;
   int ADD_EVENT = 1;
   int ADD_USER = 2;
   int SHOW_EVENTS = 3;
   int SHOW_USERS = 4;


    public static void printComands(){
        System.out.println("input " + EXIT + " for logout");
        System.out.println("input " + ADD_EVENT + " to add event");
        System.out.println("input " + ADD_USER + " to  add user");
        System.out.println("input " + SHOW_EVENTS + " to show all events");
        System.out.println("input " + SHOW_USERS + " to show all users");
    }
}
