package com.example.admin.cdnimageloader.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.cdnimageloader.R;
import com.example.admin.cdnimageloader.api.response.IPLookUpInformation;
import com.squareup.picasso.Picasso;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageSlidePageFragment extends Fragment implements AppContract.View{

    private String imageId;

    @BindView(R.id.image)
    public ImageView imageView;

    @BindView(R.id.title)
    public TextView title;
    private IPLookUpInformation information;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        ButterKnife.bind(this, view);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        Bundle bundle = getArguments();
        imageId = bundle.getString(ImagePagerAdapter.IMAGE_ID);

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("za.evrest.mobi")
                .appendPath(imageId.concat(".jpg"))
                .appendQueryParameter("rmode", "scale")
                .appendQueryParameter("w", String.valueOf(width))
                .appendQueryParameter("h", String.valueOf(height));

        String imageUrl = builder.build().toString();
        Picasso.get().load(imageUrl).into(imageView);

        if(information == null)
            new Presenter(this).getIPInformation();

        return view;
    }

    @Override
    public void showImageTitle(IPLookUpInformation information) {
        this.information = information;
        title.setText( information.getCity().concat(", ").concat(information.getTimezone()));
    }
}
