package com.example.admin.cdnimageloader.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Address;
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
import com.example.admin.cdnimageloader.ui.AppContract;
import com.example.admin.cdnimageloader.ui.adapter.ImagePagerAdapter;
import com.example.admin.cdnimageloader.ui.Presenter;
import com.example.admin.cdnimageloader.ui.service.AddressLocationService;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageSlidePageFragment extends Fragment implements AppContract.View {

    private String imageId;

    @BindView(R.id.image)
    public ImageView imageView;

    @BindView(R.id.title)
    public TextView title;

    @BindView(R.id.footer)
    public TextView footer;

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

        if (information == null)
            new Presenter(this).getIPInformation();

        getActivity().registerReceiver(receiver,
                new IntentFilter(AddressLocationService.ACTION_SEND_ADDRESS));

        return view;
    }

    @Override
    public void showImageTitle(IPLookUpInformation information) {
        this.information = information;
        title.setText(information.getCity().concat(", ").concat(information.getTimezone()));
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction() == AddressLocationService.ACTION_SEND_ADDRESS) {
                Address address = intent.getParcelableExtra(AddressLocationService.ADDRESS_EXTRA);
                footer.setText(address.getAddressLine(0));
            }
        }
    };
}
