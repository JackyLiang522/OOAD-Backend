package com.sustech.ooad.utils;

import java.io.IOException;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import net.bramp.ffmpeg.probe.FFmpegStream;

public class VideoUtil {
    /**
     * change video resolutions
     *
     * @param videoPath
     * @param resolution
     */
    public static void changeResolution(String videoPath, int resolution) throws IOException {
        int height = resolution;
        int width;
        if (height == 240) {
            width = 426;
        } else if (height == 360) {
            width = 640;
        } else if (height == 480) {
            width = 854;
        } else if (height == 720) {
            width = 1280;
        } else if (height == 1080) {
            width = 1920;
        } else {
            throw new IllegalArgumentException("Invalid resolution");
        }
        FFmpeg ffmpeg = new FFmpeg("ffmpeg");
        FFprobe ffprobe = new FFprobe("ffprobe");

        FFmpegBuilder builder = new FFmpegBuilder()

            .setInput(videoPath)     // Filename
            .overrideOutputFiles(true)      // Override the output if it exists

            .addOutput(videoPath.split("\\.")[0] + "_" + resolution + ".mp4")   // Filename for the destination
            .setFormat("mp4")        // Format is inferred from filename, or can be set

            .setAudioCodec("aac")        // using the aac codec
            .setVideoResolution(width, height) //  resolution
            .done();

        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

        // Run a one-pass encode
        executor.createJob(builder).run();
    }

    /**
     * get video resolution
     *
     * @param videoPath
     * @return
     */
    public static int getResolution(String videoPath) throws IOException {
        FFprobe ffprobe = new FFprobe("ffprobe");
        FFmpegProbeResult probeResult = ffprobe.probe(videoPath);
        FFmpegStream stream = probeResult.getStreams().get(0);
        return stream.height;
    }
}
