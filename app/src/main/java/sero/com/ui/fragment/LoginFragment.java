package sero.com.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.navigation.Navigation;

import sero.com.data.entities.User;
import sero.com.ui.R;
import sero.com.ui.viewmodel.UserViewModel;

public class LoginFragment extends Fragment {
    UserViewModel userviewmodel;

        TextInputLayout login_layout;
        TextInputLayout password_layout;
        TextInputLayout mail_layout;
        TextInputLayout firstname_layout;
        TextInputLayout lastname_layout;
        TextInputEditText login;
        TextInputEditText password;
        TextInputEditText mail;
        TextInputEditText firstname;
        TextInputEditText lastname;
        Button connection;
        Button signup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        userviewmodel =  ViewModelProviders.of(this).get(UserViewModel.class);

        login_layout = view.findViewById(R.id.login_layout);
        password_layout = view.findViewById(R.id.password_layout);
        mail_layout = view.findViewById(R.id.mail_layout);
        firstname_layout = view.findViewById(R.id.firstname_layout);
        lastname_layout = view.findViewById(R.id.lastname_layout);
        login = view.findViewById(R.id.login_input);
        password = view.findViewById(R.id.password_input);
        mail = view.findViewById(R.id.mail_input);
        firstname = view.findViewById(R.id.firstname_input);
        lastname = view.findViewById(R.id.lastname_input);

        connection = view.findViewById(R.id.login_button);
        connection.setEnabled(false);
        signup = view.findViewById(R.id.signup_button);

        login.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                mail_layout.setVisibility(View.GONE);
                firstname_layout.setVisibility(View.GONE);
                lastname_layout.setVisibility(View.GONE);

                if (login.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    connection.setEnabled(false);
                } else {
                    connection.setEnabled(true);
                    connection.setVisibility(View.VISIBLE);
                    signup.setVisibility(View.GONE);
                }
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                mail_layout.setVisibility(View.GONE);
                firstname_layout.setVisibility(View.GONE);
                lastname_layout.setVisibility(View.GONE);

                if (login.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    connection.setEnabled(false);
                } else {
                    connection.setEnabled(true);
                    loginMode();
                }
            }
        });

        connection.setOnClickListener( v -> {
            long login = Long.parseLong(this.login.getText().toString());
            String password = this.password.getText().toString();

            if (userviewmodel.exist(login, password)) {
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_searchFragment);
            } else if (userviewmodel.exist(login)){
                password_layout.setError("Mot de passe erroné");
            } else {
                signUpMode();
            }
        });

        signup.setOnClickListener( v -> {
            User user = new User();
            user.setPhone(Long.parseLong(this.login.getText().toString()));
            user.setPassword(this.password.getText().toString());
            user.setMail(this.mail.getText().toString());
            user.setFirstname(this.firstname.getText().toString());
            user.setLastname(this.lastname.getText().toString());

            if (user.getMail().isEmpty()) {
                mail_layout.setError("Ce champs est obligatoire");
            } else if (!userviewmodel.exist(user.getPhone(), user.getPassword())) {
                userviewmodel.insert(user);
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_searchFragment);
            }
        });

        password.setOnFocusChangeListener((v,hf) -> {
            password_layout.setError(null);
        });
        mail.setOnFocusChangeListener((v,hf) -> {
            mail_layout.setError(null);
        });

        return view;
    }

    private void signUpMode(){
        mail_layout.setVisibility(View.VISIBLE);
        firstname_layout.setVisibility(View.VISIBLE);
        lastname_layout.setVisibility(View.VISIBLE);

        connection.setVisibility(View.GONE);
        signup.setVisibility(View.VISIBLE);
    }

    private void loginMode(){
        mail_layout.setVisibility(View.GONE);
        firstname_layout.setVisibility(View.GONE);
        lastname_layout.setVisibility(View.GONE);

        connection.setVisibility(View.VISIBLE);
        signup.setVisibility(View.GONE);
    }
}
