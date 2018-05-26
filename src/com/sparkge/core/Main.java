package com.sparkge.core;

import org.lwjgl.*;

public class Main {

    private CoreEngine engine;

    public void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");
        this.init();
    }

    private void init() {
        engine = new CoreEngine();
        engine.start();
    }

    public static void main(String[] args) {
        new Main().run();
    }

}