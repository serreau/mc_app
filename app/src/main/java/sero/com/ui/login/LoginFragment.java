package sero.com.ui.login;

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
import sero.com.util.SharedPreferencesHelper;

public class LoginFragment extends Fragment  implements  Validator.ValidationListener{
    private LoginViewModel viewModel;

    @BindView(R.id.loginLayout)
    TextInputLayout loginLayout;

    @BindView(R.id.passwordLayout)
    TextInputLayout passwordLayout;

    @BindView(R.id.loginInput)
    @NotEmpty
    TextInputEditText loginInput;

    @BindView(R.id.passwordInput)
    @Password(scheme = Password.Scheme.ALPHA)
    TextInputEditText passwordInput;;

    @BindView(R.id.loginButton)
    Button loginButton;

    @BindView(R.id.signupButton)
    Button signupButton;

    private Validator validator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        ButterKnife.bind(this, view);

        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        validator = new Validator(this);
        validator.setValidationListener(this);

        createListeners(view);
        return view;
    }

    public void createListeners(View view) {
        loginButton.setOnClickListener(v -> {
            validator.validate();
        });

        signupButton.setOnClickListener(v -> {
            Bundle b = new Bundle();
            b.putString("login", getText(loginInput));
            b.putString("password", getText(passwordInput));
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signupFragment, b);
        });

        loginInput.setOnFocusChangeListener((view1, b) -> loginLayout.setError(null));
        passwordInput.setOnFocusChangeListener((view1, b) -> passwordLayout.setError(null));
    }

    @Override
    public void onValidationSucceeded() {
        String login = getText(loginInput);
        String password = getText(passwordInput);

        if(viewModel.exist(login, password)){
            viewModel.login(login);
            Navigation.findNavController(this.getView()).navigate(R.id.action_loginFragment_to_kanbanViewPager);
        } else {
            loginLayout.setError(getString(R.string.connectionError));
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

    @Override
    public void onStart() {
        super.onStart();
        if(SharedPreferencesHelper.isConnected(getContext()))
            Navigation.findNavController(getView()).navigate(R.id.action_loginFragment_to_kanbanViewPager);
    }
}
