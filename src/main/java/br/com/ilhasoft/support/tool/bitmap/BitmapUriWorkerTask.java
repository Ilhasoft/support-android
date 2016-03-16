package br.com.ilhasoft.support.tool.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;

import java.io.File;

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

        IOManager ioManager = new IOManager(context);
        Bitmap bitmapDecoded = bitmapDecoder.decodeSampledBitmapFromUri(context, uri, size, size);
        try {
            return BitmapHelper.rotateBitmapIfNeeded(bitmapDecoded, new File(ioManager.getFilePathForUri(uri)));
        } catch (Exception exception) {
            return bitmapDecoded;
        }
    }
}
