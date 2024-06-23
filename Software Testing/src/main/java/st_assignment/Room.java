package st_assignment;

// The Room class represents a hotel room with different types (VIP, Deluxe, Standard).

public class Room {
	private int vip;
    private int deluxe;
    private int standard;
	
    // Constructor
    public Room(int vip, int deluxe, int standard) {
        this.vip = vip;
        this.deluxe = deluxe;
        this.standard = standard;
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
    
    public void setVip(int vip) {
        this.vip = vip;
    }

    public void setDeluxe(int deluxe) {
        this.deluxe = deluxe;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }
	
    public boolean checkRoom(String roomType) {
        // Logic to check room availability based on room type
        return true; // Placeholder return value
    }
}

