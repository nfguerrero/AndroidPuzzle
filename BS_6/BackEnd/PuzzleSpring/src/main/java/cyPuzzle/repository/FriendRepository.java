package cyPuzzle.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cyPuzzle.model.*;

/**
 * 
 * This class serves as the repository for friendships.
 * 
 */
@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer>{
	
    @Query("SELECT a FROM Friend a WHERE UserID = :ID")
    public List<Friend> findFriendshipByUserID(@Param("ID") Integer ID);
}
