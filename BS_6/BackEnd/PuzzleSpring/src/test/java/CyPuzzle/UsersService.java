package CyPuzzle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cyPuzzle.model.Users;
import cyPuzzle.repository.UsersRepository;

@Service
public class UsersService {
	@Autowired
	private UsersRepository usr;
	
	public List<Users> findByUserID(int id) {
		return usr.findByUserID(id);
	}
	public List<Users> findByUserName(String UserName) {
		return usr.findByUserName(UserName);
	}
}
