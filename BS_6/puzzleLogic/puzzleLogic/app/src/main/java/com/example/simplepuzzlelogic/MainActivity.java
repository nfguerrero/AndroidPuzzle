package com.example.simplepuzzlelogic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.lang.Math;



/**
 * main.. starting to burrito this code
 *
 */
public class MainActivity extends AppCompatActivity {
    //ArrayList<Bitmap> pieces;
    ArrayList<PuzzlePiece> pieces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //final ConstraintLayout layout = findViewById(R.id.layout);
        final RelativeLayout layout = findViewById(R.id.layout);
        ImageView imageView = findViewById(R.id.imageView);

        // calc dimensions
        imageView.post(new Runnable() {
            @Override
            public void run() {
                pieces = divideImage();
                TouchListener touchListener = new TouchListener();
//                for(Bitmap piece : pieces) {
//                    ImageView iv = new ImageView(getApplicationContext());
//                    iv.setImageBitmap(piece);
//                    iv.setOnTouchListener(touchListener);
//                    layout.addView(iv);
                   for(PuzzlePiece piece : pieces) {
                    piece.setOnTouchListener(touchListener);
                    layout.addView(piece);
                }
            }
        });
    }

    /**
     * divides the image into 12 puzzle pieces then draws the edges for the pieces
     * the edge drawing should ideally be moved into the PuzzlePiece class for modularity
     * @return the arraylist of pieces that are created from the image
     */
    //private ArrayList<Bitmap> divideImage() {
      private ArrayList<PuzzlePiece> divideImage() {
        int piecesNumber = 12;
        int rows = 4;
        int cols = 3;

        ImageView imageView = findViewById(R.id.imageView);
        //ArrayList<Bitmap> pieces = new ArrayList<>(piecesNumber);
          ArrayList<PuzzlePiece> pieces = new ArrayList<>(piecesNumber);

        // create scaled bitmap from image
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        int[] dimensions = getBmpPosInsideImageView(imageView);
        int scaledBitmapLeft = dimensions[0];
        int scaledBitmapTop = dimensions[1];
        int scaledBitmapWidth = dimensions[2];
        int scaledBitmapHeight = dimensions[3];

        int croppedImageWidth = scaledBitmapWidth - 2 * java.lang.Math.abs(scaledBitmapLeft);
        int croppedImageHeight = scaledBitmapHeight - 2 * java.lang.Math.abs(scaledBitmapTop);

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, scaledBitmapWidth, scaledBitmapHeight, true);
        Bitmap croppedBitmap = Bitmap.createBitmap(scaledBitmap, java.lang.Math.abs(scaledBitmapLeft), java.lang.Math.abs(scaledBitmapTop), croppedImageWidth, croppedImageHeight);


        // find width and height of pieces (could be hardcoded)
        int pieceWidth = bitmap.getWidth()/cols;
        int pieceHeight = bitmap.getHeight()/rows;

        // create each piece as a bitmap
        int yCoord = 0;
        for (int row = 0; row < rows; row++) {
            int xCoord = 0;
            for (int col = 0; col < cols; col++) {
                //pieces.add(Bitmap.createBitmap(croppedBitmap, xCoord, yCoord, pieceWidth, pieceHeight));
                //Bitmap pieceBitmap = Bitmap.createBitmap(croppedBitmap, xCoord, yCoord, pieceWidth, pieceHeight);
                // calculate offset for each piece
                int offsetX = 0;
                int offsetY = 0;
                if (col > 0) {
                    offsetX = pieceWidth / 3;
                }
                if (row > 0) {
                    offsetY = pieceHeight / 3;
                }

                // apply the offset to each piece
                Bitmap pieceBitmap = Bitmap.createBitmap(croppedBitmap, xCoord - offsetX, yCoord - offsetY, pieceWidth + offsetX, pieceHeight + offsetY);
                PuzzlePiece piece = new PuzzlePiece(getApplicationContext());
                piece.setImageBitmap(pieceBitmap);
                //piece.xCoord = xCoord + imageView.getLeft();
                //piece.yCoord = yCoord + imageView.getTop();
                //piece.pieceWidth = pieceWidth;
                //piece.pieceHeight = pieceHeight;
                piece.xCoord = xCoord - offsetX + imageView.getLeft();
                piece.yCoord = yCoord - offsetY + imageView.getTop();
                piece.pieceWidth = pieceWidth + offsetX;
                piece.pieceHeight = pieceHeight + offsetY;

                // this bitmap has final puzzle piece image
                Bitmap puzzlePiece = Bitmap.createBitmap(pieceWidth + offsetX, pieceHeight + offsetY, Bitmap.Config.ARGB_8888);

                // draw path
                int bumpSize = pieceHeight / 4;
                Canvas canvas = new Canvas(puzzlePiece);
                Path path = new Path();
                path.moveTo(offsetX, offsetY);
                if (row == 0) {
                    // top edge
                    path.lineTo(pieceBitmap.getWidth(), offsetY);
                } else {
                    // top male
                    path.lineTo(offsetX + (pieceBitmap.getWidth() - offsetX) / 3, offsetY);
                    path.cubicTo(offsetX + (pieceBitmap.getWidth() - offsetX) / 6, offsetY - bumpSize, offsetX + (pieceBitmap.getWidth() - offsetX) / 6 * 5, offsetY - bumpSize, offsetX + (pieceBitmap.getWidth() - offsetX) / 3 * 2, offsetY);
                    path.lineTo(pieceBitmap.getWidth(), offsetY);
                }

                if (col == cols - 1) {
                    // right edge
                    path.lineTo(pieceBitmap.getWidth(), pieceBitmap.getHeight());
                } else {
                    // right male
                    path.lineTo(pieceBitmap.getWidth(), offsetY + (pieceBitmap.getHeight() - offsetY) / 3);
                    path.cubicTo(pieceBitmap.getWidth() - bumpSize,offsetY + (pieceBitmap.getHeight() - offsetY) / 6, pieceBitmap.getWidth() - bumpSize, offsetY + (pieceBitmap.getHeight() - offsetY) / 6 * 5, pieceBitmap.getWidth(), offsetY + (pieceBitmap.getHeight() - offsetY) / 3 * 2);
                    path.lineTo(pieceBitmap.getWidth(), pieceBitmap.getHeight());
                }

                if (row == rows - 1) {
                    // bottom edge
                    path.lineTo(offsetX, pieceBitmap.getHeight());
                } else {
                    // bottom male
                    path.lineTo(offsetX + (pieceBitmap.getWidth() - offsetX) / 3 * 2, pieceBitmap.getHeight());
                    path.cubicTo(offsetX + (pieceBitmap.getWidth() - offsetX) / 6 * 5,pieceBitmap.getHeight() - bumpSize, offsetX + (pieceBitmap.getWidth() - offsetX) / 6, pieceBitmap.getHeight() - bumpSize, offsetX + (pieceBitmap.getWidth() - offsetX) / 3, pieceBitmap.getHeight());
                    path.lineTo(offsetX, pieceBitmap.getHeight());
                }

                if (col == 0) {
                    // left edge
                    path.close();
                } else {
                    // left male
                    path.lineTo(offsetX, offsetY + (pieceBitmap.getHeight() - offsetY) / 3 * 2);
                    path.cubicTo(offsetX - bumpSize, offsetY + (pieceBitmap.getHeight() - offsetY) / 6 * 5, offsetX - bumpSize, offsetY + (pieceBitmap.getHeight() - offsetY) / 6, offsetX, offsetY + (pieceBitmap.getHeight() - offsetY) / 3);
                    path.close();
                }

                // mask edges
                Paint paint = new Paint();
                paint.setColor(0XFF000000);
                paint.setStyle(Paint.Style.FILL);

                canvas.drawPath(path, paint);
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                canvas.drawBitmap(pieceBitmap, 0, 0, paint);

                // draw white border
                Paint border = new Paint();
                border.setColor(0X80FFFFFF);
                border.setStyle(Paint.Style.STROKE);
                border.setStrokeWidth(8.0f);
                canvas.drawPath(path, border);

                // draw black border
                border = new Paint();
                border.setColor(0X80000000);
                border.setStyle(Paint.Style.STROKE);
                border.setStrokeWidth(3.0f);
                canvas.drawPath(path, border);

                // set the resulting bitmap to the piece
                piece.setImageBitmap(puzzlePiece);
                pieces.add(piece);
                xCoord += pieceWidth;
            }
            yCoord += pieceHeight;
        }

        return pieces;
    }

    /**
     * finds position of piece and scales it to size of img file
     * @param imageView this parameter currently a hardcoded jpg file,
     * but will implement functionality to import images from gallery
     * @return position of the piece
     */
    private int[] getBmpPosInsideImageView(ImageView imageView) {
        int[] pos = new int[4];

        if (imageView == null || imageView.getDrawable() == null)
            return pos;


        // find image matrix values and place them in array
        float[] f = new float[9];
        imageView.getImageMatrix().getValues(f);

        // find scale values should be scaleX=scaleY
        final float scaleX = f[Matrix.MSCALE_X];
        final float scaleY = f[Matrix.MSCALE_Y];

        // find drawable
        final Drawable d = imageView.getDrawable();
        final int origW = d.getIntrinsicWidth();
        final int origH = d.getIntrinsicHeight();

        // calculate dimensions of piece
        final int actW = Math.round(origW * scaleX);
        final int actH = Math.round(origH * scaleY);

        pos[2] = actW;
        pos[3] = actH;

        // get img position assuming that it's centered
        int imgViewW = imageView.getWidth();
        int imgViewH = imageView.getHeight();

        int top = (int) (imgViewH - actH)/2; //ide says that this casting is redundant but a tutorial instructed to do it this way
        int left = (int) (imgViewW - actW)/2;

        pos[0] = left;
        pos[1] = top;

        return pos;
    }
}
