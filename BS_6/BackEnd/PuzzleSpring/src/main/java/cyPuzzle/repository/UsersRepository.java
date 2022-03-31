package cyPuzzle.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cyPuzzle.model.*;

/**
 * 
 * This class serves as the repository for Users.
 * 
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Integer>{
	
    @Query("SELECT a FROM Users a WHERE UserID = :ID")
    public List<Users> findByUserID(@Param("ID") Integer ID);
    
    @Query("SELECT a FROM Users a WHERE UserName = :UserName")
    public List<Users> findByUserName(@Param("UserName") String UserName);
    
    @Query("SELECT a FROM Users a WHERE Email = :Email")
    public List<Users> findByEmail(@Param("Email") String Email);
}
