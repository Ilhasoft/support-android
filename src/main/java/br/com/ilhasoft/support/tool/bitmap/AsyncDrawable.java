package br.com.ilhasoft.support.tool.bitmap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

/**
 * Created by johndalton on 12/07/14.
 */
public class AsyncDrawable<T extends AsyncTask> extends BitmapDrawable {

    private final WeakReference<T> workerTask;

    public AsyncDrawable(Resources res, Bitmap bitmap, T imageWorkerTask) {
        super(res, bitmap);
        workerTask = new WeakReference<T>(imageWorkerTask);
    }

    public T getWorkerTask() {
        return workerTask.get();
    }

}
