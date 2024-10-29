package com.joyp.healthandfitnessapp;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.ArrayAdapter;
import java.util.List;

public class VideoAdapter extends ArrayAdapter<Video> {

    public VideoAdapter(@NonNull Context context, @NonNull List<Video> videos) {
        super(context, 0, videos);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.video_item, parent, false);
        }

        Video video = getItem(position);

        TextView titleTextView = convertView.findViewById(R.id.video_title);
        VideoView videoView = convertView.findViewById(R.id.video_view);

        titleTextView.setText(video.getTitle());

        // Setup the VideoView
        Uri videoUri = Uri.parse(video.getUrl());
        videoView.setVideoURI(videoUri);
        MediaController mediaController = new MediaController(getContext());
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.requestFocus();
        videoView.start();

        return convertView;
    }
}
