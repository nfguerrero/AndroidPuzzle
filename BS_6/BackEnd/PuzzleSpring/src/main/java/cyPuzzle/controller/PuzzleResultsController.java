package cyPuzzle.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cyPuzzle.model.*;
import cyPuzzle.repository.*;

/**
 * 
 * Controller class for PuzzleResults
 *
 */

@RestController()
public class PuzzleResultsController {
	
	@Autowired
	private PuzzleResultsRepository repository;
	
	/**
	 * The mapping is: "/results"
	 * @return all puzzle results
	 */
	@GetMapping(path="/results")
	List<PuzzleResults> getPuzzleResults() {
		List<PuzzleResults> results = repository.findAll();
		return results;
	}
	
	/**
	 * The mapping is: "/results/user:{UserID}"
	 * @param UserID
	 * @return puzzle results for a specific user
	 */
	@GetMapping("/results/user:{UserID}")
	List<PuzzleResults> getUserResults(@PathVariable Integer UserID) {
		List<PuzzleResults> userR = repository.findByUserID(UserID);
		return userR;
	}
	
	/**
	 * The mapping is: "/results/puzzle:{PuzzlesID}"
	 * @param PuzzlesID
	 * @return puzzle results for a specific puzzle
	 */
	@GetMapping("/results/puzzle:{PuzzlesID}")
	List<PuzzleResults> getByPuzzlesID(@PathVariable Integer PuzzlesID) {
		List<PuzzleResults> puzzleR = repository.findByPuzzlesID(PuzzlesID);
		return puzzleR;
	}
	
	/**
	 * The mapping is: "/results/difficulty:{DifficultyID}"
	 * @param DifficultyID
	 * @return puzzle results for a specific difficulty
	 */
	@GetMapping("/results/difficulty:{DifficultyID}")
	List<PuzzleResults> getByDifficultyID(@PathVariable Integer DifficultyID) {
		List<PuzzleResults> difficultyR = repository.findByDifficultyID(DifficultyID);
		return difficultyR;
	}
	
	/**
	 * The mapping is: "/puzzles/difficulty:{DifficultyID}"
	 * @param DifficultyID
	 * @return list of puzzles that have the given difficulty ID
	 */
	@GetMapping("/puzzles/difficulty:{DifficultyID}")
	List<Puzzles> getPuzzlesByDifficultyID(@PathVariable Integer DifficultyID) {
		List<Puzzles> difficultyR = repository.findPuzzlesByDifficultyID(DifficultyID);
		return difficultyR;
	}	
	
	/**
	 * Create a PuzzleResult given a PuzzleResult parameter.
	 * The mapping is: "/addResult"
	 * @param PuzzleResult pr
	 * @return created PuzzleResult pr
	 */
	// Create a puzzle result
	@PostMapping("/addResult")
	PuzzleResults createPuzzleResult(@RequestBody PuzzleResults pr) {
		repository.save(pr);
		return pr;
	}
	
	/**
	 * Test Auto-Deploy function
	 */
	@GetMapping("/test")
	String test() {
		return "Auto-Deploy Test!";
	}
	
}