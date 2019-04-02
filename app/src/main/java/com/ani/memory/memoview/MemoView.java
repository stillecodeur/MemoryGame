package com.ani.memory.memoview;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;

import com.ani.memory.R;
import com.ani.memory.domain.RandomNumberListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoView extends ConstraintLayout implements Memo, RandomNumberListener {


    private Context context;
    private MemoNumView numView1, numView2, numView3, numView4, numView5, numView6, numView7, numView8, numView9;
    private int numberToBeRevealed;
    private List<Integer> randomNumberList;
    private OnMatchListener onMatchListener;
    private OnFinishListener onFinishListener;
    private int revealCount = 0;
    private boolean touchDisabled = false;


    public MemoView(Context context) {
        super(context);
        init(context);
    }

    public MemoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    private void init(Context context) {
        this.context = context;

        View view = inflate(context, R.layout.memoview_layout, null);

        numView1 = view.findViewById(R.id.num1);
        numView2 = view.findViewById(R.id.num2);
        numView3 = view.findViewById(R.id.num3);
        numView4 = view.findViewById(R.id.num4);
        numView5 = view.findViewById(R.id.num5);
        numView6 = view.findViewById(R.id.num6);
        numView7 = view.findViewById(R.id.num7);
        numView8 = view.findViewById(R.id.num8);
        numView9 = view.findViewById(R.id.num9);

        addView(view);


        setNumViewClickListener(numView1);
        setNumViewClickListener(numView2);
        setNumViewClickListener(numView3);
        setNumViewClickListener(numView4);
        setNumViewClickListener(numView5);
        setNumViewClickListener(numView6);
        setNumViewClickListener(numView7);
        setNumViewClickListener(numView8);
        setNumViewClickListener(numView9);

        randomNumberList = new ArrayList<>();


    }


    private void setNumViewClickListener(final MemoNumView memoNumView) {
        memoNumView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (touchDisabled) return;

                if (memoNumView.isOpen()) return;

                if (onMatchListener == null) return;

                if (numberToBeRevealed == memoNumView.getNumber()) {
                    memoNumView.show();
                    revealCount += 1;

                    if (revealCount == 9) {
                        if (onFinishListener != null) {
                            onFinishListener.onFinish();
                        }
                    } else {
                        onMatchListener.onSuccess();
                    }


                } else {
                    onMatchListener.onFailure();
                }


            }
        });
    }


    @Override
    public void hide() {
        numView1.hide();
        numView2.hide();
        numView3.hide();
        numView4.hide();
        numView5.hide();
        numView6.hide();
        numView7.hide();
        numView8.hide();
        numView9.hide();
    }

    @Override
    public void show() {
        numView1.show();
        numView2.show();
        numView3.show();
        numView4.show();
        numView5.show();
        numView6.show();
        numView7.show();
        numView8.show();
        numView9.show();
    }


    @Override
    public void setNumberToReveal(int numberToReveal) {
        this.numberToBeRevealed = numberToReveal;
    }


    @Override
    public void start() {
        revealCount = 0;
        shuffle();
        setNumViewNumbers();
        hide();
    }

    @Override
    public void disableTouch() {
        touchDisabled = true;
    }

    @Override
    public void enableTouch() {
        touchDisabled = false;
    }

    @Override
    public void shuffle() {
        Collections.shuffle(randomNumberList);
    }

    @Override
    public void setOnMatchListener(OnMatchListener onMatchListener) {
        this.onMatchListener = onMatchListener;
    }

    @Override
    public void setOnFinishListener(OnFinishListener onFinishListener) {
        this.onFinishListener = onFinishListener;
    }


    private void setNumViewNumbers() {
        numView1.setNumber(randomNumberList.get(0));
        numView2.setNumber(randomNumberList.get(1));
        numView3.setNumber(randomNumberList.get(2));
        numView4.setNumber(randomNumberList.get(3));
        numView5.setNumber(randomNumberList.get(4));
        numView6.setNumber(randomNumberList.get(5));
        numView7.setNumber(randomNumberList.get(6));
        numView8.setNumber(randomNumberList.get(7));
        numView9.setNumber(randomNumberList.get(8));
    }

    @Override
    public void setRandomNumbers(List<Integer> numbers) {
        this.randomNumberList = numbers;
    }
}
