package com.cypuzzle.main;

public class PuzzlePiece {
    private int row;
    private int col;

    private int top;
    private int bottom;
    private int left;
    private int right;

    private int topMatched;
    private int bottomMatched;
    private int leftMatched;
    private int rightMatched;

    //constructor
    //-1 means the piece is an edge
    //0 means the piece is cut into
    //1 means the piece has a pop-out

    //In regards to matching:
    //-1 means the piece is an edge piece and cannot be matched
    //0 means the piece is not matched
    //1 means the piece is matched
    public PuzzlePiece(int row, int col, int top, int bottom, int left, int right) {
        this.row = row;
        this.col = col;

        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;

        if (topIsEdge()) {topMatched = -1;}
        else {topMatched = 0;}
        if (bottomIsEdge()) {bottomMatched = -1;}
        else {bottomMatched = 0;}
        if (leftIsEdge()) {leftMatched = -1;}
        else {leftMatched = 0;}
        if (topIsEdge()) {rightMatched = -1;}
        else {rightMatched = 0;}
    }

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public boolean topOut () {
        return top == 1;
    }
    public boolean topIn () {
        return top == 0;
    }
    public boolean topIsEdge () {
        return top == -1;
    }
    public boolean bottomOut () {
        return bottom == 1;
    }
    public boolean bottomIn () {
        return bottom == 0;
    }
    public boolean bottomIsEdge () {
        return bottom == -1;
    }
    public boolean leftOut () {
        return left == 1;
    }
    public boolean leftIn () {
        return left == 0;
    }
    public boolean leftIsEdge () {
        return left == -1;
    }
    public boolean rightOut () {
        return right == 1;
    }
    public boolean rightIn () {
        return right == 0;
    }
    public boolean rightIsEdge () {
        return right == -1;
    }

    public void setTopMatched(int n) {
        topMatched = n;
    }
    public void setBottomMatched(int n) {
        bottomMatched = n;
    }
    public void setLeftMatched(int n) {
        leftMatched = n;
    }
    public void setRightMatched(int n) {
        rightMatched = n;
    }
}
