package Flappy_Bird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class FlappyBird implements ActionListener,MouseListener,KeyListener
{

	public static FlappyBird flappybird;
	int height,width;
	public Renderer renderer;
	int score;
	public Rectangle bird;
	ArrayList<Rectangle> columns;
	public Random rand;
	public int ticks,ymotion=2;
	public boolean gameover,started;
	public FlappyBird() 
	{
		height=800;
		System.out.println("Constructor");
		width=1200;
		score=0;
		renderer=new Renderer();
		JFrame jframe =new JFrame();
		jframe.add(renderer);
		jframe.setSize(width,height);
		rand=new Random();
		jframe.setVisible(true);
		jframe.setTitle("Flappy_Bird");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setResizable(true);
		jframe.addMouseListener(this);
		jframe.addKeyListener(this);
		columns=new ArrayList<Rectangle>();
		Timer timer=new Timer(20,this);
		bird=new Rectangle(20,20);
		bird.y=height/2-10;
		bird.x=width/2-10;
		addColumn(true);
		addColumn(true);
		addColumn(true);
		addColumn(true);
		gameover=false;
		timer.start();
		
	}
	public void addColumn(boolean start)
	{
		int space=300;
		int width=150;
		int height=50+rand.nextInt(300);
		if(start){
		columns.add(new Rectangle(this.width+width+columns.size()*250,this.height-height-170,width,height));
		columns.add(new Rectangle(this.width+width+(columns.size()-1)*250,0,width,this.height-height-space));
		}
		else
		{
			columns.add(new Rectangle(columns.get(columns.size()-1).x+400,this.height-height-170,width,height));
			columns.add(new Rectangle(columns.get(columns.size()-1).x,0,width,this.height-height-space));	
		}
	}
	public void repaint(Graphics g)
	{
		
		g.setColor(Color.cyan);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.red );
		g.fillRect(bird.x, bird.y, bird.width, bird.height);
		g.setColor(Color.orange);
		g.fillRect(0, height-150, width, 150);
		g.setColor(Color.green);
		g.fillRect(0, height-170, width,20);
		for(Rectangle column:columns)
		{
			paintColumn(g,column);
		}
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial",1,70));
		g.drawString(""+score/2, 550, height-80);
		g.setFont(new Font("Arial",1,100));
		
		if(!started)
		{
			g.drawString("Click to Start", 200, (height-170)/2);
		}
		if(gameover)
		{
			g.drawString("Game Over", 200, (height-170)/2);
		}
		
	}
	public void paintColumn(Graphics g,Rectangle column)
	{
		g.setColor(Color.green.darker());
		g.fillRect(column.x, column.y, column.width, column.height);
	}
	public static void main(String[] args) 
	{
			flappybird=new FlappyBird();
			
	}
	public void jump()
	{
		if(gameover)
		{
			//gameover=false;
			//started=false;
		}
		
		 //if(!gameover)
		{
			 if(!started)
				{
					started=true;
				}
			 //if(ymotion>0) ymotion=0;
			//else
			 if(ymotion>0) ymotion=-8;
			 else ymotion=ymotion-8;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		//System.out.println("Action Listener");
		ticks++;
		int speed=5;
		if(started)
			{for(int i=0;i<columns.size();i++)
		{
			Rectangle column=columns.get(i);
			column.x-=speed;
			if(column.x+column.width<=0)
			{
				columns.remove(column);
				if(column.y==0)
				{
				addColumn(false);
				}
				
			}
			if(column.x+column.width==bird.x)
				score+=1;
			
		}
			
		if(ticks%2==0&&ymotion<15)
		{
			ymotion+=1;
		}
		//if(!gameover)
			bird.y+=ymotion;
		for(Rectangle column:columns)
		{
			if(column.intersects(bird))
				{
				gameover=true;
				bird.x=column.x-bird.width;
				}
			
			
		}
		if(bird.y<0||bird.y>this.height-170)
		{
			
			gameover=true;
			
		}
		if(gameover)
		{
			bird.y=height-170-bird.height;
		}
		renderer.repaint();
		
	}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		jump();
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method s
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		jump();
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
