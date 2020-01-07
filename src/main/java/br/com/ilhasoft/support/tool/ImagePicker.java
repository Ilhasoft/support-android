package br.com.ilhasoft.support.tool;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.IOException;

/**
 * Created by johncordeiro on 20/08/15.
 */
public class ImagePicker {

    private static final String TAG = "ImagePicker";

    public static final int REQUEST_PICK_FROM_GALLERY = 1024;
    public static final int REQUEST_IMAGE_CAPTURE = 1;

    public void pickImageFromGallery(Fragment fragment) {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        } else {
            intent.setAction(Intent.ACTION_GET_CONTENT);
        }

        intent.setType("image/*");
        fragment.startActivityForResult(intent, REQUEST_PICK_FROM_GALLERY);
    }

    public File pickImageFromCamera(Fragment fragment) throws IOException {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(fragment.getActivity().getPackageManager()) != null) {
            IOManager ioManager = new IOManager(fragment.getActivity());
            File pictureFile = ioManager.createFilePath();

            if(pictureFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(pictureFile));
                fragment.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                return pictureFile;
            }
        }
        return null;
    }

}
