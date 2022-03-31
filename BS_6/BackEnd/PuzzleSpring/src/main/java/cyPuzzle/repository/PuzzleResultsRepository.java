package cyPuzzle.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cyPuzzle.model.*;
/**
 * 
 * This class serves as the repository for PuzzleResults. 
 *
 */
@Repository
public interface PuzzleResultsRepository extends JpaRepository<PuzzleResults, Integer>{
    @Query("SELECT distinct ur FROM PuzzleResults ur WHERE ur.PuzzlesID = :ID")
    public List<PuzzleResults> findByPuzzlesID(@Param("ID") Integer ID);
    
    @Query("SELECT a FROM PuzzleResults a WHERE UserID = :ID")
    public List<PuzzleResults> findByUserID(@Param("ID") Integer ID);
    
    @Query("SELECT a FROM PuzzleResults a WHERE DifficultyID = :ID")
    public List<PuzzleResults> findByDifficultyID(@Param("ID") Integer ID);
    
    @Query("SELECT a FROM Puzzles a WHERE DifficultyID = :ID")
    public List<Puzzles> findPuzzlesByDifficultyID(@Param("ID") Integer ID);
}
