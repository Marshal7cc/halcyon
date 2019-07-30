package com.marshal.halcyon.base.test.design.bridge;

/**
 * @auth: Marshal
 * @date: 2019/7/30
 * @desc:
 */
public abstract class Computer {

    protected Game game;

    void playGame() {
        game.play();
    }

}
