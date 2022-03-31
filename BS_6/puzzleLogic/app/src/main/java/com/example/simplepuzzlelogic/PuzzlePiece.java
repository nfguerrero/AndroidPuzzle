package com.example.simplepuzzlelogic;

import android.content.Context;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * This class creates an object with the attributes listed below
 * it is my goal to add the edge creation of the puzzle pieces to this class
 */
public class PuzzlePiece extends AppCompatImageView {
    public int xCoord;
    public int yCoord;
    public int pieceWidth;
    public int pieceHeight;
    public boolean canMove = true;

    public PuzzlePiece(Context context) {
        super(context);
    }

}