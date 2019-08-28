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
    DashboardViewModel viewModel;

    @BindView(R.id.imageView)
    CircleImageView profilImage;

    @BindView(R.id.loginLayout)
    TextInputLayout loginLayout;

    @BindView(R.id.passwordLayout)
    TextInputLayout passwordLayout;

    @BindView(R.id.mailLayout)
    TextInputLayout mailLayout;

    @NotEmpty
    @BindView(R.id.loginInput)
    TextInputEditText loginInput;

    @Email
    @BindView(R.id.mailInput)
    TextInputEditText mailInput;

    @BindView(R.id.firstnameInput)
    TextInputEditText firstnameInput;

    @BindView(R.id.lastnameInput)
    TextInputEditText lastnameInput;

    @Password(min = 6)
    @BindView(R.id.passwordInput)
    TextInputEditText passwordInput;

    @ConfirmPassword
    @BindView(R.id.passwordConfirmationInput)
    TextInputEditText passwordConfirmationInput;

    @BindView(R.id.saveButton) Button saveButton;
    @BindView(R.id.logoutButton) Button logoutButton;

    private Validator validator;

    public static final int PICK_IMAGE = 1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard_fragment, container, false);
        ButterKnife.bind(this, view);
        viewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);

        validator = new Validator(this);
        validator.setValidationListener(this);

        createListeners(view);

        return  view;
    }

    private void createListeners(View view) {
        profilImage.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
        });

        viewModel.getUser().observe(this, user -> {
            loginInput.setText(user.getPhone());
            mailInput.setText(user.getMail());
            firstnameInput.setText(user.getFirstname());
            lastnameInput.setText(user.getLastname());
            passwordInput.setText(user.getPassword());
            passwordConfirmationInput.setText(user.getPassword());

            Picasso.get()
                    .load(user.getImage())
                    .error(R.drawable.default_icon)
                    .placeholder(R.drawable.default_icon)
                    .into(profilImage);
        });

        saveButton.setOnClickListener( v -> {
            validator.validate();
        });

        logoutButton.setOnClickListener( v -> {
            SharedPreferencesHelper.logout(getContext());
            Navigation.findNavController(view).navigate(R.id.action_kanbanViewPagerFragment_to_loginFragment);
        });

        loginInput.setOnFocusChangeListener((view1, b) -> loginLayout.setError(null));
        passwordInput.setOnFocusChangeListener((view1, b) -> passwordLayout.setError(null));
        mailInput.setOnFocusChangeListener((view1, b) -> mailLayout.setError(null));

    }

    @Override
    public void onValidationSucceeded() {
        User u = viewModel.getUser().getValue();
        u.setPassword(getText(passwordInput));
        u.setMail(getText(mailInput));
        u.setFirstname(getText(firstnameInput));
        u.setLastname(getText(lastnameInput));

        viewModel.updateUser(u);
        Toast.makeText(this.getContext(), R.string.changeProfilToast, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            TextInputLayout inputLayout = (TextInputLayout) error.getView().getParent().getParent();
            String message = error.getCollatedErrorMessage(this.getContext());
            inputLayout.setError(message);
            inputLayout.getEditText().setOnFocusChangeListener((view1, b) -> inputLayout.setError(null));
        }
    }

    public String getText(TextInputEditText input){
        return input.getText().toString();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            viewModel.setImage(data.getData());

            Picasso.get()
                    .load(data.getData())
                    .error(R.drawable.default_icon)
                    .placeholder(R.drawable.default_icon)
                    .into(profilImage);
        }
    }



}
