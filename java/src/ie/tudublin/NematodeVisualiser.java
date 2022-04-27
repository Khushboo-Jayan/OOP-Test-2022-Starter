package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class NematodeVisualiser extends PApplet
{
	ArrayList<Nematode> nematodes = new ArrayList<>();

	float border = 10;
	float borderRight = 800 - border*3.0f;
	float borderLeft = border*3.0f;

	//changes the 
	public void keyPressed()
	{		
		if (keyCode == LEFT)
		{
		}
		
		if (keyCode == RIGHT)
		{
		}
	}

	public void loadNematodes()
    {
        Table table = loadTable("HabHYG15ly.csv", "header");
        for(TableRow r:table.rows())
        {
            Nematode s = new Nematode(r);
            nematodes.add(s);
        }
    }//end loading csv file function


	public void settings()
	{
		size(800, 800);
	}

	public void setup() 
	{
		colorMode(HSB);
		background(0);
		smooth();	
		// loadNematodes();			
	}
	

	public void draw()
	{	
		drawArrow();
	}

	public void drawArrow(){
		stroke(random(255),255,255);
		//arrows
		//right arrow
		line(borderRight, height/2, borderRight-90, height/2);
		line(borderRight, height/2, borderRight-30, height/2 - 20);
		line(borderRight, height/2, borderRight-30, height/2 - 20);
		line(borderRight, height/2, borderRight-30, height/2 + 20);
		
		//arrow left
		line(borderLeft, height/2, borderLeft+90, height/2);
		line(borderLeft, height/2, borderLeft+30, height/2 - 20);
		line(borderLeft, height/2, borderLeft+30, height/2 + 20);
	}
}
