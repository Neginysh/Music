package com.example.music.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VerticalItemDecorator extends RecyclerView.ItemDecoration {

    private final int spaceHeight;

    public VerticalItemDecorator(int spaceHeight) {
        this.spaceHeight = spaceHeight;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.top = spaceHeight;
        outRect.left = spaceHeight/2;
        outRect.right = spaceHeight/2;
    }
}
