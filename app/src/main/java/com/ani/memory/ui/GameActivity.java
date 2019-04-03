package com.ani.memory.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ani.memory.R;
import com.ani.memory.memoview.Memo;
import com.ani.memory.memoview.MemoView;
import com.ani.memory.numgenview.NumGeneratorView;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {


    private Context mContext;
    private MemoView mMemoView;
    private NumGeneratorView mNumGenView;
    private AlertDialog mAlertMatch;
    private AlertDialog mFinishAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = GameActivity.this;
        createMatchAlert();
        createFinishAlert();

        mMemoView = findViewById(R.id.memo_view);
        mNumGenView = findViewById(R.id.num_gen_view);


        final List<Integer> randomNumbers = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            randomNumbers.add(i);
        }

        mMemoView.setRandomNumbers(randomNumbers);
        mNumGenView.setRandomNumbers(randomNumbers);

        randomize();
        startGame();

        mMemoView.setOnMatchListener(new Memo.OnMatchListener() {
            @Override
            public void onSuccess() {
                mMemoView.setNumberToReveal(mNumGenView.getGeneratedNumber());
            }

            @Override
            public void onFailure() {
                showMatchAlert();
            }


        });


        mMemoView.setOnFinishListener(new Memo.OnFinishListener() {
            @Override
            public void onFinish() {
                showFinishAlert();
            }
        });

    }


    /**
     * Shuffles the numbers in MemoView and MemoGenView
     */
    private void randomize() {
        mMemoView.shuffle();
        mNumGenView.shuffle();
        mMemoView.start();
    }


    /**
     * Starts the game with showing numbers for 5 seconds
     */
    private void startGame() {
        mMemoView.show();
        mMemoView.disableTouch();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mMemoView.enableTouch();
                mMemoView.hide();
                mMemoView.setNumberToReveal(mNumGenView.getGeneratedNumber());
            }
        }, 5000);
    }


    /**
     * Creates AlertDialog to inform user that he has chosen a wrong tile.
     */
    private void createMatchAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(getString(R.string.match_fail_title)).setMessage(R.string.match_fail_message).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        mAlertMatch = builder.create();

    }

    /**
     * Creates AlertDialog to inform user that the game has finished.
     */
    private void createFinishAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(getString(R.string.finish_title)).setMessage(getString(R.string.finish_message)).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                randomize();
                startGame();
                dialog.dismiss();
            }
        });


        mFinishAlert = builder.create();

        mFinishAlert.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                randomize();
                startGame();
            }
        });
    }


    /**
     * Alerts user that game has finished.
     */
    private void showFinishAlert() {
        mFinishAlert.show();
    }

    /**
     * Alerts user that he has chosen a wrong tile.
     */
    private void showMatchAlert() {
        mAlertMatch.show();
    }


}
