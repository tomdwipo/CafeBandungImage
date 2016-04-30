package com.tommyputranto.tes2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PhotoGalleryFragment extends Fragment {
    private static final String TAG = "PhotoGalleryFragment";

    private RecyclerView mPhotoRecyclerView;
    private List<GalleryItem> mItems = new ArrayList<>();
    private ThumbnailDownloader<PhotoHolder> mThumbnailDownloader;

    private SwipeRefreshLayout mSwipeRefresh;

    public static PhotoGalleryFragment newInstance() {
        return new PhotoGalleryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        new FetchItemsTask().execute();


     mHandler();
    }
    public void mHandler (){
        Handler responseHandler = new Handler();
        mThumbnailDownloader = new ThumbnailDownloader<>(responseHandler);
        mThumbnailDownloader.setThumbnailDownloadListener(
                new ThumbnailDownloader.ThumbnailDownloadListener<PhotoHolder>() {
                    @Override
                    public void onThumbnailDownloaded(PhotoHolder photoHolder, Bitmap bitmap) {
                        Drawable drawable = new BitmapDrawable(getResources(), bitmap);


                        photoHolder.bindDrawable(drawable);
                    }
                }
        );
        mThumbnailDownloader.start();
        mThumbnailDownloader.getLooper();
        Log.i(TAG, "Background thread started");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_photo_gallery, container, false);
        mPhotoRecyclerView = (RecyclerView) v
                .findViewById(R.id.fragment_photo_gallery_recycler_view);
        mPhotoRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mSwipeRefresh = (SwipeRefreshLayout)v.findViewById(R.id.swipe);

       mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
           @Override
           public void onRefresh() {
               refreshContent();
           }
       });
        setupAdapter();


        return v;
    }
    private void refreshContent(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new FetchItemsTask().execute();

                mHandler();
                setupAdapter();
                mSwipeRefresh.setRefreshing(false);
            }
        },3000);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mThumbnailDownloader.clearQueue();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mThumbnailDownloader.quit();
    }

    private void setupAdapter() {
        if (isAdded()) {
            mPhotoRecyclerView.setAdapter(new PhotoAdapter(mItems));
        }

    }

    private class PhotoHolder extends RecyclerView.ViewHolder {
        private ImageView mItemImageView;
        private TextView mTextNama;
        private TextView mTextAlamat;

        public PhotoHolder(View itemView) {
            super(itemView);

            mItemImageView = (ImageView) itemView
                    .findViewById(R.id.fragment_photo_gallery_image_view);
            mTextNama = (TextView)itemView.findViewById(R.id.text_nama);
            mTextAlamat = (TextView)itemView.findViewById(R.id.text_tempat);
        }

        public void bindDrawable(Drawable drawable) {
            mItemImageView.setImageDrawable(drawable);
        }
        public void bindTextNama(GalleryItem nama) {
            mTextNama.setText(nama.getId());
        }
        public void bindTextAlamat(GalleryItem alamat) {
            mTextAlamat.setText(alamat.toString());
        }

    }

    private class PhotoAdapter extends RecyclerView.Adapter<PhotoHolder> {

        private List<GalleryItem> mGalleryItems;

        public PhotoAdapter(List<GalleryItem> galleryItems) {
            mGalleryItems = galleryItems;
        }



        @Override
        public PhotoHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.list_item_gallery, viewGroup, false);
            return new PhotoHolder(view);
        }

        @Override
        public void onBindViewHolder(final PhotoHolder photoHolder, final int position) {
            GalleryItem galleryItem = mGalleryItems.get(position);

            Drawable placeholder = getResources().getDrawable(R.mipmap.ic_launcher);
            photoHolder.bindDrawable(placeholder);
            photoHolder.bindTextNama(galleryItem);
            photoHolder.bindTextAlamat(galleryItem);


            mThumbnailDownloader.queueThumbnail(photoHolder, galleryItem.getUrl());
            photoHolder.mItemImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent in = new Intent(getActivity(), MapActivity.class);
                    in.putExtra("tes", mGalleryItems.get(position).getNomor());
                    in.putExtra("nama", mGalleryItems.get(position).getId());
                    in.putExtra("foto", mGalleryItems.get(position).getUrl());
                    in.putExtra("lokasi", mGalleryItems.get(position).getLokasi());
                    getActivity().startActivity(in);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mGalleryItems.size();
        }
    }

    private class FetchItemsTask extends AsyncTask<Void,Void,List<GalleryItem>> {

        @Override
        protected List<GalleryItem> doInBackground(Void... params) {

            return new FlickrFetchr().fetchItems();

        }

        @Override
        protected void onPostExecute(List<GalleryItem> items) {
            mItems = items;
            if (String.valueOf(items) == "[]"){
                Toast.makeText(getActivity(), "Tidak ada koneksi", Toast.LENGTH_SHORT).show();
            }
            setupAdapter();
        }

    }


}
