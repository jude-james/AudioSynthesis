import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUI extends JFrame implements KeyListener {

    public static Tone[] tones;
    private ToneGenerator toneGenerator;

    private int octave = 3;
    private float volume = 1f;

    GUI() throws LineUnavailableException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLayout(null);
        this.addKeyListener(this);
        this.setVisible(true);

        this.setTitle("Simplest Synth");
        this.setBackground(Color.blue);

        tones = new Tone[] {
                new Tone(Note.C.getFrequency(octave), volume),
                new Tone(Note.C$.getFrequency(octave), volume),
                new Tone(Note.D.getFrequency(octave), volume),
                new Tone(Note.D$.getFrequency(octave), volume),
                new Tone(Note.E.getFrequency(octave), volume),
                new Tone(Note.F.getFrequency(octave), volume),
                new Tone(Note.F$.getFrequency(octave), volume),
                new Tone(Note.G.getFrequency(octave), volume),
                new Tone(Note.G$.getFrequency(octave), volume),
                new Tone(Note.A.getFrequency(octave), volume),
                new Tone(Note.A$.getFrequency(octave), volume),
                new Tone(Note.B.getFrequency(octave), volume),
        };

        toneGenerator = new ToneGenerator();
        toneGenerator.play();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'a' -> {
                tones[0].startPlaying();
            }
            case 'w' -> {
                tones[1].startPlaying();
            }
            case 's' -> {
                tones[2].startPlaying();
            }
            case 'e' -> {
                tones[3].startPlaying();
            }
            case 'd' -> {
                tones[4].startPlaying();
            }
            case 'f' -> {
                tones[5].startPlaying();
            }
            case 't' -> {
                tones[6].startPlaying();
            }
            case 'g' -> {
                tones[7].startPlaying();
            }
            case 'y' -> {
                tones[8].startPlaying();
            }
            case 'h' -> {
                tones[9].startPlaying();
            }
            case 'u' -> {
                tones[10].startPlaying();
            }
            case 'j' -> {
                tones[11].startPlaying();
            }
            case 'k' -> {
                // play a C (+1 oct)
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'a' -> {
                tones[0].stopPlaying();
            }
            case 'w' -> {
                tones[1].stopPlaying();
            }
            case 's' -> {
                tones[2].stopPlaying();
            }
            case 'e' -> {
                tones[3].stopPlaying();
            }
            case 'd' -> {
                tones[4].stopPlaying();
            }
            case 'f' -> {
                tones[5].stopPlaying();
            }
            case 't' -> {
                tones[6].stopPlaying();
            }
            case 'g' -> {
                tones[7].stopPlaying();
            }
            case 'y' -> {
                tones[8].stopPlaying();
            }
            case 'h' -> {
                tones[9].stopPlaying();
            }
            case 'u' -> {
                tones[10].stopPlaying();
            }
            case 'j' -> {
                tones[11].stopPlaying();
            }
            case 'k' -> {
                // play a C (+1 oct)
            }
        }
    }
}

