package br.com.ilhasoft.support.tool.bitmap;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by johncordeiro on 8/3/15.
 */
public class ImageStorage {

    private static final String TAG = "ImageStorage";

    public File saveBitmapToJpg(Bitmap bitmap, String filename) {
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        File file = writeFileIfNotExists(filename + ".jpg", extStorageDirectory);

        try {
            OutputStream outStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, outStream);
            outStream.flush();
            outStream.close();
        } catch (Exception exception) {
            Log.e(TAG, "saveBitmapToJpg ", exception);
        }
        return file;

    }

    @NonNull
    private File writeFileIfNotExists(String filename, String extStorageDirectory) {
        File file = new File(extStorageDirectory, filename);
        if (file.exists()) {
            file.delete();
            file = new File(extStorageDirectory, filename);
        }
        return file;
    }

}
