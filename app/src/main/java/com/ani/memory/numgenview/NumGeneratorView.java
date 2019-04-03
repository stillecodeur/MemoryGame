package com.ani.memory.numgenview;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.ani.memory.R;
import com.ani.memory.domain.RandomNumberListener;

import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class NumGeneratorView extends ConstraintLayout implements NumGenerator, RandomNumberListener {


    /**
     * View to show a single number
     */

    private Context context;
    private TextView tvGenNum;
    private List<Integer> randomNumberList;
    private Stack<Integer> randomNumberStack;


    public NumGeneratorView(Context context) {
        super(context);
        init(context);

    }

    public NumGeneratorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View view = inflate(context, R.layout.numgenerator_view_layout, null);
        tvGenNum = view.findViewById(R.id.tv_gen_num);

        addView(view);

        randomNumberStack = new Stack<>();

    }


    @Override
    public int getGeneratedNumber() {
        int generatedNumber = randomNumberStack.pop();
        tvGenNum.setText("" + generatedNumber);
        return generatedNumber;
    }

    @Override
    public void setRandomNumbers(List<Integer> numbers) {
        this.randomNumberList = numbers;
    }

    @Override
    public void shuffle() {
        Collections.shuffle(randomNumberList);
        for (Integer integer : randomNumberList) {
            randomNumberStack.push(integer);
        }
        tvGenNum.setText("");
    }


}
