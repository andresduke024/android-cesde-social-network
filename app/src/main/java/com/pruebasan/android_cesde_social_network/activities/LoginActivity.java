package com.pruebasan.android_cesde_social_network.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pruebasan.android_cesde_social_network.R;
import com.pruebasan.android_cesde_social_network.models.User;
import com.pruebasan.android_cesde_social_network.utils.ValidationException;

public class LoginActivity extends NavigationActivity {

    Button btnRegister;
    EditText txtEmail, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        createNavigationBar(R.string.login_title);
        setViewComponents();
    }

    private void setViewComponents() {
        btnRegister = findViewById(R.id.btnLogin);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);

        setListeners();
    }

    private void setListeners() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryLogin();
            }
        });
    }

    private void tryLogin() {
        try {
            validateFields();
            login();
        } catch (ValidationException exception) {
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
        } catch (Exception exception) {
            Toast.makeText(this, "Ocurrió un error inesperado, asegurese de ingresar todos los campos correctamente", Toast.LENGTH_LONG).show();
        }
    }

    private void validateFields() throws ValidationException {
        if(txtEmail.getText().toString().equals(""))
            throw new ValidationException("El correo no puede estar vacío");
        if(txtPassword.getText().toString().equals(""))
            throw new ValidationException("La contraseña no puede estar vacía");
    }

    private void login() {
        User user = new User();

        user.setEmail(txtEmail.getText().toString());
        user.setPassword(txtPassword.getText().toString());

        navigate(HomeActivity.class);
    }
}