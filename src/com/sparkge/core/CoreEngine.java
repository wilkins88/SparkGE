/**
 * @description game engine class
 * @author Jeffrey Underdown | junderdown@radialspark.com
 * @history
 *  2018-05-26 | junderdown | Created
 */

package com.sparkge.core;

import com.sparkge.rendering.Window;

import java.lang.Runnable;

import com.sparkge.game.IGame;

public class CoreEngine implements Runnable {

    // TODO: uncomment member variable references once implemented

    // private Settings settings;
    private Window window;
    private IGame game;
    private Input input;

    /**
     * @description constructor
     * @param window the opengl window wrapper class
     * @param settings game settings
     * @param game the game to be played!
     */
    public CoreEngine(Window window/* Settings settings*/, IGame game, Input input) {
        this.window = window;
        // this.settings = settings;
        this.game = game;
        this.input = input;
    }

    /**
     * description initializes engine and starts the game loop
     */
    public void start() {
        this.init();
        this.run();
    }

    /**
     * @description runs the game loop
     */
    public void run() {
        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( !this.window.shouldClose() ) {
            this.window.update();
        }

        this.cleanup();
    }

    /**
     * @description initializes game engine
     */
    private void init() {
        // this.settings = settings;
        this.window.init();
        this.input.init();
    }

    /**
     * description destroys game engine and frees memory
     */
    private void cleanup() {
        this.window.destroy();
    }
}
