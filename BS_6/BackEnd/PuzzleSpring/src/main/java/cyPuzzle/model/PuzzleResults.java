package cyPuzzle.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * This class is the model for PuzzleResults.
 *
 */

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "PuzzleResults", schema = "PuzzleGame")
public class PuzzleResults {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "PuzzleResultsID")
	private Integer PuzzleResultsID;
	
	@Column(name = "PuzzlesID")
	private Integer PuzzlesID;
	
	@Column(name = "UserID")
	private Integer UserID;
	
	@Column(name = "Duration")
	private java.sql.Date Duration;
	
	@Column(name = "Active")
	private Integer Active;
	
	@Column(name = "PuzzleName")
	private String PuzzleName;
	
	@Column(name = "DifficultyID")
	private Integer DifficultyID;
	
	@Column(name = "DifficultyName")
	private String DifficultyName;
	
	@Column(name = "UserName")
	private String UserName;	
	
	/**
	 * Default Constructor
	 */
	public PuzzleResults() 
	{
		
	}
	
	/**
	 * Constructor containing all variables of a PuzzleResult
	 * 
	 * @param puzzleResultsId
	 * @param puzzlesId
	 * @param userId
	 * @param dur
	 * @param active
	 * @param name
	 * @param difficultyId
	 * @param difficultyName
	 * @param userName
	 */
	public PuzzleResults(int puzzleResultsId, int puzzlesId, int userId, java.sql.Date dur, int active, String name, int difficultyId, String difficultyName, String userName) {
		this.PuzzleResultsID = puzzleResultsId;
		this.PuzzlesID = puzzlesId;
		this.UserID = userId;
		this.Duration = dur;
		this.Active = active;
		this.PuzzleName = name;
		this.DifficultyID = difficultyId;
		this.DifficultyName = difficultyName;
		this.UserName = userName;
	}
	
	/**
	 * 
	 * @return PuzzleResultsID
	 */
	public Integer getPuzzleResultsID() {
		return PuzzleResultsID;
	}
	/**
	 * 
	 * @return PuzzlesID
	 */
	public Integer getPuzzleID() {
		return PuzzlesID;
	}
	/**
	 * 
	 * @return UserID
	 */
	public Integer getUserID() {
		return UserID;
	}
	/**
	 * 
	 * @return Duration
	 */
	public java.sql.Date getDuration() {
		return Duration;
	}
	/**
	 * 
	 * @return Active
	 */
	public Integer getActive() {
		return Active;
	}
	/**
	 * 
	 * @return PuzzleName
	 */
	public String getPuzzleName() {
		return PuzzleName;
	}
	/**
	 * 
	 * @return DifficultyID
	 */
	public Integer getDifficultyID() {
		return DifficultyID;
	}
	/**
	 * 
	 * @return DifficultyName
	 */
	public String getDifficultyName() {
		return DifficultyName;
	}
	/**
	 * 
	 * @return UserName
	 */
	public String getUserName() {
		return UserName;
	}		
	
	/**
	 * Set the PuzzleResultID.
	 * @param prID
	 */
	public void setPuzzleResultsID(Integer prID) {
		PuzzleResultsID = prID;
	}
	/**
	 * set the PuzzleID.
	 * @param pID
	 */
	public void setPuzzleID(Integer pID) {
		PuzzlesID = pID;
	}
	/**
	 * Set the UserID.
	 * @param uID
	 */
	public void setUserID(Integer uID) {
		UserID = uID;
	}
	/**
	 * Set the duration.
	 * @param d
	 */
	public void setDuration(java.sql.Date d) {
		Duration = d;
	}
	/**
	 * Set Active to 0 (inactive) or 1 (active)
	 * @param a
	 */
	public void setActive(Integer a) {
		Active = a;
	}
	/**
	 * Set the PuzzleName.
	 * @param pn
	 */
	public void setPuzzleName(String pn) {
		PuzzleName = pn;
	}
	/**
	 * Set the DifficultyId.
	 * @param dID
	 */
	public void setDifficultyID(Integer dID) {
		DifficultyID = dID;
	}
	/**
	 * Set the DifficultyName.
	 * @param dn
	 */
	public void setDifficultyName(String dn) {
		DifficultyName = dn;
	}
	/**
	 * Set the UserName.
	 * @param un
	 */
	public void setUserName(String un) {
		UserName = un;
	}		
//	public String toString() {
//		return "PuzzleResultsID: " + this.PuzzleResultsID + "\n" +
//				"UserName: " + this.UserName + "\n" +
//				"UserID: " + this.UserID + "\n" +
//				"PuzzleName: " + this.PuzzleName + "\n" +
//				"PuzzleID: " + this.PuzzlesID + "\n" +
//				"Completed: " + this.Completed.equals(1) + "\n" +
//				"DifficultyID: " + this.DifficultyID + "\n" + 
//				"DifficultyName: " + this.DifficultyName;
//	}
}