package br.com.ilhasoft.support.tool;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.util.Patterns;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Created by lucas on 20/05/2015.
 */
public class EditTextValidator {

    public boolean validateSize(EditText editText, int minSize, String errorMessage) {
        Editable text = editText.getText();
        if (text != null && !text.toString().trim().isEmpty() && text.toString().trim().length() > minSize) {
            setError(editText, null);
            return true;
        } else {
            setError(editText, errorMessage);
            return false;
        }
    }

    public boolean validateEmpty(EditText editText, String errorMessage) {
        return validateSize(editText, 0, errorMessage);
    }

    public boolean validateSizeMulti(int minSize, String errorMessage, EditText... editTexts) {
        boolean valid = true;
        for (EditText editText : editTexts) {
            if(!validateSize(editText, minSize, errorMessage)) {
                valid = false;
            }
        }
        return valid;
    }

    public static boolean dateEvaluation(EditText editText, String errorMessage, String pattern) {
        DateFormat formatter = new SimpleDateFormat(pattern, Locale.US);
        String value = editText.getText().toString();
        try {
            formatter.parse(value);
            setError(editText, null);
            return true;
        } catch(Exception exception) {
            setError(editText, errorMessage);
            return false;
        }
    }

    private static void setError(EditText editText, String errorMessage) {
        if(editText.getParent() instanceof TextInputLayout) {
            TextInputLayout textInputLayout = (TextInputLayout) editText.getParent();
            textInputLayout.setError(errorMessage);
        } else {
            editText.setError(errorMessage);
        }
    }

    public boolean validateEmail(EditText editText, String errorMessage) {
        Pattern emailPattern = Patterns.EMAIL_ADDRESS;
        if(emailPattern.matcher(editText.getText()).matches()) {
            setError(editText, null);
            return true;
        } else {
            setError(editText, errorMessage);
            return false;
        }
    }
}
