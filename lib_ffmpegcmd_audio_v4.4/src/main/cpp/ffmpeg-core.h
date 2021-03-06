/*
 * Copyright (c) 2018 DevYK
 *
 * This file is part of MobileFFmpeg.
 *
 * MobileFFmpeg is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MobileFFmpeg is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with MobileFFmpeg.  If not, see <http://www.gnu.org/licenses/>.
 */

#ifndef MOBILE_FFMPEG_H
#define MOBILE_FFMPEG_H

#include <jni.h>
#include <android/log.h>

#include "libavutil/log.h"
#include "libavutil/ffversion.h"

/** Library version string */
#define MOBILE_FFMPEG_VERSION "4.4"

/** Defines tag used for Android logging. */
#define LIB_NAME "ffmpeg-cmd"

/** Verbose Android logging macro. */
#define LOGV(...) __android_log_print(ANDROID_LOG_VERBOSE, LIB_NAME, __VA_ARGS__)

/** Debug Android logging macro. */
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LIB_NAME, __VA_ARGS__)

/** Info Android logging macro. */
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LIB_NAME, __VA_ARGS__)

/** Warn Android logging macro. */
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN, LIB_NAME, __VA_ARGS__)

/** Error Android logging macro. */
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LIB_NAME, __VA_ARGS__)

/*
 * Class:     com_devyk_ffmpeglib_config_Config
 * Method:    enableNativeRedirection
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_lib_kalu_ffmpegcmd_cmd_Cmd_enableNativeRedirection(JNIEnv *, jclass);

/*
 * Class:     com_devyk_ffmpeglib_config_Config
 * Method:    disableNativeRedirection
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_lib_kalu_ffmpegcmd_cmd_Cmd_disableNativeRedirection(JNIEnv *, jclass);

/*
 * Class:     com_devyk_ffmpeglib_config_Config
 * Method:    setNativeLogLevel
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_lib_kalu_ffmpegcmd_cmd_Cmd_setNativeLogLevel(JNIEnv *, jclass, jint);

/*
 * Class:     com_devyk_ffmpeglib_config_Config
 * Method:    getNativeLogLevel
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_lib_kalu_ffmpegcmd_cmd_Cmd_getNativeLogLevel(JNIEnv *, jclass);

/*
 * Class:     com_devyk_ffmpeglib_config_Config
 * Method:    getNativeFFmpegVersion
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_lib_kalu_ffmpegcmd_cmd_Cmd_getNativeFFmpegVersion(JNIEnv *, jclass);

/*
 * Class:     com_devyk_ffmpeglib_config_Config
 * Method:    getNativeVersion
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_lib_kalu_ffmpegcmd_cmd_Cmd_getNativeVersion(JNIEnv *, jclass);

/*
 * Class:     com_devyk_ffmpeglib_config_Config
 * Method:    nativeFFmpegExecute
 * Signature: (J[Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL
Java_lib_kalu_ffmpegcmd_cmd_Cmd_nativeFFmpegExecute(JNIEnv *, jclass, jlong id, jobjectArray);

/*
 * Class:     com_devyk_ffmpeglib_config_Config
 * Method:    nativeFFmpegCancel
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_lib_kalu_ffmpegcmd_cmd_Cmd_nativeFFmpegCancel(JNIEnv *, jclass, jlong);

/*
 * Class:     com_devyk_ffmpeglib_config_Config
 * Method:    registerNewNativeFFmpegPipe
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT int JNICALL
Java_lib_kalu_ffmpegcmd_cmd_Cmd_registerNewNativeFFmpegPipe(JNIEnv *env, jclass object,
                                                            jstring ffmpegPipePath);

/*
 * Class:     com_devyk_ffmpeglib_config_Config
 * Method:    getNativeBuildDate
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL
Java_lib_kalu_ffmpegcmd_cmd_Cmd_getNativeBuildDate(JNIEnv *env, jclass object);

/**
 * Class:     com_devyk_ffmpeglib_config_Config
 * Method:    setNativeEnvironmentVariable
 * Signature: (Ljava/lang/String;Ljava/lang/String;)I
 */
JNIEXPORT int JNICALL
Java_lib_kalu_ffmpegcmd_cmd_Cmd_setNativeEnvironmentVariable(JNIEnv *env, jclass object,
                                                             jstring variableName,
                                                             jstring variableValue);

/*
 * Class:     com_devyk_ffmpeglib_config_Config
 * Method:    getNativeLastCommandOutput
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL
Java_lib_kalu_ffmpegcmd_cmd_Cmd_getNativeLastCommandOutput(JNIEnv *env, jclass object);

/*
 * Class:     com_devyk_ffmpeglib_config_Config
 * Method:    ignoreNativeSignal
 * Signature: (I)V
 */
JNIEXPORT void JNICALL
Java_lib_kalu_ffmpegcmd_cmd_Cmd_ignoreNativeSignal(JNIEnv *env, jclass object, jint signum);

#endif /* MOBILE_FFMPEG_H */