package br.com.ilhasoft.support.tool.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by johncordeiro on 03/09/15.
 */
public class BitmapCompressor {

    private static final int MAX_WIDTH = 800;
    private static final int MAX_HEIGHT = 800;

    public File compressFile(File pictureFile) throws IOException {
        Bitmap bitmap = BitmapFactory.decodeFile(pictureFile.getPath());

        File createdFile = BitmapHelper.createImageFile();
        bitmap = scaleBitmap(bitmap);
        bitmap = BitmapHelper.rotateBitmapIfNeeded(bitmap, pictureFile);

        FileOutputStream fileOutputStream = new FileOutputStream(createdFile);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, fileOutputStream);

        fileOutputStream.flush();
        fileOutputStream.close();
        return createdFile;
    }

    private Bitmap scaleBitmap(Bitmap bitmap) {
        int maxHeight = MAX_HEIGHT;
        int maxWidth = MAX_WIDTH;
        float scale = Math.min(((float) maxWidth / bitmap.getWidth()), ((float) maxHeight / bitmap.getHeight()));

        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);

        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

}
