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
    SignupViewModel viewModel;

    @BindView(R.id.loginLayout)
    TextInputLayout loginLayout;

    @BindView(R.id.passwordLayout)
    TextInputLayout passwordLayout;

    @BindView(R.id.mailLayout)
    TextInputLayout mailLayout;

    @BindView(R.id.loginInput)
    @NotEmpty
    TextInputEditText loginInput;

    @BindView(R.id.passwordInput)
    @Password
    TextInputEditText passwordInput;

    @BindView(R.id.passwordConfirmationInput)
    @ConfirmPassword
    TextInputEditText passwordConfirmationInput;

    @BindView(R.id.mailInput)
    @Email
    TextInputEditText mailInput;

    @BindView(R.id.firstnameInput)
    TextInputEditText firstnameInput;

    @BindView(R.id.lastnameInput)
    TextInputEditText lastnameInput;

    @BindView(R.id.loginButton)
    Button loginButton;

    @BindView(R.id.signupButton)
    Button signupButton;

    private Validator validator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signup_fragment, container, false);
        ButterKnife.bind(this, view);

        viewModel = ViewModelProviders.of(this).get(SignupViewModel.class);

        validator = new Validator(this);
        validator.setValidationListener(this);

        createListeners(view);
        return view;
    }

    public void createListeners(View view) {
        loginButton.setOnClickListener(v -> {
            Bundle b = new Bundle();
            b.putString("login", loginInput.getText().toString());
            b.putString("password", passwordInput.getText().toString());
            Navigation.findNavController(view).navigate(R.id.action_signupFragment_to_loginFragment, b);
        });

        signupButton.setOnClickListener(v -> {
            validator.validate();
        });

        loginInput.setOnFocusChangeListener((view1, b) -> loginLayout.setError(null));
        passwordInput.setOnFocusChangeListener((view1, b) -> passwordLayout.setError(null));
        mailInput.setOnFocusChangeListener((view1, b) -> mailLayout.setError(null));
    }

    @Override
    public void onValidationSucceeded() {
        User u = new User();
        u.setPhone(getText(loginInput));
        u.setPassword(getText(passwordInput));
        u.setMail(getText(mailInput));
        u.setFirstname(getText(firstnameInput));
        u.setLastname(getText(lastnameInput));

        if(!viewModel.exist(getText(loginInput), getText(passwordInput))){
            viewModel.insert(u);
            viewModel.login(getText(loginInput));
            Toast.makeText(getContext(), getString(R.string.signupSuccess), Toast.LENGTH_SHORT).show();

            Navigation.findNavController(this.getView()).navigate(R.id.action_signupFragment_to_loginFragment);

        } else if (viewModel.exist(getText(loginInput))){
            loginLayout.setError(getString(R.string.accountAlreadyExistError));
        } else {
            Toast.makeText(getContext(), getString(R.string.signupError), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            TextInputLayout inputLayout = (TextInputLayout) error.getView().getParent().getParent();
            String message = error.getCollatedErrorMessage(this.getContext());
            inputLayout.setError(message);
        }
    }

    public String getText(TextInputEditText input){
        return input.getText().toString();
    }
}
