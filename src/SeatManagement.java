import java.util.ArrayList;
import java.util.List;

public class SeatManagement {

    private List<CinemaSeat> seats;

    public SeatManagement() {
        this.seats = new ArrayList<>();
    }

    public List<CinemaSeat> getReservedSeats() {
        return this.seats;
    }

    public void addSeat(CinemaSeat cinemaSeat) {
        int seatFound = findSeat(cinemaSeat.getRowNumber(), cinemaSeat.getSeatNumber());
        if (seatFound == -1) {
            seats.add(cinemaSeat);
        } else {
            throw new SeatAlreadyReservedException("The seat is already reserved\n");
        }
    }

    public void removeSeat(int row, int seatNumber) {
        int seatFound = findSeat(row, seatNumber);
        if (seatFound != -1) {
            seats.remove(seatFound);
        } else {
            throw new SeatNotReservedException("The seat is not reserved by any client\n");
        }
    }

    public int findSeat(int row, int seatNumber) {
        int seatPositionFound = -1;
        boolean found = false;
        int i = 0;
        while (i < seats.size() && !found) {
            if (seats.get(i).getRowNumber() == row && seats.get(i).getSeatNumber() == seatNumber) {
                seatPositionFound = i;
                found = true;
            }
            i++;
        }
        return seatPositionFound;
    }
}
