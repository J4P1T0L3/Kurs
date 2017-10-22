package pl.japitole.znowniedzialatowideo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class OtwarzanieFilmu extends AppCompatActivity {
    private VideoView videoView;
    private MediaController mediaController;
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wybieranie_plikow);
        videoView = (VideoView) findViewById(R.id.videoview);

        if (mediaController == null){
            mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);

            videoView.setMediaController(mediaController);
        }
        Intent intent = getIntent();
        if (intent != null){
            String path = intent.getStringExtra("video_to_play");
            videoView.setVideoURI(Uri.parse(path));
            videoView.start();
        }
        videoView.requestFocus();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.seekTo(position);
                if (position ==0){
                    videoView.start();
                }
                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int i, int il) {
                        mediaController.setAnchorView(videoView);
                    }
                });
            }
        });
    }
    @Override
    protected void onSaveInstanceState (Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("current_position", videoView.getCurrentPosition());
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        position = savedInstanceState.getInt("current_position", 0);
        videoView.seekTo(position);
    }
    @Override
    protected void onPause(){
        super.onPause();
        videoView.pause();
    }

}
