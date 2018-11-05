package com.example.admin.cdnimageloader.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.admin.cdnimageloader.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageSlidePageFragment extends Fragment {

    @BindView(R.id.image)
    public ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        ButterKnife.bind(this, view);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("za.evrest.mobi")
                .appendPath("4aee7ef6039f904931081a30bf98de8818ae".concat(".jpg"))
                .appendQueryParameter("rmode", "scale")
                .appendQueryParameter("w", String.valueOf(width))
                .appendQueryParameter("h", String.valueOf(height));

        String imageUrl = builder.build().toString();
        Picasso.get().load(imageUrl).into(imageView);

        return view;
    }
}
