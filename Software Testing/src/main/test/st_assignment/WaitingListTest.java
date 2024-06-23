package st_assignment;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class WaitingListTest {
	
	private WaitingList waitList;
	private User userMock;
	
	@Before
    public void setUp() {
		userMock = mock(User.class);
		waitList = new WaitingList(userMock);

    }

    private Object[] getParamsforTestAddWaiting() {
    	return new Object[] {
    			new Object[] {"Zorian", "Normal", "normal.txt"},
    			new Object[] {"Ali", "VIP", "VIP.txt"},
    			new Object[] {"Abu", "Non-member", "nonMember.txt"}
    	};
    }
    	
    @Test
    @Parameters(method="getParamsforTestAddWaiting")
	public void testAddWaiting(String name, String memberType, String filename) {
		User userAdd = new User(name, memberType);
		waitList.addWaiting(userAdd);
		
		ArrayList<User> userList = waitList.getWaiting(memberType, filename);
		
		boolean found = false;
        for(User user: userList) {
        	if (user.getName().equals(name) && user.getMemberType().equals(memberType)) {
        		found = true;
        	}
        }     
        assertTrue(found); 	
	}
	
    private Object[] getParamsforTestAddWaitingInvalid() {
    	return new Object[] {
    			new Object[] {"Zorian", "VVIP", "VIP.txt"},
    			new Object[] {null, "Normal", "normal.txt"},
    			new Object[] {"Zorian", null, "normal.txt"},
    			new Object[] {null, null, "normal.txt"}
    	};
    }
    
    @Test(expected=IllegalArgumentException.class)
    @Parameters(method="getParamsforTestAddWaitingInvalid")
	public void testAddWaitingInvalid(String name, String memberType, String filename) {
		User userAdd = new User(name, memberType);
		waitList.addWaiting(userAdd);
		ArrayList<User> userList = waitList.getWaiting(memberType, filename);
	}
    
    private Object[] getWaitingListTest() {
    	return new Object[] {
    			new Object[] {"VIP", "VIP.txt", "Syarizah"},
    			new Object[] {"VIP", "VIP.txt", "Daphne"},
    			new Object[] {"VIP", "VIP.txt", "Samuel"},
    			new Object[] {"Normal", "normal.txt", "Jessyln"},
    			new Object[] {"Normal", "normal.txt", "Mohammad"},
    			new Object[] {"Normal", "normal.txt", "Jordan"},
    			new Object[] {"Non-member", "nonMember.txt", "Madeline"},
    			new Object[] {"Non-member", "nonMember.txt", "Hui Xuan"},
    			new Object[] {"Non-member", "nonMember.txt", "Sabrina"},
    			new Object[] {"Non-member", "nonMember.txt", "Nicholas"}
    			};
    }
    
    @Test
	@Parameters(method="getWaitingListTest")
	public void testGetWaiting(String memberType, String filename, String name) {
		try {
			
			boolean found = false;
			ArrayList<User> user = waitList.getWaiting(memberType, filename);
			for(User u: user) {
	        	if (u.getName().equals(name)) {
	        		found = true;
	        	}
	        }
			
			assertTrue(found);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    private Object[] getWaitingListTestInvalid() {
    	return new Object[] {
    			new Object[] {"VVIP", "VIP.txt"},
    			new Object[] {"Family", "normal.txt"},
    			new Object[] {"Staff", "nonMember.txt"},
    	};
    }
    
    @Test(expected=IllegalArgumentException.class)
    @Parameters(method="getWaitingListTestInvalid")
	public void testGetWaitingInvalid(String memberType, String filename) {
		ArrayList<User> user = waitList.getWaiting(memberType, filename);
    }
    
    private Object[] getParamsforTestRemoveWaiting() {
    	return new Object[] {
    			new Object[] {"Zorian", "Normal", "normal.txt"},
    			new Object[] {"Ali", "VIP", "VIP.txt"},
    			new Object[] {"Abu", "Non-member", "nonMember.txt"}
    			
    	};
    }
    	
    @Test
    @Parameters(method="getParamsforTestRemoveWaiting")
	public void testRemoveWaiting(String name, String memberType, String filename) {
		User userDelete = new User(name, memberType);
		ArrayList<User> userList = waitList.getWaiting(memberType, filename);
		
		waitList.removeWaiting(userDelete, filename, userList);
		
		ArrayList<User> userCheck = waitList.getWaiting(memberType, filename);
		
		boolean found = false;
        for(User user: userCheck) {
        	if (user.getName().equals(name) && user.getMemberType().equals(memberType)) {
        		found = true;
        	}
        }     
        assertFalse(found);
	}
}
    