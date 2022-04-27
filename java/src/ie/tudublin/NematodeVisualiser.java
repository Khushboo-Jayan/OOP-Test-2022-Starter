package ie.tudublin;

import java.util.ArrayList;

import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PFont;
import processing.data.Table;
import processing.data.TableRow;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

public class NematodeVisualiser extends PApplet
{
	ArrayList<Nematode> nematodes = new ArrayList<>();

	float border = 10;
	float borderRight = 800 - border*3.0f;
	float borderLeft = border*3.0f;
	Nematode currentNematode = null;
	float circleRadius = 40;
	float cx,cy;
	int col;

	//for audio
	Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    int mode = 0;
	int count =0;

	//changes the 
	public void keyPressed()
	{		
		if (keyCode == LEFT)
		{
			background(0);
            count = --count % nematodes.size();
            // currentNematode = nematodes.get(count);
            stroke(map(count, 0, nematodes.size(), 0, 255), 255, 255);
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
        textSize(45);
		textAlign(CENTER);
		text("CA 1 NEMATODES", cx, border*10.0f);

		//load an audio file
		minim = new Minim(this);
        
        ap = minim.loadFile("song.mp3", 1024);
        ap.play();		
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
		col = (int) random(255); 
		drawArrow();
		drawNematodes();
		printDetails();
	}

	public void drawArrow(){
		stroke(col,255,255);
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
		delay(500);
	}

	//Draws a nematode on the screen 
	public void drawNematodes(){
		stroke(col,255,255);
        strokeWeight(2);
        noFill();    
        currentNematode = nematodes.get(count);
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
            }//end if statement
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
            }//end else
        }//end of for loop
		String gender = currentNematode.getGender();
        switch(gender)
        {
            case "m":
            {
                int y = (int) (cy + (circleRadius/2) * currentNematode.getLength());
                line(cx, y, cx, y + circleRadius/2);
                y = (int) (y + 5 + circleRadius/2);
                circle(cx, y, 10);
                break;
            }
            case "f":
            {
                int y = (int) (cy + (circleRadius/2) * (currentNematode.getLength() - 1));
                circle(cx, y, circleRadius/2);
                break;
            }
            case "h":
            {
                int y = (int) (cy + (circleRadius/2) * currentNematode.getLength());
                line(cx, y, cx, y + circleRadius/2);
                y = (int) (y + 5 + circleRadius/2);
                circle(cx, y, 10);
                y = (int) (cy + (circleRadius/2) * (currentNematode.getLength() - 1));
                circle(cx, y, circleRadius/2);
            }
        }
		delay(500);
	}

	public void printDetails(){
		textAlign(CORNER);
		textSize(20);
		String info = currentNematode.toString();
		text(info, border*3.0f, height-border*3.0f);
	}
}
