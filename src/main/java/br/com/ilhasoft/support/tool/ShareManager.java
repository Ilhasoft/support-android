package br.com.ilhasoft.support.tool;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;

/**
 * Created by johncordeiro on 17/09/15.
 */
public class ShareManager {

    private Context context;

    public ShareManager(Context context) {
        this.context = context;
    }

    public void shareBinary(File file, String type, String title) {
        Intent sharingIntent = new Intent();
        sharingIntent.setAction(Intent.ACTION_SEND);
        sharingIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
        sharingIntent.setType(type);
        context.startActivity(Intent.createChooser(sharingIntent, title));
    }

    public void shareContent(String title, String subject, String text) {
        Intent sharingIntent = new Intent();
        sharingIntent.setAction(Intent.ACTION_SEND);
        sharingIntent.putExtra(Intent.EXTRA_TEXT, text);
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        sharingIntent.setType("text/plain");
        context.startActivity(Intent.createChooser(sharingIntent, title));
    }

}
