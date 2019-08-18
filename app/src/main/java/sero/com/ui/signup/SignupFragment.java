package sero.com.ui.signup;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.navigation.Navigation;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sero.com.data.entities.User;
import sero.com.ui.R;

public class SignupFragment extends Fragment  implements  Validator.ValidationListener{
    SignupViewModel viewmodel;

    @BindView(R.id.login_layout)
    TextInputLayout login_layout;

    @BindView(R.id.password_layout)
    TextInputLayout password_layout;

    @BindView(R.id.mail_layout)
    TextInputLayout mail_layout;

    @BindView(R.id.login_input)
    @NotEmpty
    TextInputEditText login_input;

    @BindView(R.id.password_input)
    @Password
    TextInputEditText password_input;

    @BindView(R.id.passwordconfirmation_input)
    @ConfirmPassword
    TextInputEditText passwordconfirmation_input;

    @BindView(R.id.mail_input)
    @Email
    TextInputEditText mail_input;

    @BindView(R.id.firstname_input)
    TextInputEditText firstname_input;

    @BindView(R.id.lastname_input)
    TextInputEditText lastname_input;

    @BindView(R.id.login_button)
    Button login_button;

    @BindView(R.id.signup_button)
    Button signup_button;

    private Validator validator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signup_fragment, container, false);
        ButterKnife.bind(this, view);

        viewmodel = ViewModelProviders.of(this).get(SignupViewModel.class);

        validator = new Validator(this);
        validator.setValidationListener(this);

        createListeners(view);
        return view;
    }

    public void createListeners(View view) {
        login_button.setOnClickListener(v -> {
            Bundle b = new Bundle();
            b.putString("login", login_input.getText().toString());
            b.putString("password", password_input.getText().toString());
            Navigation.findNavController(view).navigate(R.id.action_signupFragment_to_loginFragment, b);
        });

        signup_button.setOnClickListener(v -> {
            validator.validate();
        });

        login_input.setOnFocusChangeListener((view1, b) -> login_layout.setError(null));
        password_input.setOnFocusChangeListener((view1, b) -> password_layout.setError(null));
        mail_input.setOnFocusChangeListener((view1, b) -> mail_layout.setError(null));
    }

    @Override
    public void onValidationSucceeded() {
        User u = new User();
        u.setPhone(getText(login_input));
        u.setPassword(getText(password_input));
        u.setMail(getText(mail_input));
        u.setFirstname(getText(firstname_input));
        u.setLastname(getText(lastname_input));

        if(!viewmodel.exist(getText(login_input), getText(password_input))){
            viewmodel.insert(u);
            viewmodel.login(getText(login_input));
            Navigation.findNavController(this.getView()).navigate(R.id.action_signupFragment_to_searchFragment);
        } else if (viewmodel.exist(getText(login_input))){
            login_layout.setError("Ce compte existe déjà");
        } else {
            Toast.makeText(getContext(), "L'inscription n'a pu aboutir", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            TextInputLayout input_layout = (TextInputLayout) error.getView().getParent().getParent();
            String message = error.getCollatedErrorMessage(this.getContext());
            input_layout.setError(message);
        }
    }

    public String getText(TextInputEditText input){
        return input.getText().toString();
    }
}
