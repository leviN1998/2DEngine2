#version 400

in vec2 pass_tc;

out vec4 out_Color;

uniform sampler2D sampler;

void main() {

	out_Color = texture(sampler, pass_tc);

}
