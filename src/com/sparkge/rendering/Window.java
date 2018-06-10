/**
 * @description game engine window class
 * @author Jeffrey Underdown | junderdown@radialspark.com
 * @history
 *  2018-05-26 | junderdown | Created
 */

package com.sparkge.rendering;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {

    private String title;
    private int width, height;
    private long contextHandle;

    /**
     * @description constructor
     * @param title window title
     * @param width window width in pixels
     * @param height window height in pixels
     */
    public Window(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    /**
     * @description initializes window
     */
    public void init() {

        this.initGLFW();

        // Create the window
        this.contextHandle = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
        if ( this.contextHandle == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        // Get the thread stack and push a new frame
        try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(this.contextHandle, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    this.contextHandle,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(this.contextHandle);
        // Enable v-sync
        glfwSwapInterval(1);

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        // Make the window visible
        glfwShowWindow(this.contextHandle);

        // Set the clear color
        glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
    }

    /**
     * @description updates window
     */
    public void update() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

        glfwSwapBuffers(this.contextHandle); // swap the color buffers

        // Poll for window events. The key callback above will only be
        // invoked during this call.
        glfwPollEvents();
    }

    /**
     * @description destroys window and frees memory
     */
    public void destroy() {
        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(this.contextHandle);
        glfwDestroyWindow(this.contextHandle);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    /**
     * @description returns whether window should close
     * @return true if window should close
     */
    public boolean shouldClose() {
        return glfwWindowShouldClose(this.contextHandle);
    }

    /**
     * @description initializes GLFW functions
     */
    private void initGLFW() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable
    }

    /**
     * @description getter for title
     * @return window title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * @description getter for width
     * @return window width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * @description getter for height
     * @return window height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * @description getter for context handle
     * @return context handle
     */
    public long getContextHandle() {
        return this.contextHandle;
    }
}
