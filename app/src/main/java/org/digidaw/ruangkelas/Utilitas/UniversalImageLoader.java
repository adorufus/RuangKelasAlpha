package org.digidaw.ruangkelas.Utilitas;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import org.digidaw.ruangkelas.R;

public class UniversalImageLoader {
    private static final int defaultImage = R.drawable.ic_android_black_24dp;
    private Context mContext;

    public UniversalImageLoader(Context context){
        mContext = context;
    }

    public ImageLoaderConfiguration getConfig(){
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(defaultImage)
                .showImageForEmptyUri(defaultImage)
                .showImageOnFail(defaultImage)
                .cacheOnDisk(true).cacheInMemory(true)
                .cacheOnDisk(true).resetViewBeforeLoading(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mContext)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .diskCacheSize(100 * 1025 * 1025).build();

        return config;
    }

    /**
     * metode ini dapat digunakan untuk mengatur image yang statis. dan ga bisa digunakan jika
     * image tersebut sedang diubah di fragment/activity ATAU jika dia sedang diubaha di dalam list
     * atau di gridview
     * @param imgURI
     * @param image
     * @param mProgressBar
     * @param append
     */
    public static void setImage(String imgURI, ImageView image, final ProgressBar mProgressBar, String append){
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(append + imgURI, image, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {
                if(mProgressBar != null){
                    mProgressBar.setVisibility(view.VISIBLE);
                }
            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {
                if(mProgressBar != null){
                    mProgressBar.setVisibility(view.GONE);
                }
            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                if(mProgressBar != null){
                    mProgressBar.setVisibility(view.GONE);
                }
            }

            @Override
            public void onLoadingCancelled(String s, View view) {
                if(mProgressBar != null){
                    mProgressBar.setVisibility(view.GONE);
                }
            }
        });
    }
}
