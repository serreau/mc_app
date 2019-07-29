package sero.com.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.navigation.Navigation;

import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.ButterKnife;
import sero.com.data.entities.User;
import sero.com.ui.R;
import sero.com.ui.viewmodel.UserViewModel;
import sero.com.util.InputValidator;
import sero.com.util.LoginManager;

public class LoginFragment extends Fragment {
    UserViewModel userviewmodel;

    @BindView(R.id.login_layout) TextInputLayout login_layout;
    @BindView(R.id.password_layout) TextInputLayout password_layout;
    @BindView(R.id.mail_layout) TextInputLayout mail_layout;
    @BindView(R.id.firstname_layout) TextInputLayout firstname_layout;
    @BindView(R.id.lastname_layout) TextInputLayout lastname_layout;

    @BindView(R.id.login_input) TextInputEditText login_input;
    @BindView(R.id.password_input) TextInputEditText password_input;
    @BindView(R.id.mail_input) TextInputEditText mail_input;
    @BindView(R.id.firstname_input) TextInputEditText firstname_input;
    @BindView(R.id.lastname_input) TextInputEditText lastname_input;

    @BindView(R.id.login_button) Button login_button;
    @BindView(R.id.signup_button) Button signup_button;

    Context c;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        ButterKnife.bind(this, view);

        c = getActivity();
        userviewmodel = ViewModelProviders.of(this).get(UserViewModel.class);
        userviewmodel.getUser().observe(getActivity(), u -> {
            LoginManager.login(u, c);
        });

        createListeners(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (LoginManager.exist(c))
            Navigation.findNavController(this.getView()).navigate(R.id.action_loginFragment_to_searchFragment);
    }

    public void createListeners(View view) {
        mTextWacher watcherloginmode = new mTextWacher(() -> loginMode());
        mTextWacher watchersignupmode = new mTextWacher(() -> signUpMode());

        login_input.addTextChangedListener(watcherloginmode);
        password_input.addTextChangedListener(watcherloginmode);
        mail_input.addTextChangedListener(watchersignupmode);

        login_button.setOnClickListener(v -> {
            long login = Long.parseLong(this.login_input.getText().toString());
            String password = this.password_input.getText().toString();

            if (InputValidator.isValidLoginPassword(login_layout, login_input, password_layout, password_input, getContext())) {
                if(userviewmodel.exist(login,password)) {
                    Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_searchFragment);
                    userviewmodel.login("" + login);
                }
                else if(userviewmodel.exist(login)) {
                    password_layout.setError(getString(R.string.password_error));
                }
                else {
                    signUpMode();
                }
            }
        });

        signup_button.setOnClickListener(v -> {
            User user = initUser();

            if(InputValidator.isValidMail(mail_layout, mail_input, getContext())) {
                userviewmodel.insert(user);
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_searchFragment);
                userviewmodel.login("" + user.getPhone());
            }
        });

        login_input.setOnFocusChangeListener((v, hf) -> {
            login_layout.setError(null);
        });
        password_input.setOnFocusChangeListener((v, hf) -> {
            password_layout.setError(null);
        });
        mail_input.setOnFocusChangeListener((v, hf) -> {
            mail_layout.setError(null);
        });
    }

    private boolean signUpMode() {
        mail_layout.setVisibility(View.VISIBLE);
        firstname_layout.setVisibility(View.VISIBLE);
        lastname_layout.setVisibility(View.VISIBLE);
        login_button.setVisibility(View.GONE);
        signup_button.setVisibility(View.VISIBLE);

        if (mail_input.getText().toString().isEmpty())
            signup_button.setEnabled(false);
        else
            signup_button.setEnabled(true);
        return true;
    }

    private boolean loginMode() {
        mail_layout.setVisibility(View.GONE);
        firstname_layout.setVisibility(View.GONE);
        lastname_layout.setVisibility(View.GONE);
        login_button.setVisibility(View.VISIBLE);
        signup_button.setVisibility(View.GONE);

        if (login_input.getText().toString().isEmpty() || password_input.getText().toString().isEmpty())
            login_button.setEnabled(false);
        else
            login_button.setEnabled(true);
        return true;
    }

    private User initUser(){
        User user = new User();
        user.setPhone(Long.parseLong(this.login_input.getText().toString()));
        user.setPassword(this.password_input.getText().toString());
        user.setMail(this.mail_input.getText().toString());
        user.setFirstname(this.firstname_input.getText().toString());
        user.setLastname(this.lastname_input.getText().toString());
        return  user;
    }

    public class mTextWacher implements TextWatcher {
        Callable callable;

        public mTextWacher(Callable c) { this.callable = c; }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

        @Override
        public void afterTextChanged(Editable editable) {
            try { callable.call(); }
            catch (Exception e) { e.printStackTrace(); }
        }
    }
}
