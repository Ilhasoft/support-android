package br.com.ilhasoft.support.tool.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * Created by johndalton on 12/07/14.
 */
public class BitmapUriWorkerTask extends BitmapWorkerTask<Uri> {

    private Context context;

    public BitmapUriWorkerTask(Context context, ImageView imageView, int size) {
        super(imageView, size);
        this.context = context;
    }

    @Override
    protected Bitmap doInBackground(Uri... params) {
        Uri uri = params[0];
        pathName = uri.getPath();
        return bitmapDecoder.decodeSampledBitmapFromUri(context, uri, size, size);
    }
}
