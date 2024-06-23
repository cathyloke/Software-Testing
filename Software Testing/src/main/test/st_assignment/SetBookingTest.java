package st_assignment;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.*;
import org.junit.runner.RunWith;

import java.io.*;
import java.util.*;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;


@RunWith(JUnitParamsRunner.class)
public class SetBookingTest {
	private BookingList bookingListMock;
    private User userMock;
    private WaitingList waitingListMock;
    private Booking booking;
    private Room roomMock;

    @Before
    public void setUp() {
    	bookingListMock = mock(BookingList.class);
    	roomMock = mock(Room.class);
        userMock = mock(User.class);
        waitingListMock = mock(WaitingList.class);
        booking = new Booking(roomMock, userMock, waitingListMock);
    }
    
    public static Object[] getParamsFromTextValid(String filename) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
        Object[][] params = new Object[150][]; 
        int i = 0;
        while (line != null) {
            String[] values = line.split(",");
            String name = values[0];
            String memberType = values[1].trim();
            boolean rewards = Boolean.parseBoolean(values[2].trim());
            int rooms = Integer.parseInt(values[3].trim());
            boolean vipRooms = Boolean.parseBoolean(values[4].trim());
            boolean deluxeRooms = Boolean.parseBoolean(values[5].trim());
            boolean standardRooms = Boolean.parseBoolean(values[6].trim());
            int expectedVipRooms = Integer.parseInt(values[7].trim()); 
            int expectedDeluxeRooms = Integer.parseInt(values[8].trim()); 
            int expectedStandardRooms = Integer.parseInt(values[9].trim());

            Object[] paramSet = new Object[] { name, memberType, rewards, rooms, vipRooms, deluxeRooms, standardRooms, expectedVipRooms, expectedDeluxeRooms, expectedStandardRooms };
            params[i++] = paramSet;
            line = reader.readLine();
        }
        reader.close();
        // Trim the array to the actual size
        return Arrays.copyOf(params, i);
    }
    
    public static Object[] getParamsFromTextInvalid(String filename) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
        Object[][] params = new Object[150][]; 
        int i = 0;
        while (line != null) {
            String[] values = line.split(",");
            String memberType = values[0].trim();
            boolean rewards = Boolean.parseBoolean(values[1].trim());
            int rooms = Integer.parseInt(values[2].trim());
            
            Object[] paramSet = new Object[] {memberType, rewards, rooms};
            params[i++] = paramSet;
            line = reader.readLine();
        }
        reader.close();
        // Trim the array to the actual size
        return Arrays.copyOf(params, i);
    }

    //Available room is 3, 3, 3
    private Object[] getParamsforSetBookingTestValidV1() throws Exception {
    	return getParamsFromTextValid("getParamsforSetBookingTestValidV1.txt");
    }
    
    @Test
    @Parameters(method="getParamsforSetBookingTestValidV1")
    public void testSetBookingValidV1(String name, String userType, boolean rewards, int rooms, boolean vipRooms, boolean deluxeRooms, boolean standardRooms,
                               int expectedVipRooms, int expectedDeluxeRooms, int expectedStandardRooms) {
        when(userMock.getMemberType()).thenReturn(userType);
        when(roomMock.getVip()).thenReturn(3);
        when(roomMock.getDeluxe()).thenReturn(3);
        when(roomMock.getStandard()).thenReturn(3);
        ArrayList<BookingList> bookinglist = booking.getBookingList();
 
        booking.setBooking(name, userType, rewards, rooms, vipRooms, deluxeRooms, standardRooms);

        for (BookingList booking: bookinglist) {
        	if (booking.getName().equals(name)) {
        		assertEquals(expectedVipRooms, booking.getVip()); 				
                assertEquals(expectedDeluxeRooms, booking.getDeluxe()); 		
                assertEquals(expectedStandardRooms, booking.getStandard());
        	}
        }
    }
    
  //Available room is 2, 2, 2
    private Object[] getParamsforSetBookingTestValidV2() throws Exception {
    	return getParamsFromTextValid("getParamsforSetBookingTestValidV2.txt");
    }
    
    @Test
    @Parameters(method="getParamsforSetBookingTestValidV2")
    public void testSetBookingValidV2(String name, String userType, boolean rewards, int rooms, boolean vipRooms, boolean deluxeRooms, boolean standardRooms,
                               int expectedVipRooms, int expectedDeluxeRooms, int expectedStandardRooms) {
    	when(userMock.getMemberType()).thenReturn(userType);
        when(roomMock.getVip()).thenReturn(2);
        when(roomMock.getDeluxe()).thenReturn(2);
        when(roomMock.getStandard()).thenReturn(2);
        ArrayList<BookingList> bookinglist = booking.getBookingList();
 
        booking.setBooking(name, userType, rewards, rooms, vipRooms, deluxeRooms, standardRooms);

        for (BookingList booking: bookinglist) {
        	if (booking.getName().equals(name)) {
        		assertEquals(expectedVipRooms, booking.getVip()); 				
                assertEquals(expectedDeluxeRooms, booking.getDeluxe()); 		
                assertEquals(expectedStandardRooms, booking.getStandard());
        	}
        }
    }
    
    //Available room is 1, 1, 1
    private Object[] getParamsforSetBookingTestValidV3() throws Exception {
    	return getParamsFromTextValid("getParamsforSetBookingTestValidV3.txt");
    }
    
    @Test
    @Parameters(method="getParamsforSetBookingTestValidV3")
    public void testSetBookingValidV3(String name, String userType, boolean rewards, int rooms, boolean vipRooms, boolean deluxeRooms, boolean standardRooms,
                               int expectedVipRooms, int expectedDeluxeRooms, int expectedStandardRooms) {
    	when(userMock.getMemberType()).thenReturn(userType);
        when(roomMock.getVip()).thenReturn(1);
        when(roomMock.getDeluxe()).thenReturn(1);
        when(roomMock.getStandard()).thenReturn(1);
        ArrayList<BookingList> bookinglist = booking.getBookingList();
 
        booking.setBooking(name, userType, rewards, rooms, vipRooms, deluxeRooms, standardRooms);

        for (BookingList booking: bookinglist) {
        	if (booking.getName().equals(name)) {
        		assertEquals(expectedVipRooms, booking.getVip()); 				
                assertEquals(expectedDeluxeRooms, booking.getDeluxe()); 		
                assertEquals(expectedStandardRooms, booking.getStandard());
        	}
        }
    }
    
    //Invalid test
    private Object[] getParamsforSetBookingTestInvalid() throws Exception {
    	return getParamsFromTextInvalid("getParamsforSetBookingTestInvalid.txt");
    }
    
    @Test(expected = IllegalArgumentException.class)
    @Parameters(method="getParamsforSetBookingTestInvalid")
    public void testSetBookingInvalidV3(String userType, boolean rewards, int rooms) {
        when(userMock.getMemberType()).thenReturn(userType);
        when(roomMock.getVip()).thenReturn(3);
        when(roomMock.getDeluxe()).thenReturn(3);
        when(roomMock.getStandard()).thenReturn(3);
       
        booking.setBooking("Ali", userType, rewards, rooms, true, true, true);
    }
}
