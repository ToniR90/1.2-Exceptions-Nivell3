import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CinemaManagement {
    static Scanner scanner = new Scanner(System.in);

    private Cinema cinema;

    public CinemaManagement(Cinema cinema) {
        this.cinema = cinema;
    }

    public static int menu() {
        int option;
        do {
            try {
                System.out.println("Select an option:\n" +
                        "1. Show reserved seats\n" +
                        "2. Show reservations by client\n" +
                        "3. Reserve a seat\n" +
                        "4. Cancel reservation\n" +
                        "5. Cancel all reservations for a client\n" +
                        "0. Exit\n");
                option = scanner.nextInt();
                if (option < 0 || option > 5) {
                    System.out.println("Invalid option, try again" + "\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("The option has to be an integer between 0 - 5, try again" + "\n");
                scanner.next();
                option = -1;
            }
        } while (option < 0 || option > 5);
        return option;
    }

    public void showReservedSeats() {
        List<CinemaSeat> reservedSeats = cinema.getSeatManagement().getReservedSeats();
        if (reservedSeats.isEmpty()) {
            System.out.println("There are no reserved seats" + "\n");
        } else {
            System.out.println("Reserved seats:\n");
            for (CinemaSeat reservedSeat : reservedSeats) {
                System.out.println(reservedSeat + "\n");
            }
        }
    }

    public void showSeatsByClient(String client) {
        List<CinemaSeat> reservedSeatsByClient = cinema.getSeatManagement().getReservedSeats();
        boolean found = false;

        for (CinemaSeat seat : reservedSeatsByClient) {
            if (seat.getClient().equalsIgnoreCase(client)) {
                System.out.println(seat);
                found = true;
            }
        }
        if (!found) {
            System.out.println("The client " + client + " has no reservations" + "\n");
        }
    }

    public void reserveSeat() {
        try{
            int row = introduceRow();
            int seatNumber = introduceSeat();
            String client = introduceClient();

            CinemaSeat newSeat = new CinemaSeat(row , seatNumber , client);
            cinema.getSeatManagement().addSeat(newSeat);
            System.out.println("Seat reserved for " + client + "\n");
        }catch(SeatAlreadyReservedException e){
            System.out.println(e.getMessage() + "\n");
        }
    }

    public void cancelSeatReservation() {
        try{
            int row = introduceRow();
            int seatNumber = introduceSeat();

            cinema.getSeatManagement().removeSeat(row , seatNumber);
            System.out.println("The seat has been cancelled" + "\n");
        }catch(SeatNotReservedException e){
            System.out.println(e.getMessage() + "\n");
        }
    }

    public void cancellAllReservationsForClient() {
        String client = introduceClient();
        boolean hasReservation = false;

        for(int i = 0 ; i < cinema.getSeatManagement().getReservedSeats().size() ; i++){
            if(cinema.getSeatManagement().getReservedSeats().get(i).getClient().equalsIgnoreCase(client)){
                cinema.getSeatManagement().getReservedSeats().remove(i);
                i--;
                hasReservation = true;
            }
        }
        if(hasReservation){
            System.out.println("All reservations for client " + client + " have been cancelled" + "\n");
        }else{
            System.out.println("The client " + client + " has no reserved seats" + "\n");
        }
    }

    public String introduceClient() {
        String clientName = "";
        boolean valid = false;
        do {
            try{
                System.out.println("Insert client's name");
                clientName = scanner.nextLine();
                if(clientName.isEmpty() || clientName.matches(".*\\d.*")){
                    throw new IncorrectClientNameException("The client's name can't contains any number, try again" + "\n");
                }else{
                    valid = true;
                }
            }catch (IncorrectClientNameException e){
                System.out.println(e.getMessage() + "\n");
            }
        }while(!valid);
        return clientName;
    }

    public int introduceRow() {
        int rowNumber = 0;
        boolean valid = false;
        do{
            try{
                System.out.println("Insert the row number");
                rowNumber = scanner.nextInt();
                scanner.nextLine();
                if (rowNumber < 1 || rowNumber > cinema.getNumberOfRows()) {
                    throw new IncorrectRowException("The row number must be between 1 and " + cinema.getNumberOfRows() + "\n");
                }else{
                    valid = true;
                }
            }catch(InputMismatchException e){
                System.out.println("Please enter an integer number" + "\n");
                scanner.next();
            }catch(IncorrectRowException e){
                System.out.println(e.getMessage() + "\n");
            }
        }while(!valid);
        return rowNumber;
    }

    public int introduceSeat() {
        int seatNumber = 0;
        boolean valid = false;
        do{
            try{
                System.out.println("Insert the seat number");
                seatNumber = scanner.nextInt();
                scanner.nextLine();
                if(seatNumber < 1 || seatNumber > cinema.getSeatsPerRow()){
                    throw new IncorrectSeatException("The seat number must be between 1 and " + cinema.getSeatsPerRow() + "\n");
                }else{
                    valid = true;
                }
            }catch(InputMismatchException e){
                System.out.println("Please enter an integer number");
                scanner.next();
            }catch(IncorrectSeatException e){
                System.out.println(e.getMessage() + "\n");
            }
        }while(!valid);
        return seatNumber;
    }


}
