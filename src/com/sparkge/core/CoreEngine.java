/**
 * @description game engine class
 * @author Jeffrey Underdown | junderdown@radialspark.com
 * @history
 *  2018-05-26 | junderdown | Created
 */

package com.sparkge.core;

import com.sparkge.rendering.Shader;
import com.sparkge.rendering.ShaderException;
import com.sparkge.rendering.Window;
import org.lwjgl.system.MemoryUtil;

import java.lang.Runnable;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;
import static org.lwjgl.system.MemoryUtil.memFree;

public class CoreEngine implements Runnable {

    // TODO: uncomment member variable references once implemented

    // private Settings settings;
    private Window window;
    // private IGame game;

    private Shader shaderProgram;
    private int vaoId;

    /**
     * @description constructor
     * @param window the opengl window wrapper class
     * @param settings game settings
     * @param game the game to be played!
     */
    public CoreEngine(Window window/* Settings settings, IGame game */) {
        this.window = window;
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
        this.window.init();

        try {
            System.err.println("INITIALIZING SHADER");

            initShader();
        } catch (ShaderException e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    /**
     * description destroys game engine and frees memory
     */
    private void cleanup() {
        this.window.destroy();
    }

    private void initShader() throws ShaderException {

        //initialize the Shader Program
        shaderProgram = new Shader();
        shaderProgram.setVertexShader("#version 440 layout (location=0) in vec3 position; void main() { gl_Position = vec4(position, 1.0); }");
        shaderProgram.setFragmentShader("#version 440 out vec4 fragColor; void main() { fragColor = vec4(0.0, 0.5, 0.5, 1.0); }");
        shaderProgram.link();

        //declare vertices of test triangle
        float[] vertices = new float[] {
                0.0f, 0.5f, 0.0f,
                -0.5f, -0.5f, 0.0f,
                0.5f, -0.5f, 0.0f
        };

        //store vertices as a Float Buffer
        FloatBuffer verticesBuffer = MemoryUtil.memAllocFloat(vertices.length);
        verticesBuffer.put(vertices).flip();

        //create the Vertex Array Object
        vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);

        //create the Vertex Buffer Object ==> bind it and add vertices data
        int vboId = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboId);
        glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
        memFree(verticesBuffer);

        //define the data structure and store as one attribute in the VAO
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

        //unbind VBO
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        //unbind the VAO
        glBindVertexArray(0);

        //free memory from FloatBuffer
        if (verticesBuffer != null) {
            MemoryUtil.memFree(verticesBuffer);
        }

        renderShader();
    }

    private void renderShader() {
        System.err.println("ABOUT TO BIND SHADER");

        shaderProgram.bind();

        System.err.println("BOUND SHADER");

        glBindVertexArray(vaoId);
        glEnableVertexAttribArray(0);

        glDrawArrays(GL_TRIANGLES, 0, 3);

        glDisableVertexAttribArray(0);
        glBindVertexArray(0);

        shaderProgram.unbind();
    }
}
