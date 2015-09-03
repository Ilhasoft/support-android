package br.com.ilhasoft.support.tool.bitmap;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by johncordeiro on 03/09/15.
 */
public class BitmapFileWorkerTask extends BitmapWorkerTask<String> {

    public BitmapFileWorkerTask(ImageView imageView, int size) {
        super(imageView, size);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        pathName = params[0];
        return bitmapDecoder.decodeSampledBitmapFromFile(pathName, size, size);
    }
}
