package com.neet.GameState;

import com.neet.Audio.JukeBox;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.neet.Handlers.Keys;
import com.neet.Main.GamePanel;
import javax.swing.ImageIcon;

public class AcidState extends GameState {
	
	private float hue;
	private Color color;
	private double angle;
	private BufferedImage image;
        private boolean onAcidState; 
        private ImageIcon pattingPedro = null; // looped scene
	
	public AcidState(GameStateManager gsm) {
		super(gsm);
                
                
                onAcidState = true;
                //pattingPedro = new ImageIcon(getClass().getResource(null));
		try {
			image = ImageIO.read(
			getClass().getResourceAsStream(
			"/Sprites/Player/PlayerSprites.gif"
			)).getSubimage(0, 0, 40, 40);
		}
		catch(Exception e) {}
	}
        
        
        
        
        
	
	public void init() {
            JukeBox.load("/Backgrounds/PedroMusic.mp3", "PedroMusic");
            JukeBox.loop("PedroMusic");
//            pattingPedro = null;
            pattingPedro = new ImageIcon(getClass().getResource("/Backgrounds/FinalPedzHUHU.gif"));
        }
	
	public void update() {
            handleInput();
            
            // if on ACIDSTATE
           if(onAcidState == true){
               color = Color.getHSBColor(hue, 1f, 1f);
               hue += 0.01;
               if(hue > 1) hue = 0;
               angle += 0.1;
           }

           
           else { // acid FALSE
               try {
                // Load animated GIFs
                
                

                // Validate resources
//                if (pattingPedro.getImageLoadStatus() != java.awt.MediaTracker.COMPLETE) {
//                    throw new RuntimeException("Failed to load animated GIFs.");
//                }
        
           } catch (Exception e) {
               e.printStackTrace();
           }
               }
           }
	
	public void draw(Graphics2D g) {
            if(onAcidState == true) {
		g.setColor(color);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		AffineTransform at = new AffineTransform();
		at.translate(GamePanel.WIDTH / 2, GamePanel.HEIGHT / 2);
		at.rotate(angle);
		g.drawImage(image, at, null);
            }
            else { // acid FALSE
                g.drawImage(pattingPedro.getImage(), 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
            }
	}
	
	public void handleInput() {
            if(onAcidState == true && Keys.isPressed(Keys.ENTER)) {
                onAcidState = false;
                init();
                return;
            }
                
            if (onAcidState == false){
                if (Keys.isPressed(Keys.ESCAPE)) {
                    JukeBox.stop("PedroMusic");
                    gsm.setState(GameStateManager.MENUSTATE);
                    pattingPedro = null;
                    onAcidState = true;
                    return;
                }    
            }
       	}
        
        public void cleanup(){
            pattingPedro = null;
            onAcidState = true;
        }

}
