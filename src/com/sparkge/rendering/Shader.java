package com.sparkge.rendering;

import static org.lwjgl.opengl.GL20.*;

public class Shader {

    //OPENGL Program ID
    private final int programId;

    //vertex shader ID
    private int vShaderId;

    //fragment shader ID
    private int fShaderId;

    //geometry shader ID
    private int gShaderId;


    public Shader() throws ShaderException {
        programId = glCreateProgram();

        if (programId == 0) {
            throw new ShaderException("Could not create Shader");
        }
    }


    //creates and sets Shader of type VERTEX
    public void setVertexShader(String shaderText) throws ShaderException {
        vShaderId = setShader(shaderText, GL_VERTEX_SHADER);
    }

    //creates and sets Shader of type FRAGMENT
    public void setFragmentShader(String shaderText) throws ShaderException{
        fShaderId = setShader(shaderText, GL_FRAGMENT_SHADER);
    }

    //creates and sets Shader of type GEOMETRY
//    public void setGeometryShader(String shaderText) throws ShaderException {
//        gShaderId = setShader(shaderText, GL_GEOMETRY_SHADER);
//    }

    //
    public void link() throws ShaderException {
        glLinkProgram(programId);

        if (glGetProgrami(programId, GL_LINK_STATUS) == 0) {
            throw new ShaderException("Error linking Shader code: " + glGetProgramInfoLog(programId, 1024));
        }

        //once the new Shader Program has been linked ==> can detach Shaders
        if (vShaderId != 0) {
            glDetachShader(programId, vShaderId);
        }
        if (fShaderId != 0) {
            glDetachShader(programId, fShaderId);
        }

        //primarily used for debugging ==> should be removed for production
        glValidateProgram(programId);
        if (glGetProgrami(programId, GL_VALIDATE_STATUS) == 0) {
            System.err.println("Warning validating Shader code: " + glGetProgramInfoLog(programId, 1024));
        }
    }

    //activate the OPENGL program for rendering
    public void bind() {
        glUseProgram(programId);
    }

    //stop the OPENGL program
    public void unbind() {
        glUseProgram(0);
    }

    //unbind and delete the OPENGL program
    public void cleanup() {
        unbind();

        if (programId != 0) {
            glDeleteProgram(programId);
        }
    }

    private int setShader(String shaderText, int shaderType) throws ShaderException {
        //creates new shader of specified type (VERTEX, FRAGMENT, GEOMETRY)
        int shaderId = glCreateShader(shaderType);

        if (shaderId == 0) {
            throw new ShaderException("Error creating shader. Type: " + shaderText);
        }

        System.err.println("SHADER ID: " + shaderId);

        glShaderSource(shaderId, shaderText);
        glCompileShader(shaderId);

        if (glGetShaderi(shaderId, GL_COMPILE_STATUS) == 0) {
            throw new ShaderException("Error compiling Shader code: " + glGetShaderInfoLog(shaderId, 1024));
        }

        glAttachShader(programId, shaderId);

        return shaderId;
    }

}
