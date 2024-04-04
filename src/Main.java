import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Main {
    public static void main(String[] args) {
        try {
            final AudioFormat audioFormat = new AudioFormat(Synth.SAMPLE_RATE, 8, 1, true, true);
            SourceDataLine line = AudioSystem.getSourceDataLine(audioFormat);
            line.open(audioFormat, Synth.SAMPLE_RATE);
            line.start();

            byte[] toneBufferTest = Synth.createSinWaveBuffer(500);

            for (byte b : toneBufferTest) {
                // System.out.println(b);
            }

            for (double freq = 220; freq <= 400; freq++) {
                byte[] toneBuffer = Synth.createSquareWaveBuffer(freq);
                // line.write(toneBuffer, 0, toneBuffer.length);
            }

            for (double blahblah = 1; blahblah < 10;) {
                Note testNote = Note.G4$;
                line.write(testNote.tone(), 0, testNote.tone().length);
            }

            line.drain();
            line.close();

        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // goal: play(note, duration)
    /*
        void play {
            line.write(note, 0, note.tone().length)
            // use duration parameter to stop writing somehow
            // something like this
        }
     */
}

