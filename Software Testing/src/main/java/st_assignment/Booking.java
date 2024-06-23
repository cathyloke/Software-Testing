package st_assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/*
 * The Booking class manages booking operations for rooms. It allows users to make bookings,
 * cancel bookings, and handles waiting list functionalities when rooms are unavailable.
 */

public class Booking {
	private Room room;
    private User user;
    private WaitingList waitingList;
    
    ArrayList<BookingList> bookingList = new ArrayList<>();

    public ArrayList<BookingList> getBookingList() {
    	return bookingList;
    }
    
    public void setBookingList(ArrayList<BookingList> bookingList) {
    	this.bookingList = bookingList;
    }
    
    // Constructors
    public Booking(User user, WaitingList waitingList) {
        this.user = user;
        this.waitingList = waitingList;
    }
   
    public Booking(Room room, User user, WaitingList waitingList) {
        this.room = room;
        this.user = user;
        this.waitingList = waitingList;
    }

 // Sets a booking based on the provided parameters.
    public void setBooking(String name, String memberType, boolean rewards, int rooms, boolean vipRooms, boolean deluxeRooms, boolean standardRooms) {
    	BookingList booking = new BookingList(name, 0, 0, 0);
    	bookingList = booking.readBookingListFile();
    	int vip = 0;
        int deluxe = 0;
        int standard = 0;
        WaitingList waitingList = new WaitingList();
        // Validate booking parameters
        if (rooms <= 0 || rooms > 3 || (memberType.equals("Normal") && rooms == 3)
        	    || (memberType.equals("Non-member") && rooms >= 2)
        	    || (memberType.equals("Non-member") && rewards == true)) {
            throw new IllegalArgumentException();
        }
    
        int availableVIPRoom = 0, availableDeluxeRoom = 0, availableStandardRoom = 0;
        if (memberType.equals("Normal") && rewards == false)
        	availableVIPRoom = 0;
        else if (vipRooms == true)
        	availableVIPRoom = room.getVip();
        if (deluxeRooms == true)
        	availableDeluxeRoom = room.getDeluxe();
        if (standardRooms == true)
        	availableStandardRoom = room.getStandard();
        
		boolean loop = (availableVIPRoom + availableDeluxeRoom + availableStandardRoom) >= rooms;
		
		// Check room availability and membership type
		if (loop == false || (vipRooms == false && deluxeRooms == false && standardRooms == false) || (memberType.equals("Non-member") && standardRooms == false)
				|| (memberType.equals("Normal") && rewards == false && deluxeRooms == false && standardRooms == false)) {
			// Add user to waiting list if rooms are unavailable
			waitingList.addWaiting(new User(name, memberType));
		} else if (memberType.equals("Normal") && rewards == false && vipRooms == false && deluxeRooms == false && standardRooms == false) {
			throw new IllegalArgumentException();
		} else {
			// Book rooms according to membership type
			while(loop && rooms > 0) {	
				if (memberType.equals("VIP") && rooms <= 3) { // Book VIP rooms
					if (vipRooms == true && rooms > 0 && availableVIPRoom > 0) {
		        		
		        		if (availableVIPRoom < rooms) {
		        			vip = availableVIPRoom;
		        		} else if (availableVIPRoom >= rooms) {
		        			vip = rooms;
		        		}	
		        		rooms -= vip;
		        		availableVIPRoom -= vip;		
		        	} else if (deluxeRooms == true && rooms > 0 && availableDeluxeRoom > 0) { 
		        		if (availableDeluxeRoom < rooms) {
		        			deluxe = availableDeluxeRoom;
		        		} else if (availableDeluxeRoom >= rooms) {
		        			deluxe = rooms;
		        		}	
		        		rooms -= deluxe;
		        		availableDeluxeRoom -= deluxe;
		        	} else if (standardRooms == true && rooms > 0 && availableStandardRoom > 0) {
		        		
		        		if (availableStandardRoom < rooms) {
		        			standard = availableStandardRoom;
		        		} else if (availableStandardRoom >= rooms) {
		        			standard = rooms;
		        		}	
		        		rooms -= standard;
		        		availableStandardRoom -= standard;	        		
		        	}
	        	} else if (memberType.equals("Normal") && rooms <= 2) { // Book rooms for Normal members
			        	if (vipRooms == true && rewards == true && availableVIPRoom > 0) {
			        		if (availableVIPRoom < rooms) {
			        			vip = availableVIPRoom;
			        		} else if (availableVIPRoom >= rooms) {
			        			vip = rooms;
			        		}	
			        		rooms -= vip;
			        		availableVIPRoom -= vip;
			  
			        	} else if (deluxeRooms == true && availableDeluxeRoom > 0) {
			        		if (availableDeluxeRoom < rooms) {
			        			deluxe = availableDeluxeRoom;
			        		} else if (availableDeluxeRoom >= rooms) {
			        			deluxe = rooms;
			        		}	
			        		rooms -= deluxe;
			        		availableDeluxeRoom -= deluxe;	
			        	} else if (standardRooms == true && availableStandardRoom > 0) {
			        		if (availableStandardRoom < rooms) {
			        			standard = availableStandardRoom;
			        		} else if (availableStandardRoom >= rooms) {
			        			standard = rooms;
			        		}	
			        		rooms -= standard;
			        		availableStandardRoom -= standard;	
			        	}
	        	} else if (memberType.equals("Non-member") && rooms <= 1) { // Book rooms for Non-members
		        	if (standardRooms == true && availableStandardRoom > 0) {
		        		if (availableStandardRoom < rooms) {
		        			standard = availableStandardRoom;
		        		} else if (availableStandardRoom >= rooms) {
		        			standard = rooms;
		        		}	
		        		rooms -= standard;
		        		availableStandardRoom -= standard;	
		        	}
	        	} else
	        		throw new IllegalArgumentException();	//check the member type
			}      	
	        bookingList.add(new BookingList(name, vip, deluxe, standard));
		}
		booking.writeBookingListFile(bookingList);
    }
    
    // Cancels a booking associated with provided name
    public void cancelBooking(String name) {
    	BookingList booking = new BookingList(name, 0, 0, 0);
    	bookingList = booking.readBookingListFile();
    	boolean delete = false;
    	if (name == null) {
    		throw new IllegalArgumentException();
    	} else {
    		for(int i = 0; i < bookingList.size(); i++) {
    			if (bookingList.get(i).getName().equals(name)) {
    				bookingList.remove(i);
    				delete = true;
    				booking.writeBookingListFile(bookingList);
    			}
    		}
    	}
    	if (delete == false)
    		throw new IllegalArgumentException();
    }
}

// BookingList class represents a list of bookings and provides methods to read from and write to a file.
class BookingList {
	private String name;
	private int vip;
    private int deluxe;
    private int standard;
    ArrayList<BookingList> bookingList = new ArrayList<>();
    
    public String getName() {
    	return name;
    }
    public int getVip() {
    	return vip;
    }
    public int getDeluxe() {
    	return deluxe;
    }
    public int getStandard() {
    	return standard;
    }
    
    // Constructor
    public BookingList(String name, int vip, int deluxe, int standard) {
    	this.name = name;
    	this.vip = vip;
    	this.deluxe = deluxe;
    	this.standard = standard;
    } 
    
    // Reads booking data from bookingList.txt and populates the booking list.
    public ArrayList <BookingList> readBookingListFile() {
		try {
			// Create a new file object
			FileReader fileReader = new FileReader ("bookingList.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			// Read each line of the file and add it to the ArrayList
			String line;
	        while ((line = bufferedReader.readLine()) != null) {
                String[] fields = line.split("\\|");
                
                String name = fields[0];
                int vip = Integer.parseInt(fields[1]);
                int deluxe = Integer.parseInt(fields[2]);
                int standard = Integer.parseInt(fields[3]);

                bookingList.add(new BookingList(name, vip, deluxe, standard));
	        }
	        
	        // Close the BufferedWriter and FileWriter
	        bufferedReader.close();
	        fileReader.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return bookingList;
	}
    
    // Writes booking data from the booking list to bookingList.txt
    public void writeBookingListFile(ArrayList<BookingList> bookingList) {
		try {
	        // Create a new FileWriter object with the file path as parameter
	        FileWriter fileWriter = new FileWriter("bookingList.txt");
	
	        // Create a new BufferedWriter object to write data to the file
	        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	        
	        // Write data to the file
	        String line;
            for (BookingList booking : bookingList) {
            	line = String.format("%s|%s|%s|%s",
            			booking.getName(),
            			booking.getVip(),
            			booking.getDeluxe(),
            			booking.getStandard());
            
            	bufferedWriter.write(line);
            	bufferedWriter.newLine(); // add new line character
            }
	        // Close the BufferedWriter and FileWriter
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
