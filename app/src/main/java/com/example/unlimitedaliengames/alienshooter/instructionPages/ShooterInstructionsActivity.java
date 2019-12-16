package com.example.unlimitedaliengames.alienshooter.instructionPages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.unlimitedaliengames.LoginActivity;
import com.example.unlimitedaliengames.MainActivity;
import com.example.unlimitedaliengames.R;
import com.example.unlimitedaliengames.userdata.User;

public class ShooterInstructionsActivity extends AppCompatActivity {
    private View returnToMenu;
    private View customize;
    public static final String PASS_USER = "passUser";
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shooter_instructions);
        returnToMenu = findViewById(R.id.returnToMenu);
        setReturnListener();
        customize = findViewById(R.id.customize);
        setCustomizeListener();
        user = (User) intent.getSerializableExtra(PASS_USER);
    }

    /**
     * go to the customization activity when clicked
     */
    private void setCustomizeListener() {
        customize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customizeActivity();
                finish();
            }
        });
    }

    /**
     * go to the menu activity when clicked
     */
    private void setReturnListener() {
        returnToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu();
                finish();
            }
        });
    }

    /**
     * move on to next activity
     */
    private void customizeActivity() {
        Intent intent = new Intent(this, CustomizeActivity.class);
        intent.putExtra(PASS_USER, user);
        startActivity(intent);
    }

    /**
     * move on to next activity
     */
    private void menu() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(PASS_USER, user);
        startActivity(intent);
    }
}
