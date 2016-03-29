#include "com_ben_ndk2_MainActivity.h"
JNIEXPORT jint JNICALL Java_com_ben_ndk2_MainActivity_add
        (JNIEnv * env, jobject obj, jint num1, jint num2){
    return num1+num2;
}