package sero.com.ui.dashboard;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import sero.com.data.entities.User;
import sero.com.ui.R;
import sero.com.util.SharedPreferencesHelper;

public class DashboardFragment extends Fragment implements  Validator.ValidationListener{
    DashboardViewModel viewmodel;

    @BindView(R.id.picture_image)
    CircleImageView profil_image;

    @BindView(R.id.login_layout)
    TextInputLayout login_layout;

    @BindView(R.id.password_layout)
    TextInputLayout password_layout;

    @BindView(R.id.mail_layout)
    TextInputLayout mail_layout;

    @NotEmpty
    @BindView(R.id.login_input)
    TextInputEditText login_input;

    @Email
    @BindView(R.id.mail_input)
    TextInputEditText mail_input;

    @BindView(R.id.firstname_input)
    TextInputEditText firstname_input;

    @BindView(R.id.lastname_input)
    TextInputEditText lastname_input;

    @Password(min = 6)
    @BindView(R.id.password_input)
    TextInputEditText password_input;

    @ConfirmPassword
    @BindView(R.id.passwordconfirmation_input)
    TextInputEditText passwordconfirmation_input;

    @BindView(R.id.save_button) Button save_button;
    @BindView(R.id.goto_logout) Button goto_logout;

    User user;

    private Validator validator;

    public static final int PICK_IMAGE = 1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard_fragment, container, false);
        ButterKnife.bind(this, view);
        viewmodel = ViewModelProviders.of(this).get(DashboardViewModel.class);

        validator = new Validator(this);
        validator.setValidationListener(this);

        createListeners(view);

        return  view;
    }

    private void createListeners(View view) {
        profil_image.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
        });

        viewmodel.getUser().observe(this, user -> {
            login_input.setText(user.getPhone());
            mail_input.setText(user.getMail());
            firstname_input.setText(user.getFirstname());
            lastname_input.setText(user.getLastname());
            password_input.setText(user.getPassword());
            passwordconfirmation_input.setText(user.getPassword());

            Picasso.get()
                    .load(user.getImage())
                    .error(R.drawable.default_icon)
                    .placeholder(R.drawable.default_icon)
                    .into(profil_image);
        });

        save_button.setOnClickListener( v -> {
            validator.validate();
        });

        goto_logout.setOnClickListener( v -> {
            SharedPreferencesHelper.logout(getContext());
            Navigation.findNavController(view).navigate(R.id.action_kanbanViewPagerFragment_to_loginFragment);
        });

        login_input.setOnFocusChangeListener((view1, b) -> login_layout.setError(null));
        password_input.setOnFocusChangeListener((view1, b) -> password_layout.setError(null));
        mail_input.setOnFocusChangeListener((view1, b) -> mail_layout.setError(null));

    }

    @Override
    public void onValidationSucceeded() {
        User u = viewmodel.getUser().getValue();
        u.setPassword(getText(password_input));
        u.setMail(getText(mail_input));
        u.setFirstname(getText(firstname_input));
        u.setLastname(getText(lastname_input));

        viewmodel.updateUser(u);
        Toast.makeText(this.getContext(), "Profil modifié", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            TextInputLayout input_layout = (TextInputLayout) error.getView().getParent().getParent();
            String message = error.getCollatedErrorMessage(this.getContext());
            input_layout.setError(message);
            input_layout.getEditText().setOnFocusChangeListener((view1, b) -> input_layout.setError(null));
        }
    }

    public String getText(TextInputEditText input){
        return input.getText().toString();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            viewmodel.setImage(data.getData());

            Picasso.get()
                    .load(data.getData())
                    .error(R.drawable.default_icon)
                    .placeholder(R.drawable.default_icon)
                    .into(profil_image);
        }
    }



}
