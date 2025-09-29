package com.example.lesson5;

import android.os.Bundle;
import android.text.Editable;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lesson5.databinding.ActivityLoginBinding;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Инициализация binding
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // "Forgot Password?"
        binding.tvForgot.setOnClickListener(v ->
                Toast.makeText(this, "Forgot password tapped", Toast.LENGTH_SHORT).show()
        );

        // Кнопка "Log in"
        binding.btnLogin.setOnClickListener(v -> {
            String email = textOf(binding.etEmail);
            String pass  = textOf(binding.etPassword);

            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Введите email и пароль", Toast.LENGTH_SHORT).show();
                return;
            }

            // TODO: реальная авторизация
            Toast.makeText(this, "Log in: " + email, Toast.LENGTH_SHORT).show();
        });
    }

    private static String textOf(TextInputEditText et) {
        Editable e = et.getText();
        return e == null ? "" : e.toString().trim();
    }
}
