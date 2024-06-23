package st_assignment;
import java.util.Iterator;
import java.io.*;
import java.util.ArrayList;

// The WaitingList class represents a waiting list for different types of users (VIP, Normal, Non-member).

public class WaitingList {
    private ArrayList<User> VIP = new ArrayList<>();
    private ArrayList<User> normal = new ArrayList<>();
    private ArrayList<User> nonMember = new ArrayList<>();
    User user;
    ArrayList<User> list = new ArrayList<>();
    
    public ArrayList<User> getVIPList() {
    	return VIP;
    }
    
    public ArrayList<User> getNormalList() {
    	return normal;
    }
    
    public ArrayList<User> getNonMemberList() {
    	return nonMember;
    }
    
    // Constructors
    public WaitingList(User user) {
    	this.user = user;
    }
    
    public WaitingList() {
        VIP = new ArrayList<>();
        normal = new ArrayList<>();
        nonMember = new ArrayList<>();
    }

    // Add a user to the appropriate list and append them to the corresponding file
    public void addWaiting(User user) {
    	if (user == null || user.getMemberType() == null|| user.getName() == null) {
            // Handle null user case
            throw new IllegalArgumentException();
        }
    	try {
	       	if (user.getMemberType().equals("VIP")) {
	       		VIP.add(user);
	       		appendUserToFile(user, "VIP.txt");
	       	} else if (user.getMemberType().equals("Normal")) {
	       		normal.add(user);
	       		appendUserToFile(user, "normal.txt");
	       	} else if (user.getMemberType().equals("Non-member")) {
	            nonMember.add(user);
	            appendUserToFile(user, "nonMember.txt");
	       	} 
    	} catch (IOException e) {
            // Handle the IOException, e.g., log the error or throw a custom exception
            e.printStackTrace();
        }       
    }
    
    // Append a user to the text file without overwriting existing data
    private void appendUserToFile(User user, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(user.getName() + "|" + user.getMemberType());
            writer.newLine(); // Ensures each new user is added on a new line
        }
    }

    // Retrieve the appropriate list based on the given member type
    public ArrayList<User> getWaiting(String memberType, String filename) {	
    	try {
			// Create a new file object
			FileReader fileReader = new FileReader (filename);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			// Read each line of the file and add it to the ArrayList
			String line;
	        while ((line = bufferedReader.readLine()) != null) {
                String[] fields = line.split("\\|");
                
                String name = fields[0];
                String type = fields[1];

                if (memberType.equals("VIP"))
                	list.add(new User(name, type));
                else if (memberType.equals("Normal"))
                	list.add(new User(name, type));
                else if (memberType.equals("Non-member"))
                	list.add(new User(name, type));
                else
                	throw new IllegalArgumentException();
	        }
	        
	        // Close the BufferedWriter and FileWriter
	        bufferedReader.close();
	        fileReader.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return list;
    }

    
    // Remove a user from the appropriate list based on their member type
    public void removeWaiting(User userDelete, String filename, ArrayList<User> list) {
    	try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) {
            // Use iterator to safely remove elements while iterating over the list
            Iterator<User> iterator = list.iterator();
            while (iterator.hasNext()) {
                User user = iterator.next();
                if (user.getName().equals(userDelete.getName()) && user.getMemberType().equals(userDelete.getMemberType())) {
                    iterator.remove(); // Remove the current user from the list using the iterator
                }
            }

            // Write data to the file
            for (User user : list) {
                String line = String.format("%s|%s", user.getName(), user.getMemberType());
                bufferedWriter.write(line);
                bufferedWriter.newLine(); // add new line character
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
