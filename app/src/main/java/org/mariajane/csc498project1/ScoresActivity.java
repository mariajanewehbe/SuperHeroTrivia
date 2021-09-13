package org.mariajane.csc498project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ScoresActivity extends AppCompatActivity {
    public final static int SCORE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        Intent intent = getIntent();
        int score = intent.getIntExtra("Score", SCORE);

        StringBuilder scoreText = new StringBuilder("You got a ");
        scoreText.append(score).append(" out of 5!");

        TextView textView = findViewById(R.id.score_text_view);
        textView.setText(scoreText);

        TextView averageTextView = findViewById(R.id.average_text_view);

        try {
            SQLiteOpenHelper sqLiteOpenHelper = new ScoreSQLiteOpenHelper(this);
            SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();

           Cursor cursor = db.query("SCORES",
                    new String[] {"AVG(SCORE) AS AVERAGE"},
                    null, null, null, null, null);
           if(cursor.moveToFirst()) {
               float average = cursor.getFloat(0);
               String averageString;
               if (score > average)
                   averageString = "You did better than the average score of " + average + ".";
               else
                   averageString = "The average score of " + average + " is higher.";
               averageTextView.setText(averageString);

               ContentValues contentValues = new ContentValues();
               contentValues.put("SCORE", score);
               db.insert("SCORES", null, contentValues);
           }
        } catch (Exception e)
        {
            Toast.makeText(this, "Database is not available from activity", Toast.LENGTH_SHORT).show();
        }
    }
}