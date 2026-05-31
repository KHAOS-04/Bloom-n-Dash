package com.neet.GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;


import com.neet.Audio.JukeBox;
import com.neet.Entity.PlayerSave;
import com.neet.Handlers.Keys;
import com.neet.Main.GamePanel;

public class MenuState extends GameState {

    private ImageIcon background; 
    private BufferedImage head;

    private int currentChoice = 0;
    private String[] options = {
        "Start",
        "Credits",
        "Quit"
    };

    private Font font;
    private Font font2;

    public MenuState(GameStateManager gsm) {
        super(gsm);

        try {
            // Load animated background (GIF)
            background = new ImageIcon(getClass().getResource("/Backgrounds/MainMenu1.gif"));

            // Load static floating head
            head = ImageIO.read(getClass().getResourceAsStream("/HUD/Hud.gif"))
                    .getSubimage(0, 16, 15, 16);

            // Fonts
            font = new Font("Impact", Font.PLAIN, 16);
            font2 = new Font("Arial", Font.PLAIN, 10);

            // Load sound effects
            JukeBox.load("/SFX/menuoption.mp3", "menuoption");
            JukeBox.load("/SFX/menuselect.mp3", "menuselect");
            JukeBox.load("/SFX/Creds.mp3", "Creds");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() {}

    public void update() {
        // Check keys
        handleInput();
    }

    public void draw(Graphics2D g) {
        // Draw animated background
        g.drawImage(background.getImage(), 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);

        // Draw menu options
        g.setFont(font);
        FontMetrics metrics = g.getFontMetrics(font);
        g.setColor(Color.WHITE);

        int optionSpacing = 24; // space between options
        int totalOptionsHeight = optionSpacing * options.length;
        int startY = GamePanel.HEIGHT - totalOptionsHeight - 8; // bottom padding

        for (int i = 0; i < options.length; i++) {
            String option = options[i];
            int textWidth = metrics.stringWidth(option);
            int x = (GamePanel.WIDTH - textWidth) / 2;
            int y = startY + i * optionSpacing;
            g.drawString(option, x, y);

            // Draw the floating head next to the current option
            if (i == currentChoice) {
                g.drawImage(head, x - 20, y - 12, null);
            }
        }


        // Other
        g.setFont(font2);
        g.setColor(Color.WHITE);
        g.drawString("BSCS 2B", 8, 248);
    }

    private void select() {
        if (currentChoice == 0) {
            JukeBox.play("menuselect");
            PlayerSave.init();
            gsm.setState(GameStateManager.LEVEL1ASTATE);
              
        } else if (currentChoice == 1) {
            JukeBox.play("Creds");
//            gsm.setState(GameStateManager.ACIDSTATE); // to test the pedro scene
//            gsm.setState(GameStateManager.PEDROSTATE); // to test the pedro scene
          gsm.setState(GameStateManager.CREDITSSTATE); // Navigate to the Credits state
        } else if (currentChoice == 2) {
            JukeBox.play("Creds");
            System.exit(0);
        }
    }

    public void handleInput() {
        if (Keys.isPressed(Keys.ENTER)) select();
        if (Keys.isPressed(Keys.UP)) {
            if (currentChoice > 0) {
                JukeBox.play("menuoption", 0);
                currentChoice--;
            }
        }
        if (Keys.isPressed(Keys.DOWN)) {
            if (currentChoice < options.length - 1) {
                JukeBox.play("menuoption", 0);
                currentChoice++;
            }
        }
    }
}