// impordib vajalikud java vahendid

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

// Main class, kust mäng käima läheb
public class Main extends Applet implements Runnable, KeyListener
{
	
	private Image dbImage; // topelt puhverduse jaoks vajalik muutuja
	private Graphics dbGraphics; // topelt puhverduse jaoks vajalik muutuja, et pilt ei vilguks

	//main klassi privaatsed muutujad
	private Thread th = new Thread(this);
	private boolean running = false;

	private int psp = 234; //aluse algus positisioon x 
	private int bsp = 382; //palli algus positsioon y 
	private int score = 0;
	private int blocksKilled = 0;
	private int totalBlocks = 0;
	
	// palli, aluse ja blokkide lisamine nende enda klassist
	private Ball ball;
	private Paddle paddle;
	private ArrayList<Block> blocks = new ArrayList<Block>();
	
	//blokkide asetuse tegemine
	private String level[] = {"............",
							  "............",
							  ".b.b.b.b.b.b",
							  "b.b.b.b.b.b.",
							  ".b.b.b.b.b.b",
							  "b.b.b.b.b.b.",
							  ".b.b.b.b.b.b",};
	
	// palli, aluse ja blokkide genereerimine
	public void init()
	{
		ball = new Ball(getSize().width/2,bsp,6,Color.white);
		paddle = new Paddle(psp,getSize().height-12);

		for(int y = 0; y < level.length; y++)
		{
			for(int x = 0; x < level[y].length(); x++)
			{
				if(level[y].charAt(x) == 'b')
				blocks.add(new Block(x*50,y*15));
				totalBlocks++;
			}
		}
		
		addKeyListener(this);  // vajalik et alust nuppudega liigutada
	}
	
	// algus
	public void start()
	{
		running = true;
		th.start();
	}
	
	// peatus
	public void stop()
	{
		running = false;
	}
	
	// palli, aluse liikumine ning palli põrkumine aluse ja blokkidega
	public void run()
	{
		while(running == true)
		{
			ball.move(getSize(),paddle);
			paddle.move(getSize().width);
			
			for (int i= 0; i < blocks.size(); i++)
			{
				ball.checkCollision(blocks.get(i));
				if(!blocks.get(i).alive)
				{
					blocks.remove(i);
					score++;
					blocksKilled++;
				}
			}
			
			repaint();
			try
			{
				Thread.sleep(20);
			}
			catch(Exception e){	}
		}
	}
	
	// topelt bufferdus vilkumise vähendamiseks 
	public void update(Graphics g)
	{
		if(dbImage == null)
		{
			dbImage = createImage(this.getSize().width, this.getSize().height);
			dbGraphics = dbImage.getGraphics();
		}
		
		dbGraphics.setColor(this.getBackground());
		dbGraphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
		
		dbGraphics.setColor(this.getForeground());
		paint(dbGraphics);
		
		g.drawImage(dbImage,0,0,this);
		
	}
	
	// palli, aluse ning blokkide joonistamine
	public void paint(Graphics g)
	{
		g.fillRect(0,0,getSize().width, getSize().height);
		ball.draw(g);
		paddle.draw(g);
		for(Block b : blocks)
		{
			b.draw(g);
		}
		Font font = new Font ("Arial", Font.PLAIN, 20);
		int blocksLeft = totalBlocks-blocksKilled;
		g.setColor(Color.white);
		g.setFont(font);
		g.drawString("Skoor: "+score,20,20);
		g.drawString("Kaste alles: "+ blocksLeft,460,20);
	}
	
	// aluse liikumine vasaku ja parema nooleklahviga
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			paddle.goingLeft = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			paddle.goingRight = true;
		}
	}
	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			paddle.goingLeft = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			paddle.goingRight = false;
		}
	}
	public void keyTyped(KeyEvent e){}
}