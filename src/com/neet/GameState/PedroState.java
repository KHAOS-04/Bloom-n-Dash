package com.neet.GameState;

import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import com.neet.Audio.JukeBox;
import com.neet.Handlers.Keys;
import com.neet.Main.GamePanel;

public class PedroState extends GameState {

    private ImageIcon pattingPedro; // looped scene

    public PedroState(GameStateManager gsm) {
        super(gsm);
        
        try {
            // Load animated GIFs
            pattingPedro = new ImageIcon(getClass().getResource("/Backgrounds/FinalPedzHUHU.gif"));
            JukeBox.load("/Backgrounds/PedroMusic.mp3", "PedroMusic");
            JukeBox.loop("PedroMusic");
            
            // Validate resources
            if (pattingPedro.getImageLoadStatus() != java.awt.MediaTracker.COMPLETE) {
                throw new RuntimeException("Failed to load animated GIFs.");
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        

    }

    public void init() {
        
    }

    public void update() {
        handleInput(); 
    }

    public void draw(Graphics2D g) {
        // Draw the appropriate animated image
        g.drawImage(pattingPedro.getImage(), 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
        
    }

    public void handleInput() {
        if (Keys.isPressed(Keys.ESCAPE)) {
            JukeBox.stop("PedroMusic");
            gsm.setState(GameStateManager.MENUSTATE);
        }


        
    }
     public void cleanup() {
        // Stop the music and clear resources when exiting the state
        //JukeBox.stop("PedroMusic");
        pattingPedro = null;
    }
}