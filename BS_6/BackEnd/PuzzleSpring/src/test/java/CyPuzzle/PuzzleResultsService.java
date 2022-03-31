package CyPuzzle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cyPuzzle.model.PuzzleResults;
import cyPuzzle.repository.PuzzleResultsRepository;

@Service
public class PuzzleResultsService {
	@Autowired
	private PuzzleResultsRepository prr;
	
	public List<PuzzleResults> findByDifficultyID(int id) {
		return prr.findByDifficultyID(id);
	}
	public List<PuzzleResults> findByUserID(int id) {
		return prr.findByUserID(id);
	}
}
