/**
 * @description game engine class
 * @author Jeffrey Underdown | junderdown@radialspark.com
 * @history
 *  2018-05-26 | junderdown | Created
 */

package com.sparkge.core;

import com.sparkge.rendering.Window;

import java.lang.Runnable;

public class CoreEngine implements Runnable {

    // TODO: uncomment member variable references once implemented

    // private Settings settings;
    private Window window;
    // private IGame game;

    /**
     * @description constructor
     * @param settings
     * @param game
     */
    public CoreEngine(/* Settings settings, IGame game */) {
        // this.settings = settings;
        // this.game = game;
    }

    /**
     * description initializes engine and starts the game loop
     */
    public void start() {
        this.run();
    }

    /**
     * @description runs the game loop
     */
    public void run() {
        this.init();

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
        this.window = new Window("Game", 1920, 1080);
        this.window.init();
    }

    /**
     * description destroys game engine and frees memory
     */
    private void cleanup() {
        this.window.destroy();
    }
}
