package sero.com.ui.fragment.login;

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

import androidx.navigation.Navigation;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sero.com.ui.R;
import sero.com.util.LoginManager;

public class LoginFragment extends Fragment  implements  Validator.ValidationListener{
    private LoginViewModel loginviewmodel;

    @BindView(R.id.login_layout)
    TextInputLayout login_layout;

    @BindView(R.id.password_layout)
    TextInputLayout password_layout;

    @BindView(R.id.login_input)
    @NotEmpty
    TextInputEditText login_input;

    @BindView(R.id.password_input)
    @Password(scheme = Password.Scheme.ALPHA)
    TextInputEditText password_input;;

    @BindView(R.id.login_button)
    Button login_button;

    @BindView(R.id.signup_button)
    Button signup_button;

    private Validator validator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        ButterKnife.bind(this, view);

        loginviewmodel = ViewModelProviders.of(this).get(LoginViewModel.class);

        validator = new Validator(this);
        validator.setValidationListener(this);

        //login_input.setText(getArguments().getString("login", ""));
        //password_input.setText(getArguments().getString("password", ""));

        createListeners(view);
        return view;
    }

    public void createListeners(View view) {
        login_button.setOnClickListener(v -> {
            validator.validate();
        });

        signup_button.setOnClickListener(v -> {
            Bundle b = new Bundle();
            b.putString("login", getText(login_input));
            b.putString("password", getText(password_input));
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signupFragment, b);
        });

        login_input.setOnFocusChangeListener((view1, b) -> login_layout.setError(null));
        password_input.setOnFocusChangeListener((view1, b) -> password_layout.setError(null));
    }

    @Override
    public void onValidationSucceeded() {
        String login = getText(login_input);
        String password = getText(password_input);

        if(loginviewmodel.exist(login, password)){
            loginviewmodel.login(login);
            Navigation.findNavController(this.getView()).navigate(R.id.action_loginFragment_to_searchFragment);
        } else {
            login_layout.setError("Login inconnu ou mot de passe incorrect");
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

    @Override
    public void onStart() {
        super.onStart();
        if(LoginManager.isConnected(getContext()))
            Navigation.findNavController(getView()).navigate(R.id.action_loginFragment_to_searchFragment);
    }
}
