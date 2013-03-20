// siin on palli andmed

import java.awt.*;
import java.awt.geom.Rectangle2D;

// palli klass
public class Ball 
{
	
	//palli muutujad
	private double x = 0, y = 0, xspeed = 2, yspeed = 2, radius = 15;
	private Color color = Color.blue;
	public Ball(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Ball(int x, int y, int radius, Color color)
	{
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.color = color;

	}
	
	// palli põrkumise tuvastamine blokkide ja alusega
	public void checkCollision(Block b)
	{
		Rectangle2D.Double self = new Rectangle2D.Double(x-radius,y-radius,radius*2,radius*2);
		Rectangle2D.Double block = new Rectangle2D.Double(b.x,b.y,b.width,b.height);
		if(self.intersects(block))
		{
			b.alive = false;
			
			if(x >= b.x && x <= b.x + b.width )
			{
				yspeed = -yspeed;
			}
			else if(y >= b.y && y <= b.y + b.height)
			{
				xspeed = -xspeed;
			}
		}
	}
	
	// palli liikumine
	public void move(Dimension dim, Paddle paddle)
	{
		x += xspeed;
		y += yspeed;
		
		if(x < 0)
			xspeed = Math.abs(xspeed);
		if (x > dim.width)
			xspeed = -Math.abs(xspeed);
		
		if(y < 0)
			yspeed = Math.abs(yspeed);
		
		Rectangle2D.Double paddleBounds = new Rectangle2D.Double(paddle.getX(),paddle.getY(),paddle.getWidth(),paddle.getHeight());
		Rectangle2D.Double selfBounds = new Rectangle2D.Double(x-radius,y-radius,radius*2,radius*2);
		if(paddleBounds.intersects(selfBounds))
		{
			yspeed = -Math.abs(yspeed);
			yspeed -= .2;

			double dist = x-(paddle.getX()+(paddle.getWidth()/2));
			xspeed = dist/10;
		}
		
		if (y > dim.height+200)
		{
			y = 0;
		}
	}
	
	// palli joonistamine
	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillOval((int)(x-radius), (int)(y-radius), (int)(radius*2), (int)(radius*2));
	}

}
