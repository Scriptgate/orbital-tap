package net.scriptgate.orbital.tap;

public class Score {

    private int score = 0;

    public void increase(int i) {
        score+= i;
    }

    public void decrease(int i) {
        score -= i;
    }

    public int value() {
        return score;
    }

    public int slowCost() {
        return 15;
    }
}
