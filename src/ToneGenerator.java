import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class ToneGenerator {
    private static final int SAMPLE_RATE = 44100;
    private static final int SAMPLE_BITS = 8;
    private static final int CHANNELS = 1;
    private static final boolean SIGNED = true;
    private static final boolean BIG_ENDIAN = false;

    private final SourceDataLine line;

    private byte[] buffer;
    private Wavetable wavetable;

    public ToneGenerator() throws LineUnavailableException {
        AudioFormat audioFormat = new AudioFormat(SAMPLE_RATE, SAMPLE_BITS, CHANNELS, SIGNED, BIG_ENDIAN);
        line = AudioSystem.getSourceDataLine(audioFormat);
        line.open(audioFormat);
        line.start();

        buffer = new byte[1];
        wavetable = new Wavetable();
    }

    public void play() {

        float volume = 1f;
        int octave = 4;

        byte[][] tones = new byte[][] {
                wavetable.generateTriangle(Note.C.getFrequency(octave), volume),
                //wavetable.generateTriangle(Note.C$.getFrequency(octave), volume),
                //wavetable.generateSine(Note.D.getFrequency(octave), volume),
                //wavetable.generateSine(Note.D$.getFrequency(octave), volume),
                //wavetable.generateSine(Note.E.getFrequency(octave), volume),
                wavetable.generateTriangle(Note.F.getFrequency(octave), volume),
                //wavetable.generateSine(Note.F$.getFrequency(octave), volume),
                //wavetable.generateSine(Note.G.getFrequency(octave), volume),
                wavetable.generateTriangle(Note.G$.getFrequency(octave), volume),
                //wavetable.generateSine(Note.A.getFrequency(octave), volume),
                //wavetable.generateSine(Note.A$.getFrequency(octave), volume),
                //wavetable.generateSine(Note.B.getFrequency(octave), volume),
        };

        byte[][] tones2 = new byte[][] {
                wavetable.generateTriangle(Note.C.getFrequency(octave), volume),
                //wavetable.generateTriangle(Note.C$.getFrequency(octave), volume),
                //wavetable.generateSine(Note.D.getFrequency(octave), volume),
                //wavetable.generateSine(Note.D$.getFrequency(octave), volume),
                wavetable.generateTriangle(Note.E.getFrequency(octave), volume),
                //wavetable.generateTriangle(Note.F.getFrequency(octave), volume),
                //wavetable.generateSine(Note.F$.getFrequency(octave), volume),
                wavetable.generateTriangle(Note.G.getFrequency(octave), volume),
                //wavetable.generateTriangle(Note.G$.getFrequency(octave), volume),
                //wavetable.generateSine(Note.A.getFrequency(octave), volume),
                //wavetable.generateSine(Note.A$.getFrequency(octave), volume),
                //wavetable.generateSine(Note.B.getFrequency(octave), volume),
        };

        byte[][] pressedTones = new byte[12][];

        int i = 0;
        while (i < 100000) {
            for (byte[] tone : tones) {
                buffer[0] = (byte) ((buffer[0] + tone[i % tone.length]) / tones2.length);
            }
            line.write(buffer, 0, 1);
            i++;
        }

        i = 0;
        while (i < 100000) {
            for (byte[] tone : tones2) {
                buffer[0] = (byte) ((buffer[0] + tone[i % tone.length]) / tones2.length);
            }
            line.write(buffer, 0, 1);
            i++;
        }
    }
}
