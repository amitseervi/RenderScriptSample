#pragma version(1)
#pragma rs java_package_name(com.alinus.renderscriptsample)

static const float4 weight = {0.299f, 0.587f, 0.114f, 0.0f};
void init() {
}
uchar4 __attribute__((kernel)) invert(uchar4 in){
    uchar4 out=in;
    out.r= 255-in.r;
    out.b= 255-in.b;
    out.g= 255-in.g;
    return out;
}