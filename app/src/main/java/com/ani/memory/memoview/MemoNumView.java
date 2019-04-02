package com.ani.memory.memoview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ani.memory.R;

public class MemoNumView extends RelativeLayout implements MemoNum {

    private Context context;
    private View container;
    private TextView tvNumber;
    private int number;
    private boolean isOpen = false;


    public MemoNumView(Context context) {
        super(context);
        init(context);
    }

    public MemoNumView(Context context, AttributeSet attrs) {
        super(context, attrs);


        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MemoNumView);
        try {
            number = typedArray.getInteger(R.styleable.MemoNumView_num, 1);
        } finally {
            typedArray.recycle();
        }

        init(context);
    }


    private void init(Context context) {
        this.context = context;

        View view = inflate(context, R.layout.memnumview_layout, null);
        container = view.findViewById(R.id.container_layout);
        tvNumber = view.findViewById(R.id.tv_number);
        tvNumber.setText("" + number);

        addView(view);

    }


    @Override
    public void setNumber(int number) {
        this.number = number;
        updateView();
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public boolean isOpen() {
        return isOpen;
    }

    @Override
    public void show() {
        tvNumber.setVisibility(View.VISIBLE);
        isOpen = true;
    }

    @Override
    public void hide() {
        tvNumber.setVisibility(View.INVISIBLE);
        isOpen = false;
    }

    private void updateView() {
        tvNumber.setText("" + number);
    }
}
