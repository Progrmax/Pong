/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ponggame;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;


public class PongGame extends JPanel{
    
int xBall = 420;int yBall = 250; int border = 30; double xSpieler1 = 50; double ySpieler1 = 250; double xSpieler2 = 800; double ySpieler2 = 250; double ballgeschwindigkeit = 3.5;
boolean ball_in_y_richtung = true;boolean ball_in_x_richtung = true;boolean ballmitte = true; int i = 0; int Spieler1Tore = 0; int Spieler2Tore = 0; double bewegungderblöcke = 0.08;
int ballgescherhöhung = 0;    

public void moveBall(){
        
        if(ball_in_y_richtung == true){
            yBall+=ballgeschwindigkeit;
        }
        if(ball_in_y_richtung == false){
            yBall-=ballgeschwindigkeit;
        }
        if(ball_in_x_richtung == true){
            xBall+=ballgeschwindigkeit;
        }
        if(ball_in_x_richtung == false){
            xBall-=ballgeschwindigkeit;
        }
        if(yBall >= getHeight()-border){
            ball_in_y_richtung = false;
        }
        if(yBall <= 0 ){
            ball_in_y_richtung = true;
        }
        bewegungderblöcke = bewegungderblöcke * 0.9995;
        
        
        if(ballmitte == true){
            xBall = 420;
            yBall = 250;
            ySpieler1 = 250;
            ySpieler2 = 250;
            ballgeschwindigkeit = 0;
            ballgescherhöhung = 0;
            if (i == 0){
            System.out.println("Enter drücken zum Beginnen");
            i =1;
            }
        }
        if(ballmitte == false){
             if(ballgescherhöhung >= 5){
                
                ballgeschwindigkeit *= 1.0007;
                
            }
             else{
                 ballgeschwindigkeit = 3.5;
             }
        }    
        
       
                   

        
    }
    public void balltrifttor(){
        if(xBall <= 0){
            ballmitte = true;
            System.out.println("Spieler 2 hat ein Tor geschossen!");
            Spieler1Tore = Spieler1Tore +1;
            System.out.println("Der Spielstand ist: "+Spieler1Tore+" : "+Spieler2Tore);
            if(Spieler1Tore == 10){
                System.out.println("Spieler 1 gewonnen!");
                
            }
            i = 0;
            
        }
        if(xBall >= getWidth() -border){
            ballmitte = true;
            System.out.println("Spieler 1 hat ein Tor geschossen!");
            Spieler2Tore = Spieler2Tore +1;
            System.out.println("Der Spielstand ist: "+Spieler1Tore+" : "+Spieler2Tore);
            //bewegungderblöcke = bewegungderblöcke * 0.6;
            if(Spieler2Tore == 10){
                System.out.println("Spieler 2 gewonnen!");
                
            }
            i = 0;
        }   
            
        
    }
    public void balltrifftblock(){
        
        if(yBall-ySpieler2 <= 50&& yBall-ySpieler2 >=-50  && xBall-xSpieler2 <= 30 && xBall-xSpieler2 >-30){
            ball_in_x_richtung = false;
            ballgescherhöhung += 1;
            
        
        }
        if(yBall-ySpieler1 <=50 && yBall-ySpieler1 >= -50 && xBall-xSpieler1 <= 30 && xBall-xSpieler1 >-30){
            ball_in_x_richtung = true;
            ballgescherhöhung += 1;
           
            
        }
    }
    public void Blöcke(){
        if (ySpieler1 <= 0){
            ySpieler1 +=10;
        }
        if (ySpieler1 >= getHeight()-80){
            ySpieler1 -=10;
        }
        if (ySpieler2 <= 0){
            ySpieler2 +=10;
        }
        if (ySpieler2 >= getHeight()-80){
            ySpieler2 -=10;
        }
        
    }
    @Override
    public void paint(Graphics g){
        
        super.paint(g);
        
        g.fillOval(xBall,  yBall, 30, 30);
        g.fillRect((int) xSpieler1, (int) ySpieler1, 25, 85);
        g.fillRect((int) xSpieler2, (int) ySpieler2, 25, 85);
        
    }
    public void pfeiltasten(JFrame myframe){
        
        myframe.addKeyListener(new KeyAdapter() {
            @Override
           
            public void keyPressed(KeyEvent e) {
                
                switch(e.getKeyCode()) {                    
                    
                        
                    case VK_UP: ySpieler2 -= bewegungderblöcke;
                    break;
                    case VK_DOWN:ySpieler2 += bewegungderblöcke;
                    break;
                    case VK_W: ySpieler1 -= bewegungderblöcke;
                    break;
                    case VK_S: ySpieler1 += bewegungderblöcke;
                    break;
                    case VK_ENTER: ballmitte = false;
                    break;
                    default: 
                    break;
                } 
                
            }
            
        });
        
    }

   
    public static void main(String[] args) throws InterruptedException {
        JFrame myframe = new JFrame("Pong Game");
        myframe.setSize(900, 500);//768
        myframe.setVisible(true);
        
        myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        PongGame game = new PongGame();
        myframe.add(game);
        System.out.println("Willkommen zum Spiel. Spieler 1 ist links und steuert mit den Pfeiltasten. Spieler 2 ist rechts und steuert mit W und S. Viel Glück!");
        
        
    
    while(true){
        game.repaint(); 
        game.moveBall();
        game.pfeiltasten(myframe);
        game.Blöcke();
        game.balltrifftblock();
        game.balltrifttor();
        Thread.sleep(10);
        
             }

}
}
