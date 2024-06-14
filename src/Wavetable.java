import java.util.Random;

public class Wavetable {
    private static final int SAMPLE_RATE = 44100;

    private byte[] createBuffer(double freq) {
        double wavelength = 1.0f / freq;
        int samples = (int) Math.round(wavelength * SAMPLE_RATE);
        return new byte[samples];
    }

    public byte[] generateSine(double frequency, float volume) {
        byte[] output = createBuffer(frequency);

        double period = (double) SAMPLE_RATE / frequency;
        for (int i = 0; i < output.length; i++) {
            double angle = 2.0 * Math.PI * i / period;
            output[i] = (byte) ((Math.sin(angle) * volume) * 127f);
        }

        return output;
    }

    public byte[] generateSquare(double frequency, float volume) {
        byte[] output = createBuffer(frequency);

        double period = (double) SAMPLE_RATE / frequency;
        for (int i = 0; i < output.length; i++) {
            double angle = 2.0 * Math.PI * i / period;
            output[i] = (byte) ((Math.signum(Math.sin(angle)) * volume) * 127f);
        }

        return output;
    }

    public byte[] generateSaw(double frequency, float volume) {
        byte[] output = createBuffer(frequency);

        double period = (double) SAMPLE_RATE / frequency;
        for (int i = 0; i < output.length; i++) {
            double x = 2 * ((i % period) * (1 / period)) - 1;
            output[i] = (byte) ((x * volume) * 127f);
        }

        return output;
    }

    public byte[] generateTriangle(double frequency, float volume) {
        byte[] output = createBuffer(frequency);

        double period = (double) SAMPLE_RATE / frequency;
        for (int i = 0; i < output.length; i++) {
            double x = (4 * Math.abs((i / period) - Math.floor((i / period) + 0.5d))) - 1;
            output[i] = (byte) ((x * volume) * 127f);
        }

        return output;
    }

    public byte[] generateNoise(double frequency, float volume) {
        byte[] output = new byte[999999]; // research how white noise samples are actually done

        Random ran = new Random();
        for (int i = 0; i < output.length; i++) {
            double x = 2 * ran.nextDouble() - 1;
            output[i] = (byte) ((x * volume) * 127f);
            System.out.println(output[i]);
        }

        return output;
    }
}
