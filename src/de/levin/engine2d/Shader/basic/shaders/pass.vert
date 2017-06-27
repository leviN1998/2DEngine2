#version 400

layout(location = 0)in vec2 position;
layout(location = 1)in vec2 textureCoordinates;

uniform mat4 pr_matrix;
uniform mat4 transformation;

out vec2 pass_tc;


void main() {

	gl_Position = pr_matrix * transformation * vec4(position, 0, 1);
	pass_tc = textureCoordinates;

}
