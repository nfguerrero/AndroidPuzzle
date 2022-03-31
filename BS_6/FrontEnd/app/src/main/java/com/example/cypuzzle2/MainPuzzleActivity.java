package com.example.cypuzzle2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Collections;
import java.util.Random;



/**
 * Main java class
 * There are some methods in this class that should be moved to their own class files
 *
 */
public class MainPuzzleActivity extends AppCompatActivity{
    //ArrayList<Bitmap> pieces;
    ArrayList<PuzzlePiece> pieces;
    ImageButton button;
    float elapsed_time;
    String mCurrentPhotoPath;
    String mCurrentPhotoUri;
    ImageButton rotate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puzzle_main);


        button = findViewById(R.id.puzzle_back_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openMain();
            }
        });


        //final ConstraintLayout layout = findViewById(R.id.layout);
        final RelativeLayout layout = findViewById(R.id.layout);
        final ImageView imageView = findViewById(R.id.imageView);


        Intent intent = getIntent();
        final String assetName = intent.getStringExtra("assetName");
        mCurrentPhotoPath = intent.getStringExtra("mCurrentPhotoPath");
        mCurrentPhotoUri = intent.getStringExtra("mCurrentPhotoUri");

        rotate = findViewById(R.id.rotate_button);
        rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(PuzzlePiece piece : pieces){
                    if(piece.selected){
                        layout.removeView(piece);
                        piece.setImageBitmap(rotateImage(piece.puzbitm,90));
                        layout.addView(piece);
                    }
                }
            }
        });



        // calc dimensions
        imageView.post(new Runnable() {
            @Override
            public void run() {
                if (assetName != null) {
                    setPicFromAsset(assetName, imageView);
                }else if (mCurrentPhotoPath != null) {
                    setPicFromPhotoPath(mCurrentPhotoPath, imageView);
                }else if (mCurrentPhotoUri != null) {
                    imageView.setImageURI(Uri.parse(mCurrentPhotoUri));
                }
                pieces = divideImage();
                TouchListener touchListener = new TouchListener(MainPuzzleActivity.this);
                Collections.shuffle(pieces);
//                for(Bitmap piece : pieces) {
//                    ImageView iv = new ImageView(getApplicationContext());
//                    iv.setImageBitmap(piece);
//                    iv.setOnTouchListener(touchListener);
//                    layout.addView(iv);
                for (PuzzlePiece piece : pieces) {
                    piece.setOnTouchListener(touchListener);
                    layout.addView(piece);
                    RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) piece.getLayoutParams();
                    lParams.leftMargin = new Random().nextInt(layout.getWidth() - piece.pieceWidth);
                    lParams.topMargin = new Random().nextInt(layout.getHeight() - piece.pieceHeight);
                    piece.setLayoutParams(lParams);
                }
            }
        });
    }

    /**
     * helper method to open the Main Menu page
     */
    public void openMain(){
        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
        onBackPressed();
    }

    private void setPicFromAsset(String assetName, ImageView imageView) { //a lot of this was learned from a youtube tutorial by david king
        // Get the dimensions of the View
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();

        AssetManager am = getAssets();
        try {
            InputStream is = am.open("img/" + assetName);
            // Get the dimensions of the bitmap
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(is, new Rect(-1, -1, -1, -1), bmOptions);
            int photoW = bmOptions.outWidth;
            int photoH = bmOptions.outHeight;

            // Determine how much to scale down the image
            int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

            is.reset();

            // Decode the image file into a Bitmap sized to fill the View
            bmOptions.inJustDecodeBounds = false;
            bmOptions.inSampleSize = scaleFactor;
            bmOptions.inPurgeable = true;

            Bitmap bitmap = BitmapFactory.decodeStream(is, new Rect(-1, -1, -1, -1), bmOptions);
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * divides the image into 12 puzzle pieces then draws the edges for the pieces
     * the edge drawing should ideally be moved into the PuzzlePiece class for modularity
     *
     * @return the arraylist of pieces that are created from the image
     */
    //private ArrayList<Bitmap> divideImage() {
    private ArrayList<PuzzlePiece> divideImage() {
        int piecesNumber = 12;
        int rows = 4;
        int cols = 3;

        if(PuzzleDifficulty.getDiff() == 1){
            piecesNumber = 12;
            rows = 4;
            cols = 3;
        }

        if(PuzzleDifficulty.getDiff() == 2){
            piecesNumber = 24;
            rows = 6;
            cols = 4;
        }

        if(PuzzleDifficulty.getDiff() == 3){
            piecesNumber = 108;
            rows = 12;
            cols = 9;
        }




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
        int pieceWidth = bitmap.getWidth() / cols;
        int pieceHeight = bitmap.getHeight() / rows;

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


                // this bitmap is puzzle piece image without edges
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
                    path.cubicTo(pieceBitmap.getWidth() - bumpSize, offsetY + (pieceBitmap.getHeight() - offsetY) / 6, pieceBitmap.getWidth() - bumpSize, offsetY + (pieceBitmap.getHeight() - offsetY) / 6 * 5, pieceBitmap.getWidth(), offsetY + (pieceBitmap.getHeight() - offsetY) / 3 * 2);
                    path.lineTo(pieceBitmap.getWidth(), pieceBitmap.getHeight());
                }

                if (row == rows - 1) {
                    // bottom edge
                    path.lineTo(offsetX, pieceBitmap.getHeight());
                } else {
                    // bottom male
                    path.lineTo(offsetX + (pieceBitmap.getWidth() - offsetX) / 3 * 2, pieceBitmap.getHeight());
                    path.cubicTo(offsetX + (pieceBitmap.getWidth() - offsetX) / 6 * 5, pieceBitmap.getHeight() - bumpSize, offsetX + (pieceBitmap.getWidth() - offsetX) / 6, pieceBitmap.getHeight() - bumpSize, offsetX + (pieceBitmap.getWidth() - offsetX) / 3, pieceBitmap.getHeight());
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
                piece.puzbitm = puzzlePiece;
                pieces.add(piece);
                xCoord += pieceWidth;
            }
            yCoord += pieceHeight;
        }

        return pieces;
    }

    /**
     * finds position of piece and scales it to size of img file
     *
     * @param imageView this parameter currently a hardcoded jpg file,
     *                  but will implement functionality to import images from gallery
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

        int top = (int) (imgViewH - actH) / 2; //ide says that this casting is redundant but a tutorial instructed to do it this way
        int left = (int) (imgViewW - actW) / 2;

        pos[0] = left;
        pos[1] = top;

        return pos;
    }

    /**
     * helper method to open new puzzle page on button click
     */
    public void openNewPuzzle(){
        Intent intent = new Intent(this, NewPuzzleActivity.class);
        startActivity(intent);
    }

    /**
     * @param p , a PuzzlePiece
     * @return whether a puzzle piece is currently locked in
     */
    public static boolean isLocked(PuzzlePiece p) {
        return true;
    }

    /**
     * returns an array of puzzle pieces
     * helper method
     */
    public ArrayList<PuzzlePiece> returnPuzzlePiecesArray() {
        return pieces;
    }

    public float checkGameOver() {
        if (isGameOver()) {
            return elapsed_time;
        }
        return 0;
    }

    private boolean isGameOver() {
        for (PuzzlePiece piece : pieces) {
            if (piece.canMove) {
                return false;
            }
        }

        return true;
    }

    private void setPicFromPhotoPath(String mCurrentPhotoPath, ImageView imageView) {
        // Get the dimensions of the View
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        Bitmap rotatedBitmap = bitmap;


        try {
            ExifInterface ei = new ExifInterface(mCurrentPhotoPath);
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotatedBitmap = rotateImage(bitmap, 90);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotatedBitmap = rotateImage(bitmap, 180);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotatedBitmap = rotateImage(bitmap, 270);
                    break;
            }
        } catch (IOException e) {
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }

        imageView.setImageBitmap(rotatedBitmap);
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }

}