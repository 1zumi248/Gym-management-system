package 健身房管理系统;

import java.time.LocalDate;
import java.util.ArrayList;
public class CourseBooking {

    private int []bookingDateRemain = new int[7];

    public CourseBooking( ) {
        
        for (int i =0; i<7;i++){
        	
        bookingDateRemain[i]=10;
    
        	}
    }
    public int getCoursex(int a){
    	return bookingDateRemain[a];
    }

    public void setBookingDateRemain(int[]bookingDateRemain) {
        this.bookingDateRemain = bookingDateRemain;
    }
    public int calculator(int i) {
        bookingDateRemain[i]--;
        return bookingDateRemain[i];
    }

}