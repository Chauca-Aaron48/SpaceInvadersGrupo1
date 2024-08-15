package presentacion;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class ReproductorMúsica {

    private Clip clip;

    public ReproductorMúsica(String dirección) {
        try {
            File archivoDeAudio = new File(dirección);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivoDeAudio);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void reproducir() {
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void bucle() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void detener() {
        if (clip != null) {
            clip.stop();
        }
    }

}
