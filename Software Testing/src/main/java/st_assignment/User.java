package st_assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

// The User class represents a user in the system, including their name, membership type, and booking information.

public class User {
	private String name;
	private String memberType;
	private int numberOfRoomsBooked;
	public boolean exclReward;
	
	// Constructor
	public User(String name, String memberType) {
        this.name = name;
        this.memberType = memberType;
        this.numberOfRoomsBooked = 0;
        this.exclReward = false;
    }
	
	public String getName() {
		return name;
	}
	
	public String getMemberType() {
		return memberType;
	}
	
	public boolean getExclReward() {
		return exclReward;
	}
	
	public void setName(String name) {
        this.name = name;
    }
	
	public void setMemberType(String type) {
		this.memberType = memberType;
	}
	
	public void setExclReward(boolean reward) {
	    this.exclReward = reward;
	}
	
	// Books a room for the user of the specified type and updates the number of rooms booked.
	public void bookRoom(String roomType, int numberOfRooms) {
		numberOfRoomsBooked += numberOfRooms;
	}
	
	// Updates the membership type of the user to the specified new membership type.
	public void updateMemberType(String newMemberType) {
		if (newMemberType != "VIP" && newMemberType != "Normal" && newMemberType != "Non-member") {
			throw new IllegalArgumentException("Invalid membership type specified");
		}
		
        if (newMemberType.equals("VIP") || newMemberType.equals("Normal") || newMemberType.equals("Non-member")) {
            this.memberType = newMemberType;
        }
    }
	
	// Reads user data from the specified file and returns a list of User objects.
	public static ArrayList<User> readUsersFromFile(String filename) throws IOException {
		ArrayList<User> users = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] userData = line.split("\\|");
            users.add(new User(userData[0], userData[1]));
        }
        reader.close();
        return users;
    }
	
	// Writes the user data from the specified ArrayList to the specified file.
	public static void writeUsersToFile(ArrayList<User> users, String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (User user : users) {
            writer.write(user.getName() + "|" + user.getMemberType());
            writer.newLine();
        }
        writer.close();
    }
}
