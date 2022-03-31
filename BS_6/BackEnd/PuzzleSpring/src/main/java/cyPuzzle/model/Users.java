package cyPuzzle.model;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 
 * This class is the model for users.
 *
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "Users", schema = "PuzzleGame")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "UserID")
	private Integer UserID;
	
	@Column(name = "UserName")
	private String UserName;	
	
	@Column(name = "Password")
	private String Password;
	
	@Column(name = "Active")
	private Integer Active;
	
	@Column(name = "DateCreated")
	private java.sql.Date DateCreated;
	
	@Column(name = "Email")
	private String Email;
	
	/**
	 * Default Constructor.
	 */
	public Users() 
	{
		
	}
	/**
	 * Constructor containing parameters for all the variables of a user.
	 * 
	 * @param UserID
	 * @param UserName
	 * @param Password
	 * @param Active
	 * @param DateCreated
	 * @param Email
	 */
	public Users(int UserID, String UserName, String Password, Integer Active, java.sql.Date DateCreated, String Email) {
		this.UserID = UserID;
		this.UserName = UserName;
		this.Password = Password;
		this.Active = Active;
		this.DateCreated = DateCreated;
		this.Email = Email;
	}
	
	//Getter Methods
	
	/**
	 * 
	 * @return UserID
	 */
	public int getUserID() {
		return UserID;
	}
	/**
	 * 
	 * @return UserName
	 */
	public String getUserName() {
		return UserName;
	}
	/**
	 * 
	 * @return Password
	 */
	public String getPassword() {
		return Password;
	}
	/**
	 * 
	 * @return Active
	 */
	public int getActive() {
		return Active;
	}
	/**
	 * 
	 * @return DateCreated
	 */
	public java.sql.Date getDateCreated() {
		return DateCreated;
	}
	/**
	 * 
	 * @return Email
	 */
	public String getEmail() {
		return Email;
	}
	
	//Setter Methods
	
	/**
	 * Set the UserID.
	 * @param UserID
	 */
	public void setUserID(int UserID) {
		this.UserID = UserID;
	}
	/**
	 * Set the UserName.
	 * @param UserName
	 */
	public void setUserName(String UserName) {
		this.UserName = UserName;
	}
	/**
	 * Set the Password.
	 * @param Password
	 */
	public void setPassword(String Password) {
		this.Password = Password;
	}
	/**
	 * Set Active to 0 (inactive) or 1 (active)
	 * @param Active
	 */
	public void setActive(int Active) {
		this.Active = Active;
	}
	/**
	 * Set the DateCreated.
	 * @param DateCreated
	 */
	public void setDateCreated(java.sql.Date DateCreated) {
		this.DateCreated = DateCreated;
	}
	/**
	 * Set the Email.
	 * @param Email
	 */
	public void setEmail(String Email) {
		this.Email = Email;
	}
	
}
