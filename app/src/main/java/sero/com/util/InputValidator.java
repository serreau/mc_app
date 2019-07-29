package sero.com.util;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Patterns;

import androidx.navigation.Navigation;

import sero.com.ui.R;
import sero.com.ui.fragment.LoginFragment;
import sero.com.ui.viewmodel.UserViewModel;

public abstract class InputValidator {

    public final static boolean isValidMail(TextInputLayout mail_layout, TextInputEditText mail_input, Context context){

        if (!Patterns.EMAIL_ADDRESS.matcher(mail_input.getText().toString()).matches()) {
            mail_layout.setError(context.getString(R.string.pattern_error));
            return false;
        }
        return  true;
    }

    public final static boolean isValidLoginPassword(TextInputLayout login_layout, TextInputEditText login_input,
                                             TextInputLayout password_layout, TextInputEditText password_input,
                                                     Context context){
        boolean valid = true;

        if (!Patterns.PHONE.matcher(login_input.getText().toString()).matches()) {
            login_layout.setError(context.getString(R.string.pattern_error));
            valid = false;
        }
        //TODO
        if (password_input.length() <= 5) {
            password_layout.setError(context.getString(R.string.pattern_error));
            valid = false;
        }

        return valid;
    }

}
