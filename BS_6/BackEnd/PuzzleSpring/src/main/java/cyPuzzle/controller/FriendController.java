package cyPuzzle.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cyPuzzle.model.*;
import cyPuzzle.repository.*;
/**
 * 
 * This class is the controller for the friendships.
 *
 */
@RestController
public class FriendController {
	
	@Autowired
	private FriendRepository repository;
	
	/**
	 * Returns the users that are friends of the given UserID.
	 * @param UserID
	 * @return user
	 */
	@GetMapping("/friendship:{UserID}")
	List<Friend> getFriendships(@PathVariable Integer UserID) {
		List<Friend> friends = repository.findFriendshipByUserID(UserID);
		return friends;
	}
	/**
	 * Create a Friendship given a a userID and friendID.
	 * The mapping is: "/addFriendship"
	 * @param Friend friend
	 */
	@PostMapping("/addFriendship")
	void createFriendship(@RequestBody Friend friend) {
		repository.save(friend);
	}
}
