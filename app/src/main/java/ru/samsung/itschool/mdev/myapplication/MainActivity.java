package ru.samsung.itschool.mdev.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.Toast;

import ru.samsung.itschool.mdev.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // https://github.com/m7amdelbana/Android-MVVM-Java-Example
        // https://medium.com/nuances-of-programming/mvvm-%D0%BD%D0%B0-android-%D1%81-%D0%BA%D0%BE%D0%BC%D0%BF%D0%BE%D0%BD%D0%B5%D0%BD%D1%82%D0%B0%D0%BC%D0%B8-%D0%B0%D1%80%D1%85%D0%B8%D1%82%D0%B5%D0%BA%D1%82%D1%83%D1%80%D1%8B-%D0%B1%D0%B8%D0%B1%D0%BB%D0%B8%D0%BE%D1%82%D0%B5%D0%BA%D0%B0-koin-e2e77b77950e
        // https://itsobes.ru/AndroidSobes/chto-takoe-mvvm/
        // https://geekstand.top/development/ponimanie-mvvm-patterna-dlja-android-v-2021-godu/

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        LoginViewModel loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding.setViewModel(loginViewModel);
        binding.setLifecycleOwner(this);

        loginViewModel.getUser().observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                User user = (User) o;
                if (user != null)
                    if (user.getEmail().length() > 0 || user.getPassword().length() > 0)
                        Toast.makeText(getApplicationContext(), "Email : " + user.getEmail() + " Password " + user.getPassword(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}