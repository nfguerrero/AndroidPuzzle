package com.cypuzzle.main;

import java.lang.reflect.Array;
import java.util.Random;
import java.util.ArrayList;

public class PuzzleLogic {
    private int columns;
    private int rows;
    private int dimensions;


    //constructor
    public PuzzleLogic(int cols, int rows) {
        setGrid(cols, rows);
    }

    //Set the grid size
    public void setGrid(int cols, int rows) {
        columns = cols;
        this.rows = rows;
        setDimensions(cols, rows);
    }
    public void setDimensions(int cols, int rows) {
        dimensions = cols * rows;
    }
    public ArrayList<PuzzlePiece> createPuzzlePieces() {
        ArrayList<PuzzlePiece> list = new ArrayList<PuzzlePiece>();

        //randomizer to create random cut ins and pop outs in the pieces
        Random random = new Random(2);
        int row = 0;
        int col = 0;


        for (int i = 0; i < this.dimensions - 1; i++) {
            PuzzlePiece p;
            int top = -2;
            int left = -2;
            int bottom = -2;
            int right = -2;

            //Piece is on the top row and top is an edge
            if (row == 0) {
                top = -1;
            }
            //Piece is in the bottom row and bottom is an edge
            else if (row == rows) {
                bottom = -1;
            }
            //Piece is not in the top or bottom rows
            else {
                for (PuzzlePiece piece : list) {
                    //if the piece is in the row above the piece under construction in the same column
                    if (piece.getRow() + 1 == row && piece.getCol() == col) {
                        if (piece.bottomIn() == true) {
                            top = 1;
                        }
                        else
                        {
                            top = 0;
                        }
                        break;
                    }
                }
            }
            //Piece is in the far left column and left is an edge
            if (col == 0) {
                left = -1;
            }
            //Piece is in the far right column and right is an edge
            else if (col == columns) {
                right = -1;
            }
            //Piece is not in the far left or far right columns
            else {
                for (PuzzlePiece piece : list) {
                    //if the piece is in the column to the left of the piece under construction and in the same row
                    if (piece.getCol() - 1 == col && piece.getRow() == row) {
                        if (piece.rightIn() == true) {
                            left = 1;
                        }
                        else {
                            left = 0;
                        }
                    }
                }
            }
            //Set bottom and right if not already set
            //Top and left are always set in above code
            if (bottom == -2) {
                bottom = random.nextInt();
            }
            if (right == -2) {
                right = random.nextInt();
            }
            p = new PuzzlePiece(row, col, top, bottom, left, right);
            list.add(p);
            col++;
            if (row >= this.rows) {
                col = 0;
                row += 1;
            }
        }
        return list;
    }

}