package br.com.ilhasoft.support.tool.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by john-mac on 2/15/16.
 */
public class VideoUriBitmapTask extends BitmapWorkerTask<Uri> {

    private static final String TAG = "VideoUriBitmapTask";

    private Context context;

    public VideoUriBitmapTask(Context context, ImageView imageView, int size) {
        super(imageView, size);
        this.context = context;
    }

    @Override
    protected Bitmap doInBackground(Uri... params) {
        Uri uri = params[0];
        pathName = uri.getPath();
        try {
            return BitmapHelper.getThumbnailFromVideoUri(context, uri);
        } catch(Exception exception) {
            Log.e(TAG, "doInBackground: ", exception);
            return null;
        }
    }

}
