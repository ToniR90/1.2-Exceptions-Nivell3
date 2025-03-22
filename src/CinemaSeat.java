import java.util.Objects;

public class CinemaSeat {

    private int rowNumber;
    private int seatNumber;
    private String client;

    public CinemaSeat(int rowNumber , int seatNumber , String client){
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
        this.client = client;
    }

    public int getRowNumber() {
        return this.rowNumber;
    }

    public int getSeatNumber(){
        return this.seatNumber;
    }

    public String getClient(){
        return this.client;
    }

    @Override
    public boolean equals(Object obj){
        boolean areEquals = false;
        if(this == obj){
            areEquals = true;
        }else if(obj != null && obj instanceof CinemaSeat){
            CinemaSeat cinemaSeat = (CinemaSeat) obj;
            areEquals = this.getRowNumber() == cinemaSeat.getRowNumber() &&
                        this.getSeatNumber() == cinemaSeat.getSeatNumber();
        }
    return areEquals;
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.rowNumber , this.seatNumber);
    }
}
