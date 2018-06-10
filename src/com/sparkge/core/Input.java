package com.sparkge.core;

import com.sparkge.rendering.Window;

import static org.lwjgl.glfw.GLFW.*;

public class Input {
    private Keyboard keyboard;
    private Mouse mouse;
    private Window window;

    public Input(Keyboard keyboard, Mouse mouse, Window window) {
        this.keyboard = keyboard;
        this.mouse = mouse;
        this.window = window;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public Mouse getMouse() {
        return mouse;
    }

    /**
     * @description sets window callbacks for key and mouse button inputs
     */
    public void init() {
        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(this.window.getContextHandle(), (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE ) {
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
            } else {
                System.out.println(key);
            }
        });
        glfwSetMouseButtonCallback(this.window.getContextHandle(), (window, button, action, mods) -> {
           System.out.println(button);
        });

    }
}
