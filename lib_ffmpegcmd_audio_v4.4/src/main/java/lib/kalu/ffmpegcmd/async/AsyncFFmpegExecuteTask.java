package lib.kalu.ffmpegcmd.async;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Keep;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lib.kalu.ffmpegcmd.callback.ExecuteCallback;
import lib.kalu.ffmpegcmd.callback.LogCallback;
import lib.kalu.ffmpegcmd.cmd.Cmd;
import lib.kalu.ffmpegcmd.entity.LogMessage;
import lib.kalu.ffmpegcmd.ffmpeg.FFmpeg;

@Keep
public class AsyncFFmpegExecuteTask extends AsyncTask<Void, Integer, Integer> {

    private final String[] arguments;
    private static ExecuteCallback sExecuteCallback;
    private final Long executionId;
    private AsyncLogCallback mAsyncLogCallback;
    protected static Handler mHandler = null;

    public AsyncFFmpegExecuteTask(final String[] arguments, final ExecuteCallback executeCallback) {
        this(FFmpeg.DEFAULT_EXECUTION_ID, arguments, executeCallback);
    }

    public AsyncFFmpegExecuteTask(final long executionId, final String[] arguments, final ExecuteCallback executeCallback) {
        this.executionId = executionId;
        this.arguments = arguments;
        onDestory();
        sExecuteCallback = executeCallback;
//        try {
//            int index;
//            if (arguments[0].equalsIgnoreCase("ffmpeg")) {
//                index = 3;
//            } else {
//                index = 2;
//            }
//            long duration = VideoUitls.getDuration(arguments[index]);
//            if (duration <= 0) {
//                duration = -1L;
//            }
//            mDuration = duration;
//        } catch (Exception e) {
//            mDuration = -1L;
//        }
//        FFmpegLogUtil.logE("AsyncFFmpegExecuteTask => mDuration = " + mDuration);
        mHandler = new Handler(Looper.getMainLooper());
        mAsyncLogCallback = new AsyncLogCallback();
        enableLogCallback(mAsyncLogCallback);
    }

    private void onDestory() {
//        mDuration = -1L;
        if (sExecuteCallback != null) {
            sExecuteCallback = null;
        }
        if (mAsyncLogCallback != null) {
            enableLogCallback(null);
            mAsyncLogCallback = null;
        }

        if (mHandler != null) {
            mHandler = null;
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (sExecuteCallback != null) {
            sExecuteCallback.onStart(executionId);
        }

    }

    private void enableLogCallback(AsyncLogCallback asyncLogCallback) {
        Cmd.enableLogCallback(asyncLogCallback);
    }

    /**
     * ??????????????????
     *
     * @param unused
     * @return
     */
    @Override
    protected Integer doInBackground(final Void... unused) {
        return Cmd.ffmpegExecute(executionId, this.arguments);
    }

    /**
     * ??????????????????
     *
     * @param rc
     */
    @Override
    protected void onPostExecute(final Integer rc) {
        synchronized (this) {
            if (sExecuteCallback != null) {
                if (rc == 0)
                    sExecuteCallback.onSuccess(executionId);
                else if (rc == 255)
                    sExecuteCallback.onCancel(executionId);
            }
        }
    }

    /**
     * ????????????????????????
     *
     * @param num
     */
    public static boolean judgeContainsStr(String num) {
        String regex = ".*[a-zA-Z]+.*";
        Matcher m = Pattern.compile(regex).matcher(num);
        return m.matches();
    }

    public static boolean judgeContainsStr(float num_f) {
        boolean contains;
        String num = String.valueOf(num_f);
        try {
            contains = judgeContainsStr(num);
        } catch (Exception e) {
            contains = false;
        }
        return contains;
    }

    /**
     * ????????? LOG message
     */
    protected class AsyncLogCallback implements LogCallback {
        @Override
        public void apply(final LogMessage message) {
            synchronized (this) {
                switch (message.getLevel()) {
                    case AV_LOG_FATAL:
                    case AV_LOG_ERROR:
                        if (sExecuteCallback != null && mHandler != null)
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    sExecuteCallback.onFailure(executionId, message.getText());
                                }
                            });
                        break;
                    case AV_LOG_INFO:

                        if (message.isProcess() && sExecuteCallback != null && mHandler != null)
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {

                                    int position = 0;
                                    String trim = message.getText().trim();
                                    Pattern pattern1 = Pattern.compile("time=(.*?)bitrate=");
                                    Matcher m1 = pattern1.matcher(trim);
                                    if (m1.find()) {
                                        String group = m1.group(1);
                                        String[] strs = group.split(":");
                                        if (strs[0].compareTo("0") > 0) {
                                            position += Integer.valueOf(strs[0]) * 60 * 60;//???
                                        }
                                        if (strs[1].compareTo("0") > 0) {
                                            position += Integer.valueOf(strs[1]) * 60;
                                        }
                                        if (strs[2].compareTo("0") > 0) {
                                            position += Math.round(Float.valueOf(strs[2]));
                                        }
                                    }


                                    int duration = 0;
                                    String output = Cmd.getLastCommandOutput();
                                    Pattern pattern2 = Pattern.compile("Duration: (.*?), start: (.*?), bitrate: (\\d*) kb\\/s");
                                    Matcher m2 = pattern2.matcher(output);
                                    if (m2.find()) {
                                        String group = m2.group(1);
                                        String[] strs = group.split(":");
                                        if (strs[0].compareTo("0") > 0) {
                                            duration += Integer.valueOf(strs[0]) * 60 * 60;//???
                                        }
                                        if (strs[1].compareTo("0") > 0) {
                                            duration += Integer.valueOf(strs[1]) * 60;
                                        }
                                        if (strs[2].compareTo("0") > 0) {
                                            duration += Math.round(Float.valueOf(strs[2]));
                                        }
                                    }

                                    if (position > 0 && duration > 0 && position <= duration) {
                                        float rate = position * 100 / duration;
                                        sExecuteCallback.onProgress(duration, position, rate);
//                                        Log.e("onProgress1", "position = " + position + ", duration = " + duration);
                                    } else {
//                                        Log.e("onProgress1", "position =0, duration = " + duration);
                                    }
//                                    long value = duration * 1000000;
//                                    if (second >= value) {
//                                        if (second - value > 1000000) {
//                                            sExecuteCallback.onProgress(value, 0, 0f);
//                                        } else {
//                                            sExecuteCallback.onProgress(value, value, 100f);
//                                        }
//                                    } else {
//                                        float rate = second * 100 / value;
//                                        sExecuteCallback.onProgress(value, second, rate);
//                                    }

//                                    sExecuteCallback.onProgress(executionId, message.getText());
                                }
                            });
                        break;
                    default:
                        break;
                }
                if (sExecuteCallback != null && mHandler != null)
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            sExecuteCallback.onMessage(message);
                        }
                    });
            }
        }
    }
}
