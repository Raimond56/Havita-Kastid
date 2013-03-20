// siin on aluse andmed

import java.awt.*;

public class Paddle 
{
	
	// aluse muutujad
	int x = 0, y = 0, width=128, height=32;
	public boolean goingLeft = false;
	public boolean goingRight = false;
	public Paddle(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	// aluse asukoha m‰‰ramine koguaeg
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	// aluse liikumine
	public void move(int maxWidth)
	{
		if(goingLeft)
			x -= 6;
		if(goingRight)
			x += 6;
		if(x < 0)
			x = 0;
		if(x+120 > maxWidth)
			x = maxWidth-120;
	}
	
	// aluse joonistamine
	public void draw(Graphics g)
	{
		g.fillRect(x, y, width, height);
	}
}
