package lib.kalu.ffmpegcmd.entity;

import org.json.JSONObject;

import java.util.List;

public class MediaInformation {

    private static final String KEY_MEDIA_PROPERTIES = "format";
    private static final String KEY_FILENAME = "filename";
    private static final String KEY_FORMAT = "format_name";
    private static final String KEY_FORMAT_LONG = "format_long_name";
    private static final String KEY_START_TIME = "start_time";
    private static final String KEY_DURATION = "duration";
    private static final String KEY_SIZE = "size";
    private static final String KEY_BIT_RATE = "bit_rate";
    private static final String KEY_TAGS = "tags";

    /**
     * Stores all properties.
     */
    private final JSONObject jsonObject;

    /**
     * Stores streams.
     */
    private final List<StreamInformation> streams;

    public MediaInformation(final JSONObject jsonObject, final List<StreamInformation> streams) {
        this.jsonObject = jsonObject;
        this.streams = streams;
    }

    /**
     * Returns file name.
     *
     * @return media file name
     */
    public String getFilename() {
        return getStringProperty(KEY_FILENAME);
    }

    /**
     * Returns format.
     *
     * @return media format
     */
    public String getFormat() {
        return getStringProperty(KEY_FORMAT);
    }

    /**
     * Returns long format.
     *
     * @return media long format
     */
    public String getLongFormat() {
        return getStringProperty(KEY_FORMAT_LONG);
    }

    /**
     * Returns duration.
     *
     * @return media duration in milliseconds
     */
    public String getDuration() {
        return getStringProperty(KEY_DURATION);
    }

    /**
     * Returns start time.
     *
     * @return media start time in milliseconds
     */
    public String getStartTime() {
        return getStringProperty(KEY_START_TIME);
    }

    /**
     * Returns size.
     *
     * @return media size in bytes
     */
    public String getSize() {
        return getStringProperty(KEY_SIZE);
    }

    /**
     * Returns bitrate.
     *
     * @return media bitrate in kb/s
     */
    public String getBitrate() {
        return getStringProperty(KEY_BIT_RATE);
    }

    /**
     * Returns all tags.
     *
     * @return tags dictionary
     */
    public JSONObject getTags() {
        return getProperties(KEY_TAGS);
    }

    /**
     * Returns all streams.
     *
     * @return list of streams
     */
    public List<StreamInformation> getStreams() {
        return streams;
    }

    /**
     * Returns the media property associated with the key.
     *
     * @param key property key
     * @return media property as string or null if the key is not found
     */
    public String getStringProperty(final String key) {
        JSONObject mediaProperties = getMediaProperties();
        if (mediaProperties == null) {
            return null;
        }

        if (mediaProperties.has(key)) {
            return mediaProperties.optString(key);
        } else {
            return null;
        }
    }

    /**
     * Returns the media property associated with the key.
     *
     * @param key property key
     * @return media property as Long or null if the key is not found
     */
    public Long getNumberProperty(String key) {
        JSONObject mediaProperties = getMediaProperties();
        if (mediaProperties == null) {
            return null;
        }

        if (mediaProperties.has(key)) {
            return mediaProperties.optLong(key);
        } else {
            return null;
        }
    }

    /**
     * Returns the media properties associated with the key.
     *
     * @param key properties key
     * @return media properties as a JSONObject or null if the key is not found
     */
    public JSONObject getProperties(String key) {
        JSONObject mediaProperties = getMediaProperties();
        if (mediaProperties == null) {
            return null;
        }

        return mediaProperties.optJSONObject(key);
    }

    /**
     * Returns all media properties.
     *
     * @return all media properties as a JSONObject or null if no media properties are defined
     */
    public JSONObject getMediaProperties() {
        return jsonObject.optJSONObject(KEY_MEDIA_PROPERTIES);
    }

    /**
     * Returns all properties defined.
     *
     * @return all properties as a JSONObject or null if no properties are defined
     */
    public JSONObject getAllProperties() {
        return jsonObject;
    }

}
