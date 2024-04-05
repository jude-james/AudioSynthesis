import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Main {
    public static void main(String[] args) throws LineUnavailableException {
        final AudioFormat audioFormat = new AudioFormat(Synth.SAMPLE_RATE, 8, 1, true, true);
        SourceDataLine line = AudioSystem.getSourceDataLine(audioFormat);
        line.open(audioFormat, Synth.SAMPLE_RATE);
        line.start();

        // sin wave values from -127 to 127 based on freq
        byte[] toneBufferTest = Synth.createSinWaveBuffer(100, 1);
        for (byte b : toneBufferTest) {
            System.out.println(b);
        }

        // getting frequency of A4
        Notes note = Notes.A;
        System.out.println(note.getFrequency(4) + "Hz");

        // square wave pitching up
        for (double freq = 100; freq <= 300; freq++) {
            byte[] toneBuffer = Synth.createSquareWaveBuffer(freq, 1);
            // line.write(toneBuffer, 0, toneBuffer.length);
        }

        // playing a C#3 for 2 seconds
        note = Notes.C$;
        byte[] toneBuffer = Synth.createSinWaveBuffer(note.getFrequency(3), 2000);
        int out = line.write(toneBuffer, 0, toneBuffer.length);
        System.out.println(out);
        System.out.println(toneBuffer.length);

        line.drain();
        line.close();
    }
}

