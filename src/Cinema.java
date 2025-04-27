import java.util.InputMismatchException;
import java.util.Scanner;

public class Cinema {

    static Scanner scanner = new Scanner(System.in);

    private int numberOfRows;
    private int seatsPerRow;
    private SeatManagement seatManagement;
    private CinemaManagement cinemaManagement;

    public Cinema() {
        this.seatManagement = new SeatManagement();
        this.cinemaManagement = new CinemaManagement(this);
        initializeData();
    }

    public int getNumberOfRows() {
        return this.numberOfRows;
    }

    public int getSeatsPerRow() {
        return this.seatsPerRow;
    }

    public SeatManagement getSeatManagement() {
        return this.seatManagement;
    }

    public CinemaManagement getCinemaManagement() {
        return this.cinemaManagement;
    }

    public void start() {
        int option = 0;
        String client = "";
        do {
            option = CinemaManagement.menu();
            switch (option) {
                case 1:
                    cinemaManagement.showReservedSeats();
                    break;
                case 2:
                    System.out.println("Enter the client's name");
                    client = scanner.next().trim();
                    cinemaManagement.showSeatsByClient(client);
                    break;
                case 3:
                    cinemaManagement.reserveSeat();
                    break;
                case 4:
                    cinemaManagement.cancelSeatReservation();
                    break;
                case 5:
                    cinemaManagement.cancellAllReservationsForClient();
                    break;
                case 0:
                    System.out.println("See you again!");
                    break;
            }
        } while (option != 0);
    }

    public void initializeData() {
        do {
            try {
                System.out.println("Initialize the number of rows of your Cinema");
                this.numberOfRows = scanner.nextInt();
                if (this.numberOfRows < 0) {
                    System.out.println("The number can't be negative, try again");
                }
            } catch (InputMismatchException e) {
                System.out.println("The number of rows has to be an integer, try again");
                scanner.next();
                this.numberOfRows = -1;
            }
        } while (this.numberOfRows < 0);

        do {
            try {
                System.out.println("Initialize the number of seats per row");
                this.seatsPerRow = scanner.nextInt();
                if (this.seatsPerRow < 0) {
                    System.out.println("The number can't be negative, try again");
                }
            } catch (InputMismatchException e) {
                System.out.println("The number of seats has to be an integer, try again");
                scanner.next();
                this.seatsPerRow = -1;
            }
        } while (this.seatsPerRow < 0);
    }
}
