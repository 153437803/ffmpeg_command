//
// Created by YANGJIE8 on 2020/5/22.
//
#include "ffmpeg_progress.h"
#include "ffmpeg-core.h"

static jclass m_clazz = NULL;//当前类(面向java)
static JavaVM *jvm = NULL;
JNIEnv *mEnv = NULL;
jmethodID methodID;

int register_progress_call(JNIEnv *env, jclass pVoid) {
    m_clazz = (*env)->FindClass(env, "lib/kalu/ffmpegcmd/async/AsyncFFmpegExecuteTask");
//    m_clazz = (*env)->NewGlobalRef(env, pVoid);
    methodID = (*env)->GetStaticMethodID(env, m_clazz, "onProgress", "(J)V");
    mEnv = env;
    return 0;
}


void callJavaMethodProgress(JNIEnv *env, jclass clazz, long second) {
    if (clazz == NULL) {
        return;
    }
//    //获取方法ID (I)V指的是方法签名 通过javap -s -public FFmpegCmd 命令生成
//    jmethodID methodID = (*env)->GetStaticMethodID(env, clazz, "onProgress", "(F)V");
//    if (methodID == NULL) {
//        return;
//    }
//    //调用该java方法
//    (*env)->CallStaticVoidMethod(env, clazz, methodID, ret);

    //获取方法ID (I)V指的是方法签名 通过javap -s -public FFmpegCmd 命令生成

    if (methodID == NULL || mEnv == NULL) {
        return;
    }
    //调用该java方法
    (*env)->CallStaticVoidMethod(mEnv, clazz, methodID, second);
}

int ffmpeg_progress(long second) {

//    (*jvm)->AttachCurrentThread(jvm, (void **) &env, NULL);
    callJavaMethodProgress(mEnv, m_clazz, second);
//    (*jvm)->DetachCurrentThread(jvm);

    return 0;
}


