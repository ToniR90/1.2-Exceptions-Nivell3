import java.util.Scanner;

public class Cinema {

    private int numberOfRows;
    private int seatsPerRow;
    private SeatManagement seatManagement;
    private CinemaManagement cinemaManagement;

    public Cinema(){
        this.seatManagement = new SeatManagement();
        this.cinemaManagement = new CinemaManagement(this);
        initializeData();
    }

    public int getNumberOfRows(){
        return this.numberOfRows;
    }

    public int getSeatsPerRow() {
        return this.seatsPerRow;
    }

    public static void start(){
        int option;
        do{
            option = CinemaManagement.menu();
            switch(option){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 0:
                    break;
                default:

            }
        }while(option != 0);


    }

    public void initializeData(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Initialize the number of rows");
        this.numberOfRows = scanner.nextInt();
        System.out.println("Initialize the number of seats per row");
        this.seatsPerRow = scanner.nextInt();

    }
}
