import java.util.Scanner;

public class CinemaManagement {

    private Cinema cinema;

    public CinemaManagement(Cinema cinema){

    }

    public static int menu(){
        Scanner scanner = new Scanner(System.in);
        int option;
        do{
            System.out.println("Select an option:\n" +
                    "1. Show reserved seats\n" +
                    "2. Show reservations by client\n" +
                    "3. Reserve a seat\n" +
                    "4. Cancel reservation\n" +
                    "5. Cancel all reservations for a client\n" +
                    "0. Exit\n");
            option = scanner.nextInt();

        }while(option != 0);
    return option;
    }
}
