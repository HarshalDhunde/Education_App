package com.example.brainwired;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class Youtubepage extends AppCompatActivity {

    YouTubePlayerView youTubePlayerView;
    Button homebtn;
    private FullScreenHelper fullScreenHelper = new FullScreenHelper(this);
//    LifecycleOwner lifecycleOwner;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtube_activity);

        homebtn = findViewById(R.id.home);
        youTubePlayerView = findViewById(R.id.youtube_player_view);

        getLifecycle().addObserver(youTubePlayerView);

//        lifecycleOwner.getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "Y40J_DomBu4";
                youTubePlayer.loadVideo(videoId, 0);

            }
        });


        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iintent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(iintent);
            }
        });

    }


    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            youTubePlayerView.enterFullScreen();
        }
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            youTubePlayerView.exitFullScreen();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        youTubePlayerView.release();
    }




    /**
     * Class responsible for changing the view from full screen to non-full screen and vice versa.
     *
     * @author Pierfrancesco Soffritti
     */
    public class FullScreenHelper {

        private Activity context;
        private View[] views;

        /**
         * @param context
         * @param views to hide/show
         */
        public FullScreenHelper(Activity context, View ... views) {
            this.context = context;
            this.views = views;
        }

        /**
         * call this method to enter full screen
         */
        public void enterFullScreen() {
            View decorView = context.getWindow().getDecorView();

            hideSystemUi(decorView);

            for(View view : views) {
                view.setVisibility(View.GONE);
                view.invalidate();
            }
        }

        /**
         * call this method to exit full screen
         */
        public void exitFullScreen() {
            View decorView = context.getWindow().getDecorView();

            showSystemUi(decorView);

            for(View view : views) {
                view.setVisibility(View.VISIBLE);
                view.invalidate();
            }
        }

        private void hideSystemUi(View mDecorView) {
            mDecorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }

        private void showSystemUi(View mDecorView) {
            mDecorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }


}
