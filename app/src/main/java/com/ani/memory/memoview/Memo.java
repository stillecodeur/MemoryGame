package com.ani.memory.memoview;

public interface Memo extends MemoVisibility {


    interface OnMatchListener {
        void onSuccess();

        void onFailure();
    }


    interface OnFinishListener{
        void onFinish();
    }

    void setNumberToReveal(int numberToReveal);

    void start();

    void disableTouch();

    void enableTouch();

    void setOnMatchListener(OnMatchListener onMatchListener);

    void setOnFinishListener(OnFinishListener onFinishListener);
}
