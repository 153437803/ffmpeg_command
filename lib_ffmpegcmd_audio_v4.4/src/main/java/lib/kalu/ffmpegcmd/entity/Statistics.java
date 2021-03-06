package lib.kalu.ffmpegcmd.entity;

public class Statistics {

    private long executionId;
    private int videoFrameNumber;
    private float videoFps;
    private float videoQuality;
    private long size;
    private int time;
    private double bitrate;
    private double speed;

    public Statistics() {
        executionId = 0;
        videoFrameNumber = 0;
        videoFps = 0;
        videoQuality = 0;
        size = 0;
        time = 0;
        bitrate = 0;
        speed = 0;
    }

    public Statistics(long executionId, int videoFrameNumber, float videoFps, float videoQuality, long size, int time, double bitrate, double speed) {
        this.executionId = executionId;
        this.videoFrameNumber = videoFrameNumber;
        this.videoFps = videoFps;
        this.videoQuality = videoQuality;
        this.size = size;
        this.time = time;
        this.bitrate = bitrate;
        this.speed = speed;
    }

    public void update(final Statistics newStatistics) {
        if (newStatistics != null) {
            this.executionId = newStatistics.getExecutionId();
            if (newStatistics.getVideoFrameNumber() > 0) {
                this.videoFrameNumber = newStatistics.getVideoFrameNumber();
            }
            if (newStatistics.getVideoFps() > 0) {
                this.videoFps = newStatistics.getVideoFps();
            }

            if (newStatistics.getVideoQuality() > 0) {
                this.videoQuality = newStatistics.getVideoQuality();
            }

            if (newStatistics.getSize() > 0) {
                this.size = newStatistics.getSize();
            }

            if (newStatistics.getTime() > 0) {
                this.time = newStatistics.getTime();
            }

            if (newStatistics.getBitrate() > 0) {
                this.bitrate = newStatistics.getBitrate();
            }

            if (newStatistics.getSpeed() > 0) {
                this.speed = newStatistics.getSpeed();
            }
        }
    }

    public long getExecutionId() {
        return executionId;
    }

    public void setExecutionId(long executionId) {
        this.executionId = executionId;
    }

    public int getVideoFrameNumber() {
        return videoFrameNumber;
    }

    public void setVideoFrameNumber(int videoFrameNumber) {
        this.videoFrameNumber = videoFrameNumber;
    }

    public float getVideoFps() {
        return videoFps;
    }

    public void setVideoFps(float videoFps) {
        this.videoFps = videoFps;
    }

    public float getVideoQuality() {
        return videoQuality;
    }

    public void setVideoQuality(float videoQuality) {
        this.videoQuality = videoQuality;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getBitrate() {
        return bitrate;
    }

    public void setBitrate(double bitrate) {
        this.bitrate = bitrate;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Statistics{");
        stringBuilder.append("executionId=");
        stringBuilder.append(executionId);
        stringBuilder.append(", videoFrameNumber=");
        stringBuilder.append(videoFrameNumber);
        stringBuilder.append(", videoFps=");
        stringBuilder.append(videoFps);
        stringBuilder.append(", videoQuality=");
        stringBuilder.append(videoQuality);
        stringBuilder.append(", size=");
        stringBuilder.append(size);
        stringBuilder.append(", time=");
        stringBuilder.append(time);
        stringBuilder.append(", bitrate=");
        stringBuilder.append(bitrate);
        stringBuilder.append(", speed=");
        stringBuilder.append(speed);
        stringBuilder.append('}');

        return stringBuilder.toString();
    }

}
