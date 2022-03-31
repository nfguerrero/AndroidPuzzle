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
import cyPuzzle.repository.FriendRepository;
public class FriendTest {

	@InjectMocks
	FriendService frService;

	@Mock
	FriendRepository friends;

	@Before
	public void initMocks() {
	    MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getFriendsByFriendshipIDTest() {
        List<Friend> list = new ArrayList<Friend>(); 
		Friend friendship1 = new Friend(1, 1, 2);
		Friend friendship2 = new Friend(2, 2, 1);
		list.add(friendship1);
		list.add(friendship2);
		when(friends.findFriendshipByUserID(1)).thenReturn(list);
		List<Friend> fr = frService.findFriendshipByUserID(1);
		
		//should be of size 2
		assertEquals(2, fr.size());
		//should have called findFriendshipByUserID(1) once
		verify(friends, times(1)).findFriendshipByUserID(1);
		//should not have called findFriendshipByUserID(2) at all
		verify (friends, times(0)).findFriendshipByUserID(2);
		
		Iterator i = list.iterator();
		Friend test = (Friend) i.next();
		assertEquals((int)test.getUserID(), (int) 1);
		assertEquals((int)test.getFriendID(), (int) 2);
		
	}
}
	