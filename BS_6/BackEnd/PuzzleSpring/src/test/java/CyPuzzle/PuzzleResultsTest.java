package CyPuzzle;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.Before;

import cyPuzzle.model.*;
import cyPuzzle.repository.PuzzleResultsRepository;
public class PuzzleResultsTest {

	@InjectMocks
	PuzzleResultsService prService;

	@Mock
	PuzzleResultsRepository prr;

	@Before
	public void initMocks() {
	    MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getPuzzleResultssByDifficultyIDTest() {
        long millis=System.currentTimeMillis();  
        java.sql.Date date=new java.sql.Date(millis);  
        List<PuzzleResults> list = new ArrayList<PuzzleResults>();
		PuzzleResults puzzleResult = new PuzzleResults(2, 4, 1, date, 1, "Medium1", 2, "Medium", "Justn");
		PuzzleResults puzzleResult2 = new PuzzleResults(1, 1, 1, date, 1, "Medium2", 2, "Medium", "Justn");
		list.add(puzzleResult);
		list.add(puzzleResult2);
		when(prr.findByDifficultyID(2)).thenReturn(list);
		List<PuzzleResults> pr = prService.findByDifficultyID(2);
		
		//should be of size 2
		assertEquals(2, pr.size());
		//should have called findByDifficultyID(2) once
		verify(prr, times(1)).findByDifficultyID(2);
		//should not have called findByDifficultyID(1) at all
		verify(prr, times(0)).findByDifficultyID(1);
		
		Iterator i = list.iterator();
		PuzzleResults test = (PuzzleResults) i.next();
		assertEquals((int)test.getPuzzleResultsID(), (int) 2);
		assertEquals((int) test.getPuzzleID(),(int) 4);
		assertEquals((int)test.getUserID(), (int) 1);
		assertEquals(test.getPuzzleName(), "Medium1");
		assertEquals((int) test.getDifficultyID(), (int) 2);
		assertEquals(test.getDifficultyName(), "Medium");
		assertEquals(test.getUserName(), "Justn");
		
	}
	
	@Test
	public void getPuzzleResultsByUserIDTest() {
        long millis=System.currentTimeMillis();  
        java.sql.Date date=new java.sql.Date(millis);  
        List<PuzzleResults> list = new ArrayList<PuzzleResults>();
		PuzzleResults puzzleResult = new PuzzleResults(1, 6, 3, date, 1, "Medium1", 2, "Medium", "Justn");
		list.add(puzzleResult);
		PuzzleResultsRepository prr = mock(PuzzleResultsRepository.class);
		when(prr.findByUserID(3)).thenReturn(list);
		List<PuzzleResults> pr = prr.findByUserID(3);
		
		//should be of size 1
		assertEquals(1, pr.size());
		//should have called findByUserID(3)) once
		verify(prr, times(1)).findByUserID(3);
		//should not have called findByUserID(1) at all
		verify(prr, times(0)).findByUserID(1);
		
		Iterator i = list.iterator();
		PuzzleResults test = (PuzzleResults) i.next();
		assertEquals((int)test.getPuzzleResultsID(), (int) 1);
		assertEquals((int) test.getPuzzleID(),(int) 6);
		assertEquals((int)test.getUserID(), (int) 3);
		assertEquals(test.getPuzzleName(), "Medium1");
		assertEquals((int) test.getDifficultyID(), (int) 2);
		assertEquals(test.getDifficultyName(), "Medium");
		assertEquals(test.getUserName(), "Justn");
		
	}
	
	@Test
	public void getPuzzleResultsByPuzzlesIDTest() {
		long millis=System.currentTimeMillis();  
        java.sql.Date date=new java.sql.Date(millis);  
        List<PuzzleResults> list = new ArrayList<PuzzleResults>();
		PuzzleResults puzzleResult = new PuzzleResults(1, 1, 1, date, 1, "Easy1", 3, "Easy", "Justn");
		PuzzleResults puzzleResult2 = new PuzzleResults(2, 1, 2, date, 1, "Easy1", 3, "Easy", "Squishy");
		list.add(puzzleResult);
		list.add(puzzleResult2);
		PuzzleResultsRepository prr = mock(PuzzleResultsRepository.class);
		when(prr.findByPuzzlesID(1)).thenReturn(list);
		List<PuzzleResults> pr = prr.findByPuzzlesID(1);
		
		//should be of size 2
		assertEquals(2, pr.size());
		//should have called findByPuzzlesID(1)) once
		verify(prr, times(1)).findByPuzzlesID(1);
		//should not have called findByPuzzlesID(4) at all
		verify(prr, times(0)).findByPuzzlesID(4);
		
		Iterator i = list.iterator();
		PuzzleResults test = (PuzzleResults) i.next();
		assertEquals((int)test.getPuzzleResultsID(), (int) 1);
		assertEquals((int) test.getPuzzleID(),(int) 1);
		assertEquals((int)test.getUserID(), (int) 1);
		assertEquals(test.getPuzzleName(), "Easy1");
		assertEquals((int) test.getDifficultyID(), (int) 3);
		assertEquals(test.getDifficultyName(), "Easy");
		assertEquals(test.getUserName(), "Justn");
	}
	
	@Test
	public void getPuzzlesByDifficultyIDTest() {
		long millis=System.currentTimeMillis();  
        java.sql.Time date=new java.sql.Time(millis);  
        List<Puzzles> list = new ArrayList<Puzzles>();
		Puzzles puzzle = new Puzzles(1, "Easy1", 3, 1, date);
		Puzzles puzzle2 = new Puzzles(2, "Easy2", 3, 1, date);
		list.add(puzzle);
		list.add(puzzle2);
		PuzzleResultsRepository prr = mock(PuzzleResultsRepository.class);
		when(prr.findPuzzlesByDifficultyID(3)).thenReturn(list);
		List<Puzzles> pr = prr.findPuzzlesByDifficultyID(3);
		
		//should be of size 2
		assertEquals(2, pr.size());
		//should have called findPuzzlesByDifficultyID(3)) once
		verify(prr, times(1)).findPuzzlesByDifficultyID(3);
		//should not have called findPuzzlesByDifficultyID(2) at all
		verify(prr, times(0)).findPuzzlesByDifficultyID(2);
		
		Iterator i = list.iterator();
		Puzzles test = (Puzzles) i.next();
		assertEquals((int)test.getPuzzlesID(), (int) 1);
		assertEquals(test.getPuzzleName(), "Easy1");
		assertEquals((int)test.getdifficultyID(), (int) 3);
		assertEquals((int)test.getActive(), (int) 1);
		assertEquals(test.getDateCreated(), date);
	}
}
