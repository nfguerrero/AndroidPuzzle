package cyPuzzle.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "Puzzles", schema = "PuzzleGame")
public class Puzzles {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PuzzlesID")
	private Integer PuzzlesID;
	
	@Column(name = "PuzzleName")
	private String PuzzleName;	
	
	@Column(name = "difficultyID")
	private Integer difficultyID;
	
	@Column(name = "Active")
	private Integer Active;
	
	@Column(name = "DateCreated")
	private java.sql.Time DateCreated;
	
	/**
	 * Default Constructor for Puzzles class
	 */
	public Puzzles() {};
	
	/**
	 * Constructor for Puzzles class for manual variable input
	 * 
	 * @param pID
	 * @param pName
	 * @param dID
	 * @param a
	 * @param date
	 */
	public Puzzles(int pID, String pName, int dID, int a, java.sql.Time date)
	{
		this.PuzzlesID = pID;
		this.PuzzleName = pName;
		this.difficultyID = dID;
		this.Active = a;
		this.DateCreated = date;
	}
	
	/**
	 * Get the ID of the Puzzle
	 * @return PuzzlesID
	 */
	public Integer getPuzzlesID() {
		return PuzzlesID;
	}
	
	/**
	 * Get the Name of the Puzzle
	 * @return PuzzleName
	 */
	public String getPuzzleName() {
		return PuzzleName;
	}
	
	/**
	 * Get the Difficulty ID of the Puzzle
	 * @return difficultyID
	 */
	public Integer getdifficultyID() {
		return difficultyID;
	}
	
	/**
	 * Get the time the Puzzle was created
	 * @return DateCreated
	 */
	public java.sql.Time getDateCreated() {
		return DateCreated;
	}
	
	/**
	 * Get whether the Puzzle is active or not
	 * @return Active
	 */
	public Integer getActive() {
		return Active;
	}
}
