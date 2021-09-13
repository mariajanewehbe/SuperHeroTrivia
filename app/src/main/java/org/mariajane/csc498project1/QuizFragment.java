package org.mariajane.csc498project1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class QuizFragment extends Fragment implements View.OnClickListener{
    int false_counter = 0;
    int counter = 0;
    int right_answers = 0;

    int ticks = 5;
    boolean running = true;

    int[] chosen_pictures = {-99, -99, -99, -99, -99};
    int[] chosen_names = {-99, -99, -99, -99, -99};

    public QuizFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        Button trueButton = view.findViewById(R.id.true_button);
        Button falseButton = view.findViewById(R.id.false_button);

        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);
        return view;
    }

    public boolean searchArray(int[] a, int num)
    {
        for(int i=0; i<a.length; i++)
        {
            if(a[i] == num)
                return true;
        }
        return false;
    }

    public void questionGenerator()
    {
        View view = getView();

        if(view != null)
        {
            TextView textView = view.findViewById(R.id.hero_text);
            ImageView imageView = view.findViewById(R.id.hero_image);

            try {
                SQLiteOpenHelper sqLiteOpenHelper = new SuperheroSQLiteOpenHelper(getContext());
                SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();

                Random rand = new Random();

                if(false_counter <= 2) {
                    int photo_index = rand.nextInt(10);
                    int name_index = rand.nextInt(10);

                    while (searchArray(chosen_names, name_index)) {
                        name_index = rand.nextInt(10);
                    }

                    while (searchArray(chosen_pictures, photo_index)) {
                        photo_index = rand.nextInt(10);
                    }

                    chosen_names[counter] = name_index;
                    chosen_pictures[counter] = photo_index;

                    if (name_index != photo_index)
                        false_counter++;

                    Cursor cursorName = db.query("SUPERHERO",
                            new String[]{"NAME", "PICTURE"},
                            "_id = ?", new String[]{Integer.toString(name_index)},
                            null, null, null);

                    Cursor cursorPicture = db.query("SUPERHERO",
                            new String[]{"NAME", "PICTURE"},
                            "_id = ?", new String[]{Integer.toString(photo_index)},
                            null, null, null);

                    if (cursorName.moveToFirst()) {
                        String chosen_name = cursorName.getString(0);

                        textView.setText(chosen_name);
                    }

                    if(cursorPicture.moveToFirst()) {
                        int chosen_pic = cursorPicture.getInt(1);

                        imageView.setImageResource(chosen_pic);
                    }
                }
                else
                {
                    int index = rand.nextInt(10);
                    while(searchArray(chosen_pictures, index) || searchArray(chosen_names, index))
                    {
                        index = rand.nextInt(10);
                    }

                    chosen_names[counter] = chosen_pictures[counter] = index;

                    Cursor cursor = db.query("SUPERHERO",
                            new String[]{"NAME", "PICTURE"},
                            "_id = ?", new String[]{Integer.toString(index)},
                            null, null, null);

                    if(cursor.moveToFirst())
                    {
                        String chosen_name = cursor.getString(0);
                        int chosen_pic = cursor.getInt(1);

                        imageView.setImageResource(chosen_pic);
                        textView.setText(chosen_name);
                    }
                }
            } catch(Exception e){
                Toast.makeText(getContext(), "Database is not available.", Toast.LENGTH_SHORT).show();
            }
        }
        ticks = 5;
    }

    @Override
    public void onStart() {
        super.onStart();

        View view = getView();

        if(view != null)
        {
            TextView textView = view.findViewById(R.id.hero_text);
            ImageView imageView = view.findViewById(R.id.hero_image);

            try {
                SQLiteOpenHelper sqLiteOpenHelper = new SuperheroSQLiteOpenHelper(getContext());
                SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();

                Random rand = new Random();

                if(false_counter <= 2) {
                    int photo_index = rand.nextInt(10);
                    int name_index = rand.nextInt(10);

                    while (searchArray(chosen_names, name_index)) {
                        name_index = rand.nextInt(10);
                    }

                    while (searchArray(chosen_pictures, photo_index)) {
                        photo_index = rand.nextInt(10);
                    }

                    chosen_names[counter] = name_index;
                    chosen_pictures[counter] = photo_index;

                    if (name_index != photo_index)
                        false_counter++;

                    Cursor cursorName = db.query("SUPERHERO",
                            new String[]{"NAME", "PICTURE"},
                            "_id = ?", new String[]{Integer.toString(name_index)},
                            null, null, null);

                    Cursor cursorPicture = db.query("SUPERHERO",
                            new String[]{"NAME", "PICTURE"},
                            "_id = ?", new String[]{Integer.toString(photo_index)},
                            null, null, null);

                    if (cursorName.moveToFirst()) {
                        String chosen_name = cursorName.getString(0);

                        textView.setText(chosen_name);
                    }

                    if(cursorPicture.moveToFirst()) {
                        int chosen_pic = cursorPicture.getInt(1);

                        imageView.setImageResource(chosen_pic);
                    }
                }
                else
                {
                    int index = rand.nextInt(10);
                    while(searchArray(chosen_pictures, index))
                    {
                        index = rand.nextInt(10);
                    }

                    chosen_names[counter] = chosen_pictures[counter] = index;

                    Cursor cursor = db.query("SUPERHERO",
                            new String[]{"NAME", "PICTURE"},
                            "_id = ?", new String[]{Integer.toString(index)},
                            null, null, null);

                    if(cursor.moveToFirst())
                    {
                        String chosen_name = cursor.getString(0);
                        int chosen_pic = cursor.getInt(1);

                        imageView.setImageResource(chosen_pic);
                        textView.setText(chosen_name);
                    }
                }
            } catch(Exception e){
                Toast.makeText(getContext(), "Database is not available.", Toast.LENGTH_SHORT).show();
            }
        }
        ticks = 5;
        runTimer();
    }

    public void runTimer()
    {
        TextView timerTextView = getView().findViewById(R.id.timer_textview);
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(ticks > 0 && running) {
                    ticks--;
                }
                else if(!running)
                    return;

                else if(ticks == 0)
                {
                    Toast.makeText(getContext(), "You ran out of time.", Toast.LENGTH_SHORT).show();
                    counter++;
                    if(counter < 5)
                        questionGenerator();
                    else
                        displayScore();
                }


                int minutes = ticks / 60 % 60;
                int seconds = ticks;
                String stringToDisplay = String.format("%02d:%02d", minutes, seconds);
                timerTextView.setText(stringToDisplay);
                handler.postDelayed(this, 1000);
            }
        });
    }

    public void displayScore()
    {
        running = false;
        Intent intent = new Intent(getContext(), ScoresActivity.class);
        intent.putExtra("Score", right_answers);
        startActivity(intent);
    }

    @Override
    public void onStop() {
        super.onStop();
        running = false;
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.true_button)
        {
            if(chosen_names[counter] == chosen_pictures[counter])
            {
                Toast.makeText(getContext(), "Your answer is correct.", Toast.LENGTH_SHORT).show();
                right_answers++;
            }
            else
                Toast.makeText(getContext(), "Your answer is incorrect.", Toast.LENGTH_SHORT).show();
            counter++;
            if(counter < 5)
                questionGenerator();
            else {
                displayScore();
            }
        }
        if(v.getId() == R.id.false_button)
        {
            if(chosen_names[counter] != chosen_pictures[counter])
            {
                Toast.makeText(getContext(), "Your answer is correct.", Toast.LENGTH_SHORT).show();
                right_answers++;
            }
            else
                Toast.makeText(getContext(), "Your answer is incorrect", Toast.LENGTH_SHORT).show();
            counter++;
            if(counter < 5)
                questionGenerator();
            else
                displayScore();
        }

    }
}