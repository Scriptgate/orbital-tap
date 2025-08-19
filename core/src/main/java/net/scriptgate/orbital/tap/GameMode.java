package net.scriptgate.orbital.tap;

public class GameMode {

    enum State {
        START_SCREEN,
        GAME,
        CREDITS
    }

    State state = State.START_SCREEN;

}
