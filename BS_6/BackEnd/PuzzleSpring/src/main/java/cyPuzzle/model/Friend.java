package cyPuzzle.model;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 
 * This class is the model for friendships.
 *
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "Friend", schema = "PuzzleGame")
public class Friend {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "FriendshipID")
	private Integer FriendshipID;
	
	@Column(name = "UserID")
	private Integer UserID;	
	
	@Column(name = "FriendID")
	private Integer FriendID;	
	
	
	/**
	 * Default Constructor.
	 */
	public Friend() 
	{
		
	}
	/**
	 * Constructor containing parameters for all the variables of a friendship.
	 * 
	 * @param FriendshipID
	 * @param UserID
	 * @param FriendID
	 */
	public Friend(int FriendshipID, int UserID, int FriendID) {
		this.FriendshipID = FriendshipID;
		this.UserID = UserID;
		this.FriendID = FriendID;
	}
	
	//Getter Methods
	
	/**
	 * 
	 * @return FriendshipID
	 */
	public int FriendshipID() {
		return FriendshipID;
	}
	/**
	 * 
	 * @return UserID
	 */
	public int getUserID() {
		return UserID;
	}	/**
	 * 
	 * @return FriendID
	 */
	public int getFriendID() {
		return FriendID;
	}
	
	//Setter Methods

	/**
	 * Set the FriendshipID.
	 * @param FriendshipID
	 */
	public void setFriendshipID(int FriendshipID) {
		this.FriendshipID = FriendshipID;
	}
	/**
	 * Set the UserID.
	 * @param UserID
	 */
	public void setUserID(int UserID) {
		this.UserID = UserID;
	}
	/**
	 * Set the FriendID.
	 * @param FriendID
	 */
	public void setFriendID(int FriendID) {
		this.FriendID = FriendID;
	}
	
}
