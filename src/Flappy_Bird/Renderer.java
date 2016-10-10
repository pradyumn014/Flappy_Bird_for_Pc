package Flappy_Bird;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Renderer extends JPanel
{
	private static final long serialVersionUID = 4226816340941728320L;

@Override
protected void paintComponent(Graphics g) {
	
	super.paintComponent(g);
	System.out.print("Renderer");
	FlappyBird.flappybird.repaint(g);
}

}
