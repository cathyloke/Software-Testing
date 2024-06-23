package st_assignment;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

public class UserTest {

	@Test
	public void testReadUsersFromFile() {
	    try {
	        // Read users from user.txt file
	    	ArrayList<User> users = User.readUsersFromFile("user.txt");

	        // Define expected properties arrays
	        String[] expectedNames = {"Weng Yan", "Le Yun", "Jia Zheng", "Hui Hui"};
	        String[] expectedMemberTypes = {"VIP", "Normal", "Non-member", "Normal"};

	        // Extract properties from users list
	        String[] actualNames = users.stream().map(User::getName).toArray(String[]::new);
	        String[] actualMemberTypes = users.stream().map(User::getMemberType).toArray(String[]::new);

	        // Verify arrays are equal
	        assertArrayEquals(expectedNames, actualNames);
	        assertArrayEquals(expectedMemberTypes, actualMemberTypes);
	    } catch (IOException e) {
	        fail("Error occurred during test: " + e.getMessage());
	    }
	}

	@Test
    public void testWriteUsersToFile() {
        try {
            // Read users from user.txt file
        	ArrayList<User> users = User.readUsersFromFile("user.txt");

            // Write users to a temporary file
            User.writeUsersToFile(users, "test_users_write.txt");

            // Read users from the written file
            ArrayList<User> readUsers = User.readUsersFromFile("test_users_write.txt");

            // Verify number of users and their properties
            assertEquals(users.size(), readUsers.size());
            for (int i = 0; i < users.size(); i++) {
                assertEquals(users.get(i).getName(), readUsers.get(i).getName());
                assertEquals(users.get(i).getMemberType(), readUsers.get(i).getMemberType());
            }

            // Delete the temporary file
            File file = new File("test_users_write.txt");
            file.delete();
        } catch (IOException e) {
            fail("Error occurred during test: " + e.getMessage());
        }
    }
	
	@Test
    public void testUpdateMemberTypeValid() {
        try {
            // Open user.txt file for reading
            BufferedReader reader = new BufferedReader(new FileReader("user.txt"));

            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into name and membership type
                String[] userData = line.split("|");
                String name = userData[0];
                String memberType = userData[1];

                // Create a User object
                User user = new User(name, memberType);

                // Test updating membership type with valid values
                user.updateMemberType("VIP");
                assertEquals("VIP", user.getMemberType());
            }

            // Close file reader
            reader.close();
        } catch (IOException e) {
            fail("Error reading users from file: " + e.getMessage());
        }
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testUpdateMemberTypeInvalid() {
        try {
            // Open users.txt file for reading
            BufferedReader reader = new BufferedReader(new FileReader("user.txt"));

            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into name and membership type
                String[] userData = line.split("|");
                String name = userData[0];
                String memberType = userData[1];

                // Create a User object
                User user = new User(name, memberType);

                user.updateMemberType("Gold"); // Attempting to update to an invalid membership type
            }

            // Close file reader
            reader.close();
        } catch (IOException e) {
            fail("Error reading users from file: " + e.getMessage());
        }
    }
}
