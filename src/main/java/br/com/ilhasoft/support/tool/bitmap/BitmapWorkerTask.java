package br.com.ilhasoft.support.tool.bitmap;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * Created by johndalton on 12/07/14.
 */
public abstract class BitmapWorkerTask<Input> extends AsyncTask<Input, Void, Bitmap> {

    protected final BitmapDecoder bitmapDecoder;

    private final WeakReference<ImageView> imageViewReference;
    private final MediaAnimator mediaAnimator;

    protected final int size;
    public String pathName = null;

    public BitmapWorkerTask(ImageView imageView, int size) {
        imageViewReference = new WeakReference<>(imageView);
        this.size = size;
        mediaAnimator = new MediaAnimator(imageView.getContext());
        bitmapDecoder = new BitmapDecoder();
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (bitmap != null) {
            final ImageView imageView = imageViewReference.get();
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
                mediaAnimator.showMedia(imageView);
            }
        }
    }

}
