package com.example.cypuzzle2;

import android.content.Context;
import android.graphics.Bitmap;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * This class creates an object with the attributes listed below
 *
 */
public class PuzzlePiece extends AppCompatImageView {
    public int xCoord;
    public int yCoord;
    public int pieceWidth;
    public int pieceHeight;
    public boolean canMove = true;
    public Bitmap puzbitm;
    public boolean selected = false;

    public PuzzlePiece(Context context) { super(context); }
}