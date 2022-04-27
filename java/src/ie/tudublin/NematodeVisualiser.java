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
	Nematode currentNematode = null;
	float circleRadius = 40;
	float cx,cy;

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
        Table table = loadTable("nematodes.csv", "header");
        for(TableRow r:table.rows())
        {
            Nematode s = new Nematode(r);
            nematodes.add(s);
        }
    }//end loading csv file function


	//prints the data in nematodes.csv file 
	public void printNematodes()
    {
        for(Nematode s:nematodes)
        {
            System.out.println(s);
        }
    }//end printNematodes


	public void settings()
	{
		size(800, 800);
	}

	public void setup() 
	{
		colorMode(HSB);
		background(0);
		smooth();	
		loadNematodes();
		printNematodes();
		cx = width/2;
		cy = height/2;			
	}

	// public void drawNematodes()
    // {
    //     for(Nematode s:nematodes)
    //     {
    //         s.render(this);
    //     }
    // }
	

	public void draw()
	{	
		drawArrow();
		drawNematodes();
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

	//Draws a nematode on the screen 
	public void drawNematodes(){
		stroke(255);
        strokeWeight(2);
        noFill();    
        currentNematode = nematodes.get(0);
        for(int i = 0; i < currentNematode.getLength(); i++)
        {
            int offset = (int) (circleRadius * i);

            if(currentNematode.getLength() % 2 == 1)
            {
                circle(cx, cy - offset, circleRadius);
                circle(cx, cy + offset, circleRadius);

                // Limbs
                // Top segment limbs
                line(cx + circleRadius/2, cy - offset, cx + circleRadius, cy - offset);
                line(cx - circleRadius/2, cy - offset, cx - circleRadius, cy - offset);
                // Bottom segment limbs
                line(cx + circleRadius/2, cy + offset, cx + circleRadius, cy + offset);
                line(cx - circleRadius/2, cy + offset, cx - circleRadius, cy + offset);
            }
            else
            {
                circle(cx, cy + circleRadius/2 - offset, circleRadius);
                circle(cx, cy - circleRadius/2 + offset, circleRadius);
                // Top limbs
                line(cx + circleRadius, cy + circleRadius/2 - offset, cx + circleRadius, cy + circleRadius/2 - offset);
                line(cx - circleRadius, cy + circleRadius/2 - offset, cx - circleRadius, cy + circleRadius/2 - offset);
                // Bottom limbs
                line(cx + circleRadius, cy + circleRadius/2 - offset, cx + circleRadius, cy + circleRadius/2 - offset);
                line(cx - circleRadius, cy + circleRadius/2 - offset, cx - circleRadius, cy + circleRadius/2 - offset);
            }
        }
	}
}
