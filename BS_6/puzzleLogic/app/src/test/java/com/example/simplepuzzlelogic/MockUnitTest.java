package com.example.simplepuzzlelogic;

import org.junit.Test;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Equals;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;

import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

/**
 * Mockito test with 3 tests
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MockUnitTest {

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

    /**
     * This should always be true as of now, but the pieces are locking into place.
     * This test should help to change that functionality
     * @throws JSONException when the piece cannot move
     */
    @Test
    public void puzzlePieceMovableTest_returnsTrue() throws JSONException {
        PuzzlePiece mockPiece = Mockito.mock(PuzzlePiece.class);
        assertTrue(mockPiece.canMove);
    }


//    @Test
//    public void workingTouchListenerTest() throws JSONException{
//        TouchListener mockTouch = Mockito.mock(TouchListener.class);
//        PuzzlePiece mockPiece = Mockito.mock(PuzzlePiece.class);
//        mockPiece.canMove=true;
//        mockTouch.onTou
//    }

    /**
     * This tests the correct division of the image
     * @throws JSONException if pieces were not created
     */
    @Test
    public void divideImageTest() throws JSONException{
        MainActivity mockOnCreate = Mockito.mock(MainActivity.class);
        assertTrue(mockOnCreate.pieces!=null);

    }


    @Test
    public void pieceLocked_returnsTrue() throws JSONException {
        PuzzlePiece mockPiece = Mockito.mock(PuzzlePiece.class);
        Mockito.mock(MainActivity.class);
        assertTrue(MainActivity.isLocked(mockPiece));
        assertFalse(mockPiece.canMove);
    }

    @Test
    public void puzzleCompleted_returnsTrue() throws JSONException {
        Mockito.mock(MainActivity.class);
        ArrayList<PuzzlePiece> MockArray= mock(MainActivity.class).pieces;
        int len=MockArray.size();
        for(int i=0; i<len; i++){
            assertTrue(MainActivity.isLocked(MockArray.get(i)));
        }

    }


}