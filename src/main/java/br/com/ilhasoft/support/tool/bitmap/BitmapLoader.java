package br.com.ilhasoft.support.tool.bitmap;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

/**
 * Created by johndalton on 12/07/14.
 */
public class BitmapLoader {

    private final Context context;

    public BitmapLoader(Context context) {
        this.context = context;
    }

    public void loadBitmapByFile(String pathName, ImageView imageView, int size) {
        if (cancelPotentialWork(pathName, imageView)) {
            final BitmapFileWorkerTask task = new BitmapFileWorkerTask(imageView, size);
            final AsyncDrawable<BitmapFileWorkerTask> asyncDrawable = new AsyncDrawable<>(context.getResources(), null, task);
            imageView.setImageDrawable(asyncDrawable);
            task.execute(pathName);
        }
    }

    public void loadBitmapByUri(Uri uri, ImageView imageView, int size) {
        if (cancelPotentialWork(uri.getPath(), imageView)) {
            final BitmapUriWorkerTask task = new BitmapUriWorkerTask(context, imageView, size);
            final AsyncDrawable<BitmapUriWorkerTask> asyncDrawable = new AsyncDrawable<>(context.getResources(), null, task);
            imageView.setImageDrawable(asyncDrawable);
            task.execute(uri);
        }
    }

    public boolean cancelPotentialWork(String pathName, ImageView imageView) {
        final BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);

        if (bitmapWorkerTask != null) {
            final String bitmapData = bitmapWorkerTask.pathName;
            if (bitmapData != pathName) {
                bitmapWorkerTask.cancel(true);
            } else {
                return false;
            }
        }
        return true;
    }

    private BitmapWorkerTask getBitmapWorkerTask(ImageView imageView) {
        if (imageView != null) {
            final Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AsyncDrawable) {
                try {
                    final AsyncDrawable<BitmapWorkerTask> asyncDrawable = (AsyncDrawable<BitmapWorkerTask>) drawable;
                    return asyncDrawable.getWorkerTask();
                } catch(Exception exception) { exception.printStackTrace(); }
            }
        }
        return null;
    }

}
