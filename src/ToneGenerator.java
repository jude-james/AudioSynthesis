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
    private final byte[] buffer;

    public ToneGenerator() throws LineUnavailableException {
        AudioFormat audioFormat = new AudioFormat(SAMPLE_RATE, SAMPLE_BITS, CHANNELS, SIGNED, BIG_ENDIAN);
        line = AudioSystem.getSourceDataLine(audioFormat);
        line.open(audioFormat);
        line.start();

        buffer = new byte[1];
    }

    public void play() {

        /*
        float volume = 1f;
        int octave = 4;

        byte[][] tones = new byte[][] {
                //Wavetable.generateSine(Note.C.getFrequency(octave), volume),
                //Wavetable.generateSine(Note.C$.getFrequency(octave), volume),
                //Wavetable.generateSine(Note.D.getFrequency(octave), volume),
                //Wavetable.generateSine(Note.D$.getFrequency(octave), volume),
                //Wavetable.generateSine(Note.E.getFrequency(octave), volume),
                //Wavetable.generateSine(Note.F.getFrequency(octave), volume),
                //Wavetable.generateSine(Note.F$.getFrequency(octave), volume),
                //Wavetable.generateSine(Note.G.getFrequency(octave), volume),
                //Wavetable.generateSine(Note.G$.getFrequency(octave), volume),
                //Wavetable.generateSine(Note.A.getFrequency(octave), volume),
                //Wavetable.generateSine(Note.A$.getFrequency(octave), volume),
                //Wavetable.generateSine(Note.B.getFrequency(octave), volume),
        };

        int i = 0;
        while (true) {
            for (byte[] tone : tones) {
                if (tones.length == 1) {
                    buffer[0] = tone[i % tone.length];
                }
                else {
                    buffer[0] = (byte) ((buffer[0] + tone[i % tone.length]) / tones.length);
                }
            }
            line.write(buffer, 0, 1);
            i++;
        }
         */

        int i = 0;
        while (true) {
            for (Tone t : GUI.tones) {
                if (t.isPlaying) {
                    if (Tone.getCurrentlyPlaying() == 1) {
                        buffer[0] = t.tone[i % t.tone.length];
                    }
                    else {
                        buffer[0] = (byte) ((buffer[0] + t.tone[i % t.tone.length]) / Tone.getCurrentlyPlaying());
                    }
                }
            }
            line.write(buffer, 0, 1);
            i++;
        }
    }
}

