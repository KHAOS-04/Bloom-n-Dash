
package com.neet.GameState;

import com.neet.Audio.JukeBox;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;

import com.neet.Handlers.Keys;
import com.neet.Main.GamePanel;

public class PauseState extends GameState {

    private ImageIcon pauseGif; // Animated GIF as an ImageIcon

    public PauseState(GameStateManager gsm) {
        super(gsm);
        JukeBox.load("/SFX/Creds.mp3", "Creds");

        try {
            pauseGif = new ImageIcon(getClass().getResource("/Backgrounds/PauseScreen.gif"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() {}

    public void update() {
        handleInput();
    }

    public void draw(Graphics2D g) {
        // Draw the animated GIF
        if (pauseGif != null) {
            g.drawImage(pauseGif.getImage(), 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
        }
    }

    public void handleInput() {
        if (Keys.isPressed(Keys.ESCAPE)) {
            JukeBox.play("Creds");
            gsm.setPaused(false); // Unpause the game
        }
        if (Keys.isPressed(Keys.MENU)) {
            JukeBox.play("Creds");
            JukeBox.stop("level1");
            JukeBox.stop("level-Salamin");
            JukeBox.stop("level1boss");
            gsm.setPaused(false);
            gsm.setState(GameStateManager.MENUSTATE); // Return to the main menu
        }
    }
}