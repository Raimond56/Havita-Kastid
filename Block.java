// blokkide klass

import java.awt.*;

public class Block 
{
	
	//blokkide muutujad
	int x, y, width=50, height=15;
	public boolean alive = true;
	
	public Block(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	//bloki joonistamine
	public void draw(Graphics g)
	{
		g.setColor(Color.orange);
		g.fillRect(x,y,width,height);
		g.setColor(Color.white);
		g.drawRect(x,y,width,height);
		g.drawRect(x,y,width-1,height-1);
	}
}
