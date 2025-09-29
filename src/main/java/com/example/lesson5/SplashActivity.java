package com.example.lesson5;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



import com.example.lesson5.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;

    private static final int SPLASH_DELAY = 450;          // Константа: задержка показа экрана (миллисекунды) = 3 секунды

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.splash), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Задержка перед переходом к главному экрану
        new Handler(Looper.getMainLooper())               // Создаём Handler, привязанный к главному (UI) потоку
                .postDelayed(() -> {                      // Планируем выполнение блока кода через заданную задержку
                    Intent intent = new Intent(           // Создаём намерение перейти на другой экран
                            SplashActivity.this,          // Текущий контекст — эта Activity
                            FoodHomeActivity.class            // Класс целевой Activity (главный экран)
                    );
                    startActivity(intent);                // Запускаем целевую Activity
                    finish();                             // Закрываем сплэш, чтобы Back не возвращал к нему
                }, SPLASH_DELAY);                         // Время задержки в миллисекундах
    }
}

