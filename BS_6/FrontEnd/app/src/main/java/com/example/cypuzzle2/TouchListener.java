package com.example.cypuzzle2;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static java.lang.StrictMath.abs;

/**
 * This class will allow the user to move the pieces
 */
public class TouchListener implements View.OnTouchListener {
    private float xDelta;
    private float yDelta;
    private MainPuzzleActivity activity;
    //boolean selected = false;

    public TouchListener(MainPuzzleActivity activity) {
        this.activity = activity;
    }


    /**
     * This method calculates the movement of the piece
     * @param view refers to which screen the app is on
     * @param motionEvent the movement of the user's finger
     * @return return true if no errors
     */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        float x = motionEvent.getRawX();
        float y = motionEvent.getRawY();


        final double tolerance = sqrt(pow(view.getWidth(), 2) + pow(view.getHeight(), 2)) / 10;

        PuzzlePiece piece = (PuzzlePiece) view;
        if (!piece.canMove) {
            return true;
        }




        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                xDelta = x-lParams.leftMargin;
                yDelta = y-lParams.topMargin;
                piece.bringToFront();
                if(piece.selected == true) {
                    piece.setColorFilter(null);
                    piece.selected = false;
                    break;
                }
                piece.setColorFilter(Color.BLUE);
                piece.selected = true;
                break;
            case MotionEvent.ACTION_MOVE:
                lParams.leftMargin = (int) (x-xDelta); //cast from float to int
                lParams.topMargin = (int) (y-yDelta);
                view.setLayoutParams(lParams);
                break;

            case MotionEvent.ACTION_UP:
                int xDiff = abs(piece.xCoord - lParams.leftMargin);
                int yDiff = abs(piece.yCoord - lParams.topMargin);
                if (xDiff <= tolerance && yDiff <= tolerance) {
                    lParams.leftMargin = piece.xCoord;
                    lParams.topMargin = piece.yCoord;
                    piece.setLayoutParams(lParams);
                    piece.canMove = false;
                    sendViewToBack(piece);
                    activity.checkGameOver();
                    piece.setColorFilter(null);
                    piece.selected = false;
                }

                break;
        }
        return true;
    }

        public void sendViewToBack(final View child) {
         final ViewGroup parent = (ViewGroup)child.getParent();
         if (null != parent) {
            parent.removeView(child);
            parent.addView(child, 0);
         }
    }
}


