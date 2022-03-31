package cyPuzzle.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cyPuzzle.model.*;
import cyPuzzle.repository.*;
/**
 * 
 * This class is the controller for the Users.
 *
 */
@RestController
public class UsersController {
	
	@Autowired
	private UsersRepository repository;
	
	/**
	 * Returns the user with the given UserID.
	 * @param UserID
	 * @return user
	 */
	@GetMapping("/users/user:{UserID}")
	List<Users> getUserResults(@PathVariable Integer UserID) {
		List<Users> user = repository.findByUserID(UserID);
		return user;
	}
	@GetMapping("/users/username:{UserName}")
	List<Users> getUserResults(@PathVariable String UserName) {
		List<Users> user = repository.findByUserName(UserName);
		return user;
	}
	@GetMapping("/users/email:{Email}")
	List<Users> getUserByEmail(@PathVariable String Email) {
		List<Users> user = repository.findByEmail(Email);
		return user;
	}
}
