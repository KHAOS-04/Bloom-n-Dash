package com.neet.GameState;

import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import com.neet.Audio.JukeBox;
import com.neet.Handlers.Keys;
import com.neet.Main.GamePanel;

public class CreditsState extends GameState {

    private ImageIcon creditsImage;       // Main animated credits image
    private ImageIcon extraCreditsImage; // Extra animated credits image
    private boolean showingExtraCredits; // Tracks whether to show the extra credits image

    public CreditsState(GameStateManager gsm) {
        super(gsm);
        JukeBox.load("/SFX/Creds.mp3", "Creds");
        JukeBox.load("/Music/Credits.mp3", "Credits");
        

        try {
            // Load animated GIFs
            creditsImage = new ImageIcon(getClass().getResource("/Backgrounds/Credits.gif"));
            extraCreditsImage = new ImageIcon(getClass().getResource("/Backgrounds/ExtraCredits.png"));

            // Validate resources
            if (creditsImage.getImageLoadStatus() != java.awt.MediaTracker.COMPLETE || 
                extraCreditsImage.getImageLoadStatus() != java.awt.MediaTracker.COMPLETE) {
                throw new RuntimeException("Failed to load animated GIFs.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        showingExtraCredits = false; // Initially show the main credits
    }

    public void init() {}

    public void update() {
        handleInput();
    }

    public void draw(Graphics2D g) {
        // Draw the appropriate animated image
        if (showingExtraCredits) {
            g.drawImage(extraCreditsImage.getImage(), 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
        } else {
            g.drawImage(creditsImage.getImage(), 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
        }
    }

    public void handleInput() {
        if (Keys.isPressed(Keys.ESCAPE)) {
            JukeBox.stop("Credits"); // Stop the music when exiting
            JukeBox.play("Creds");
            gsm.setState(GameStateManager.MENUSTATE);
        }

        if (Keys.isPressed(Keys.ENTER)) {
                JukeBox.play("Creds");
            showingExtraCredits = !showingExtraCredits; // Toggle between images
        }
    }
     public void cleanup() {
        // Stop the music and clear resources when exiting the state
        JukeBox.stop("Credits");
        creditsImage = null;
        extraCreditsImage = null;
    }
}