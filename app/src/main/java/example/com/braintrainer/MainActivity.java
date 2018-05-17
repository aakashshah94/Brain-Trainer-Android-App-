package example.com.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.sql.Time;
import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout layout;
    Button playAgain;
    Button startButton;
    TextView scoreText;
    TextView timer;
    TextView sumText;
    Button answer1;
    Button answer2;
    Button answer3;
    Button answer4;
    GridLayout grid;
    TextView answerText;
    int []answers=new int[4];
    int correctAnswerlocation;
    int score = 0;
    int round = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.btnStart:
                view.setVisibility(View.INVISIBLE);
                layout.setVisibility(View.VISIBLE);
                playAgainMethod(playAgain);

                break;

            default:
                if(Integer.valueOf(view.getTag().toString())==correctAnswerlocation+1)
                {
                    answerText.setText("Correct Answer");
                    score++;
                }
                else
                {
                    answerText.setText("Incorrect Answer");
                }
                round++;
                scoreText.setText(Integer.toString(score)+"/"+Integer.toString(round));
                generateQuestion();
                break;
        }
    }

    void init()
    {
        grid = findViewById(R.id.answer_grid);
        startButton =findViewById(R.id.btnStart);
        answer1 = findViewById(R.id.btnOption1);
        answer2 = findViewById(R.id.btnOption2);
        answer3 = findViewById(R.id.btnOption3);
        answer4 = findViewById(R.id.btnOption4);
        answerText = findViewById(R.id.tvAnswer);
        scoreText = findViewById(R.id.tvScore);
        timer = findViewById(R.id.tvTimer);
        sumText = findViewById(R.id.tvSum);
        startButton.setOnClickListener(this);
        answer1.setOnClickListener(this);
        answer2.setOnClickListener(this);
        answer3.setOnClickListener(this);
        answer4.setOnClickListener(this);
        playAgain = findViewById(R.id.playAgain);
        layout = findViewById(R.id.relative);

      }

      void generateQuestion()
      {
          Random rand =new Random();
          int a = rand.nextInt(21);
          int b = rand.nextInt(21);
          sumText.setText(Integer.toString(a)+"+"+Integer.toString(b));
          correctAnswerlocation = rand.nextInt(4);
          int incorrectAnswer;
          for(int i=0;i<4;++i)
          {
              if(i==correctAnswerlocation)
                  answers[i] = a+b;
              else
              {
                  incorrectAnswer = rand.nextInt(41);
                  if(incorrectAnswer==a+b)
                      answers[i] = incorrectAnswer + rand.nextInt(5)+1;
                  else
                      answers[i] = incorrectAnswer;
              }
          }
          answer1.setText(Integer.toString(answers[0]));
          answer2.setText(Integer.toString(answers[1]));
          answer3.setText(Integer.toString(answers[2]));
          answer4.setText(Integer.toString(answers[3]));


      }
      void playAgainMethod(View view)
      {

          grid.setVisibility(View.VISIBLE);
          score = 0;
          round = 0;
          generateQuestion();
          timer.setText("30S");
          scoreText.setText("0/0");
          answerText.setText("");
          new CountDownTimer(30100,1000)
          {

              @Override
              public void onTick(long l) {
                  timer.setText(String.valueOf(l/1000)+"s");

              }

              @Override
              public void onFinish() {
                  timer.setText("0s");
                  answerText.setText("Your Score:"+Integer.toString(score)+"/"+Integer.toString(round));
                  grid.setVisibility(View.GONE);
                  playAgain.setVisibility(View.VISIBLE);

              }
          }.start();


      }
}
