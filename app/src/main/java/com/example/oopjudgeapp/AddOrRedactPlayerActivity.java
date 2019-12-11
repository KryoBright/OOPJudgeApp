package com.example.oopjudgeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddOrRedactPlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_redact_player);
        final Player thisPlayer;
        Integer id = getIntent().getIntExtra("id", -1);
        if (id != -1)
            thisPlayer = EverythingHolder.getAllPlayers().get(id);
        else thisPlayer = new Player("");
        final EditText name = findViewById(R.id.editTextPlayerName);
        name.setText(thisPlayer.getName());
        Button savePlayerBtn = findViewById(R.id.buttonSavePlayer);
        savePlayerBtn.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 thisPlayer.setName(name.getText().toString());
                                                 EverythingHolder.addPlayer(thisPlayer);
                                                 finish();
                                             }
                                         }
        );
    }
}
