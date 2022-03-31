package CyPuzzle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cyPuzzle.model.Friend;
import cyPuzzle.repository.FriendRepository;

@Service
public class FriendService {
	@Autowired
	private FriendRepository friends;
	
	public List<Friend> findFriendshipByUserID(int id) {
		return friends.findFriendshipByUserID(id);
	}
}
