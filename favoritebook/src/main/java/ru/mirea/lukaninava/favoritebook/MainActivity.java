package ru.mirea.lukaninava.favoritebook;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> activityResultLauncher;
    static  final String KEY = "title";
    static  final String USER_MESSAGE = "User book";
    private TextView textViewUserBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewUserBook = findViewById(R.id.userBook);

        ActivityResultCallback<ActivityResult> callback = new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                if (o.getResultCode() == Activity.RESULT_OK){
                    Intent data = o.getData();
                    String userBook = data.getStringExtra(USER_MESSAGE);
                    textViewUserBook.setText(String.format("Ваша любимая книга: %s", userBook));
                }
            }
        };
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), callback);

    }
    public void  editBook(View view){
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra(KEY, "Зелёная миля");
        activityResultLauncher.launch(intent);
    }
}