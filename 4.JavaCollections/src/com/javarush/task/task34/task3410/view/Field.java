package com.javarush.task.task34.task3410.view;



import com.javarush.task.task34.task3410.controller.EventListener;
import com.javarush.task.task34.task3410.model.Direction;
import com.javarush.task.task34.task3410.model.GameObject;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * Created by kalinnikov_al on 15.06.2017.
 */
public class Field extends JPanel {
    private View view;
    EventListener eventListener;

    public Field(View view) {

        this.view = view;
        KeyHandler keyH = new KeyHandler();
        addKeyListener(keyH);
        setFocusable(true);
    }

    @Override
    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0,0, this.getWidth(), this.getHeight());
        for (GameObject gameObject: view.getGameObjects().getAll()) {
            gameObject.draw(g);
        }
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public class KeyHandler extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
           switch (e.getKeyCode()){
               case KeyEvent.VK_LEFT:
                   eventListener.move(Direction.LEFT);
                   break;
               case KeyEvent.VK_RIGHT:
                   eventListener.move(Direction.RIGHT);
                   break;
               case KeyEvent.VK_UP:
                   eventListener.move(Direction.UP);
                   break;
               case KeyEvent.VK_DOWN:
                   eventListener.move(Direction.DOWN);
                   break;
               case KeyEvent.VK_R:
                   eventListener.restart();
                   break;

           }
        }
    }
}
