package st_assignment;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.*;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class CancelBookingTest {
	private BookingList bookingListMock;
    private User userMock;
    private WaitingList waitingListMock;
    private Booking booking;

    @Before
    public void setUp() {
    	bookingListMock = mock(BookingList.class);
        userMock = mock(User.class);
        waitingListMock = mock(WaitingList.class);
        booking = new Booking(userMock, waitingListMock);
    }
    
    //Cancel booking
    private Object[] getParamsforTestCancelBookingValid() {
    	return new Object[] {
    			new Object[] {"Hui Hui"},
    			new Object[] {"Zorian"},
    			new Object[] {"Bob"}
    	};
    }
    	
    @Test
    @Parameters(method="getParamsforTestCancelBookingValid")
    public void testCancelBooking(String name) {
        
        booking.cancelBooking(name);
        
        String AR = null;
        for(BookingList booking: booking.getBookingList()) {
        	if (booking.getName().equals(name)) {
        		AR = name;
        	}
        }
        assertNull(AR); 		
    }
    
    private Object[] getParamsforTestCancelBookingInvalid() {
        return new Object[] {
            new Object[] {null},  
            new Object[] {""},    
            new Object[] {"John ADoe"} 
        };
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method="getParamsforTestCancelBookingInvalid")
    public void testCancelBookingInvalid(String name) {
        
        booking.cancelBooking(name);
        
        String AR = null;
        for(BookingList booking: booking.getBookingList()) {
            if (booking.getName().equals(name)) {
                AR = name;
            }
        }
        assertNull(AR);     
    }
}