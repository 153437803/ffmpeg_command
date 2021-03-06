/*
 * Copyright (c) 2020 DevYK
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

#ifndef MOBILE_FFPROBE_H
#define MOBILE_FFPROBE_H

#include <jni.h>

/*
 * Class:     com_devyk_ffmpeglib_config_Config
 * Method:    nativeFFprobeExecute
 * Signature: ([Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_lib_kalu_ffmpegcmd_cmd_Cmd_nativeFFprobeExecute(JNIEnv *, jclass, jobjectArray);

#endif /* MOBILE_FFPROBE_H */