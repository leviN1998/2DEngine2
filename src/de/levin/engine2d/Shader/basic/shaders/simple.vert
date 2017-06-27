#version 400

in vec2 position;
in vec2 textureCoordinates;

uniform mat4 pr_matrix;
uniform mat4 transformation;
uniform vec4 uni_color;

out vec4 color;

void main(void){

    gl_Position = pr_matrix * transformation * vec4(position, 0, 1);
    color = uni_color;

}