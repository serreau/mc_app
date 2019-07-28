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

import butterknife.BindView;
import butterknife.ButterKnife;
import sero.com.data.entities.User;
import sero.com.ui.R;
import sero.com.ui.viewmodel.UserViewModel;
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
        userviewmodel.get(login_input.getText().toString()).observe(getActivity(), u -> {
            LoginManager.login(u, c);
        });

        login_button.setEnabled(false);

        login_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                loginMode();
            }
        });

        password_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                loginMode();
            }
        });

        mail_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                signUpMode();
            }
        });

        login_button.setOnClickListener( v -> {
            long login = Long.parseLong(this.login_input.getText().toString());
            String password = this.password_input.getText().toString();

            if (userviewmodel.exist(login, password)) {
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_searchFragment);
                userviewmodel.setPhone(""+login);
            } else if (userviewmodel.exist(login)){
                password_layout.setError(getString(R.string.password_error));
            } else {
                signUpMode();
            }
        });

        signup_button.setOnClickListener( v -> {
            User user = new User();
            user.setPhone(Long.parseLong(this.login_input.getText().toString()));
            user.setPassword(this.password_input.getText().toString());
            user.setMail(this.mail_input.getText().toString());
            user.setFirstname(this.firstname_input.getText().toString());
            user.setLastname(this.lastname_input.getText().toString());

            if (user.getMail().isEmpty())
                mail_layout.setError(getString(R.string.fieldrequired_error));
            else if (!Patterns.EMAIL_ADDRESS.matcher(user.getMail()).matches())
                mail_layout.setError(getString(R.string.pattern_error));
            else {
                userviewmodel.insert(user);
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_searchFragment);
            }
        });

        password_input.setOnFocusChangeListener((v,hf) -> {
            password_layout.setError(null);
        });
        mail_input.setOnFocusChangeListener((v,hf) -> {
            mail_layout.setError(null);
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(LoginManager.exist(c))
            Navigation.findNavController(this.getView()).navigate(R.id.action_loginFragment_to_searchFragment);
    }

    private void signUpMode(){
        mail_layout.setVisibility(View.VISIBLE);
        firstname_layout.setVisibility(View.VISIBLE);
        lastname_layout.setVisibility(View.VISIBLE);

        login_button.setVisibility(View.GONE);
        signup_button.setVisibility(View.VISIBLE);

        if (mail_input.getText().toString().isEmpty())
            signup_button.setEnabled(false);
        else
            signup_button.setEnabled(true);
    }

    private void loginMode(){
        mail_layout.setVisibility(View.GONE);
        firstname_layout.setVisibility(View.GONE);
        lastname_layout.setVisibility(View.GONE);

        login_button.setVisibility(View.VISIBLE);
        signup_button.setVisibility(View.GONE);

        if (login_input.getText().toString().isEmpty() || password_input.getText().toString().isEmpty())
            login_button.setEnabled(false);
        else
            login_button.setEnabled(true);
    }
}
