package CyPuzzle;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.Before;

import cyPuzzle.model.*;
import cyPuzzle.repository.UsersRepository;
public class UsersTest {

	@InjectMocks
	UsersService urService;

	@Mock
	UsersRepository usr;

	@Before
	public void initMocks() {
	    MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getUsersByUserIDTest() {
        long millis=System.currentTimeMillis();  
        java.sql.Date date=new java.sql.Date(millis);  
        List<Users> list = new ArrayList<Users>(); 
		Users user = new Users(110, "TestBoi", "password321", 1, date, "TestBoi@newuser.yup");
		Users user2 = new Users(111, "TestGal", "password321", 1, date, "TestGal@newuser.yup");
		list.add(user);
		list.add(user2);
		when(usr.findByUserID(110)).thenReturn(list);
		List<Users> ur = urService.findByUserID(110);
		
		//should be of size 1
		assertEquals(2, ur.size());
		//should have called findByUserID(110) once
		verify(usr, times(1)).findByUserID(110);
		//should not have called findByUserID(111) at all
		verify (usr, times(0)).findByUserID(111);
		
		Iterator i = list.iterator();
		Users test = (Users) i.next();
		assertEquals((int)test.getUserID(), (int) 110);
		assertEquals(test.getUserName(), "TestBoi");
		assertEquals(test.getEmail(), "TestBoi@newuser.yup");
		
	}
	@Test
	public void getUsersByUserNameTest() {
        long millis=System.currentTimeMillis();  
        java.sql.Date date=new java.sql.Date(millis);  
        List<Users> list = new ArrayList<Users>(); 
		Users user = new Users(110, "TestBoi", "password321", 1, date, "TestBoi@newuser.yup");
		Users user2 = new Users(111, "TestGal", "password321", 1, date, "TestGal@newuser.yup");
		list.add(user);
		list.add(user2);
		when(usr.findByUserName("TestBoi")).thenReturn(list);
		List<Users> ur = urService.findByUserName("TestBoi");
		
		//should be of size 2
		assertEquals(2, ur.size());
		//should have called findByUserName("TestBoi") once
		verify(usr, times(1)).findByUserName("TestBoi");
		//should not have called findByUserName("TestGal") at all
		verify (usr, times(0)).findByUserName("TestGal");
		
		Iterator i = list.iterator();
		Users test = (Users) i.next();
		assertEquals((int)test.getUserID(), (int) 110);
		assertEquals(test.getUserName(), "TestBoi");
		assertEquals(test.getEmail(), "TestBoi@newuser.yup");
		
	}
}
	