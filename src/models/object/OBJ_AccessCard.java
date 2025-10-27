package models.object;

import models.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_AccessCard extends SuperObject {
    String[] dialogues = new String[10];
    private int dialogueIndex;
    GamePanel gp;

    public OBJ_AccessCard() {
        name = "accesscard";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/accessCard.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        collision = true;
    }

    public void setDialogue(OBJ_AccessCard objAccessCard, int i) {
        dialogues[0] = "Has encontrado una tarjeta de acceso!";
        dialogues[1] = "Ya podes pasar al siguiente nivel \n Acordate que no te puede atrapar la camara!";

    }

    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }


        GamePanel gp = null;
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
    }

    public void interact() {
        if (!gp.player.accessCard) {
            gp.player.accessCard = true; // Marcar que ya la tiene
            setDialogue(this, 0);
        } else {
            setDialogue(this, 1);
        }

    }
}
