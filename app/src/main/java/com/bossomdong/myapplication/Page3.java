package com.bossomdong.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Page3 extends AppCompatActivity {

    private Gameview gameview;
    private Gameview2 gameview2;

    private PrefsUtils prefsUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);

        gameview = new Gameview(this);
        gameview2 = new Gameview2(this);

        prefsUtils = new PrefsUtils();
        switch (prefsUtils.getModel(this)) {
            case PrefsUtils.MODEL_MAN:
                setContentView(gameview);
                break;
            case PrefsUtils.MODEL_WOMEN:
                setContentView(gameview2);
                break;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        gameview.resume();
        gameview2.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameview.pause();
        gameview2.resume();
    }

    class Gameview extends SurfaceView implements Runnable {
        private Thread gameThread;
        private SurfaceHolder ourHolder;
        private volatile boolean playing;
        private Canvas canvas;
        private Bitmap bitmapRunningMan;
        private boolean isMoving;
        private float runSpeedPerSecond = 2; //original is 200
        private float manXPos = 0, manYPos = 0;
        private int frameWidth = 400, frameHeigth = 490;
        private int frameCount = 15;
        private int currentFrame = 0; //original is 0
        private long fps;
        private long timeThisFrame;
        private long lastFrameChangeTime = 1000;
        private int frameLengthInMillisecond = 1000;

        private Rect frameTodraw = new Rect(200, 6, frameWidth, frameHeigth);

        private RectF whereToDraw = new RectF(manXPos, manYPos, manXPos + frameWidth, frameHeigth);

        public Gameview(Context context) {
            super(context);
            ourHolder = getHolder();
            bitmapRunningMan = BitmapFactory.decodeResource(getResources(), R.drawable.step3man15);
            bitmapRunningMan = Bitmap.createScaledBitmap(bitmapRunningMan, frameWidth * frameCount, frameHeigth, false);
        }

        @Override
        public void run() {
            while (playing) {
                long startFrameTime = System.currentTimeMillis();
                update();
                draw();

                timeThisFrame = System.currentTimeMillis();

                if (timeThisFrame >= 1) {
                    fps = 1000 / timeThisFrame;
                    // System.out.println("boss"+timeThisFrame);
                }
            }
        }

        public void update() {
            if (isMoving) {
                manXPos = manXPos + runSpeedPerSecond / 75;
                //System.out.println("boss"+manXPos+"and"+manYPos);

                if (manXPos + frameWidth - 100 > getWidth()) {
                    //System.out.println("boss"+getWidth());
                    manYPos += (int) frameHeigth;
                    manXPos = 10;
                }
                if (manYPos + frameHeigth > getHeight()) {
                    manYPos = 10;
                }
            }
        }

        public void manageCurrentFrame() {
            long time = System.currentTimeMillis();

            if (isMoving) {
                if (time > lastFrameChangeTime + frameLengthInMillisecond) {
                    lastFrameChangeTime = time;
                    currentFrame++;
                    if (currentFrame >= frameCount) {
                        currentFrame = 1;
                    }
                }
            }
            frameTodraw.left = currentFrame * frameWidth;
            frameTodraw.right = frameTodraw.left + frameWidth;
        }

        public void draw() {
            if (ourHolder.getSurface().isValid()) {
                canvas = ourHolder.lockCanvas();
                canvas.drawColor(Color.WHITE);
                whereToDraw.set((int) manXPos, (int) manYPos, (int) manXPos + frameWidth, (int) manYPos + frameHeigth);
                manageCurrentFrame();
                canvas.drawBitmap(bitmapRunningMan, frameTodraw, whereToDraw, null);
                ourHolder.unlockCanvasAndPost(canvas);
            }
        }

        public void pause() {
            playing = false;

            try {
                gameThread.join();
            } catch (InterruptedException e) {
                Log.e("ERR", "Joining Thread");
            }
        }

        public void resume() {
            playing = true;
            gameThread = new Thread(this);
            gameThread.start();
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    isMoving = !isMoving;
                    break;
            }

            return true;
        }
    }

    class Gameview2 extends SurfaceView implements Runnable {
        private Thread gameThread;
        private SurfaceHolder ourHolder;
        private volatile boolean playing;
        private Canvas canvas;
        private Bitmap bitmapRunningMan;
        private boolean isMoving;
        private float runSpeedPerSecond = 2; //original is 200
        private float manXPos = 0, manYPos = 0;
        private int frameWidth = 400, frameHeigth = 490;
        private int frameCount = 15;
        private int currentFrame = 0; //original is 0
        private long fps;
        private long timeThisFrame;
        private long lastFrameChangeTime = 1000;
        private int frameLengthInMillisecond = 1000;

        private Rect frameTodraw = new Rect(200, 6, frameWidth, frameHeigth);

        private RectF whereToDraw = new RectF(manXPos, manYPos, manXPos + frameWidth, frameHeigth);

        public Gameview2(Context context) {
            super(context);
            ourHolder = getHolder();
            bitmapRunningMan = BitmapFactory.decodeResource(getResources(), R.drawable.step3women15);
            bitmapRunningMan = Bitmap.createScaledBitmap(bitmapRunningMan, frameWidth * frameCount, frameHeigth, false);
        }

        @Override
        public void run() {
            while (playing) {
                long startFrameTime = System.currentTimeMillis();
                update();
                draw();

                timeThisFrame = System.currentTimeMillis();

                if (timeThisFrame >= 1) {
                    fps = 1000 / timeThisFrame;
                    // System.out.println("boss"+timeThisFrame);
                }
            }
        }

        public void update() {
            if (isMoving) {
                manXPos = manXPos + runSpeedPerSecond / 75;
                //System.out.println("boss"+manXPos+"and"+manYPos);

                if (manXPos + frameWidth - 100 > getWidth()) {
                    //System.out.println("boss"+getWidth());
                    manYPos += (int) frameHeigth;
                    manXPos = 10;
                }
                if (manYPos + frameHeigth > getHeight()) {
                    manYPos = 10;
                }
            }
        }

        public void manageCurrentFrame() {
            long time = System.currentTimeMillis();

            if (isMoving) {
                if (time > lastFrameChangeTime + frameLengthInMillisecond) {
                    lastFrameChangeTime = time;
                    currentFrame++;
                    if (currentFrame >= frameCount) {
                        currentFrame = 1;
                    }
                }
            }
            frameTodraw.left = currentFrame * frameWidth;
            frameTodraw.right = frameTodraw.left + frameWidth;
        }

        public void draw() {
            if (ourHolder.getSurface().isValid()) {
                canvas = ourHolder.lockCanvas();
                canvas.drawColor(Color.WHITE);
                whereToDraw.set((int) manXPos, (int) manYPos, (int) manXPos + frameWidth, (int) manYPos + frameHeigth);
                manageCurrentFrame();
                canvas.drawBitmap(bitmapRunningMan, frameTodraw, whereToDraw, null);
                ourHolder.unlockCanvasAndPost(canvas);
            }
        }

        public void pause() {
            playing = false;

            try {
                gameThread.join();
            } catch (InterruptedException e) {
                Log.e("ERR", "Joining Thread");
            }
        }

        public void resume() {
            playing = true;
            gameThread = new Thread(this);
            gameThread.start();
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    isMoving = !isMoving;
                    break;
            }

            return true;
        }
    }
}