package st_assignment;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(value = Suite.class)
@SuiteClasses(value = { 
		UserTest.class,
		SetBookingTest.class,
		CancelBookingTest.class, 
		WaitingListTest.class})

public class TestSuite {

}
