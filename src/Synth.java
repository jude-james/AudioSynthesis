import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import java.util.Random;

public class Synth {
    public static final int SAMPLE_RATE = 16 * 1024; // 16KHz
    private static final int SAMPLE_BITS = 8;
    private static final int CHANNELS = 1;
    private static final boolean SIGNED = true;
    private static final boolean BIG_ENDIAN = false;

    private final SourceDataLine line;

    public Synth() throws LineUnavailableException {
        AudioFormat audioFormat = new AudioFormat(SAMPLE_RATE, SAMPLE_BITS, CHANNELS, SIGNED, BIG_ENDIAN);
        line = AudioSystem.getSourceDataLine(audioFormat);
        line.open();
        line.start();
    }

    public enum Waveform {
        Sine, Square, Saw, Triangle, Noise
    }

    public void play(Note note, int octave, int duration, float volume, Waveform waveform) {
        byte[] buffer = switch (waveform) {
            case Sine -> generateSineWaveBuffer(note.getFrequency(octave), duration, volume);
            case Square -> generateSquareWaveBuffer(note.getFrequency(octave), duration, volume);
            case Saw -> generateSawWaveBuffer(note.getFrequency(octave), duration, volume);
            case Triangle -> generateTriangleWaveBuffer(note.getFrequency(octave), duration, volume);
            case Noise -> generateNoiseWaveBuffer(note.getFrequency(octave), duration, volume);
        };
        line.write(buffer, 0, buffer.length);
    }

    public void closeLine() {
        line.drain();
        line.stop();
        line.close();
    }

    public byte[] generateSineWaveBuffer(double frequency, int ms, float volume) {
        int sampleCount = (ms * SAMPLE_RATE) / 1000;
        byte[] output = new byte[sampleCount];

        double period = (double) SAMPLE_RATE / frequency;
        for (int i = 0; i < output.length; i++) {
            double angle = 2.0 * Math.PI * i / period;
            output[i] = (byte) ((Math.sin(angle) * volume) * 127f);
        }

        return output;
    }

    public byte[] generateSquareWaveBuffer(double frequency, int ms, float volume) {
        int sampleCount = (ms * SAMPLE_RATE) / 1000;
        byte[] output = new byte[sampleCount];

        double period = (double) SAMPLE_RATE / frequency;
        for (int i = 0; i < output.length; i++) {
            double angle = 2.0 * Math.PI * i / period;
            output[i] = (byte) ((Math.signum(Math.sin(angle)) * volume) * 127f);
        }

        return output;
    }

    public byte[] generateSawWaveBuffer(double frequency, int ms, float volume) {
        int sampleCount = (ms * SAMPLE_RATE) / 1000;
        byte[] output = new byte[sampleCount];

        double period = (double) SAMPLE_RATE / frequency;
        for (int i = 0; i < output.length; i++) {
            double x = 2 * ((i % period) * (1 / period)) - 1;
            output[i] = (byte) ((x * volume) * 127f);
        }

        return output;
    }

    public byte[] generateTriangleWaveBuffer(double frequency, int ms, float volume) {
        int sampleCount = (ms * SAMPLE_RATE) / 1000;
        byte[] output = new byte[sampleCount];

        double period = (double) SAMPLE_RATE / frequency;
        for (int i = 0; i < output.length; i++) {
            double x = (4 * Math.abs((i / period) - Math.floor((i / period) + 0.5d))) - 1;
            output[i] = (byte) ((x * volume) * 127f);
        }

        return output;
    }

    public byte[] generateNoiseWaveBuffer(double frequency, int ms, float volume) {
        int sampleCount = (ms * SAMPLE_RATE) / 1000;
        byte[] output = new byte[sampleCount];

        Random ran = new Random();
        for (int i = 0; i < output.length; i++) {
            double x = 2 * ran.nextDouble() - 1;
            output[i] = (byte) ((x * volume) * 127f);
        }

        return output;
    }
}

