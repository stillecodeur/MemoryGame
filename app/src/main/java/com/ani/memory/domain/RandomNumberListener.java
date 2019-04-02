package com.ani.memory.domain;

import java.util.List;

public interface RandomNumberListener {
    void setRandomNumbers(List<Integer> numbers);
    void shuffle();
}
