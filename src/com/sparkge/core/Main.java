package com.sparkge.core;

import com.sparkge.rendering.Window;
import org.lwjgl.*;

public class Main {

    private CoreEngine engine;

    public void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");
        this.init();
    }

    private void init() {
        Window window = new Window("Game", 1920, 1080);
        engine = new CoreEngine(window);
        engine.start();
    }

    public static void main(String[] args) {
        new Main().run();
    }

}