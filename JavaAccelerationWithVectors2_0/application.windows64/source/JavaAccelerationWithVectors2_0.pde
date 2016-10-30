import g4p_controls.*;

import g4p_controls.*;

/**           
 * Acceleration with Vectors 
 * by Nathan Rowbottom Feb 20 2016.  
 * 
 * Demonstration of the basics of motion with vectors and tracers.
 * A "Mover" object was inspired from code by Daniel Shiffman but now has been heavily modified
 * stores location, velocity, and acceleration as vectors
 * Mover also has tracers contain
 * The motion is controlled by affecting the acceleration (in this case towards the mouse)
 * 
 */

//Controls list
//Play - pause button or checkbox
//Slider for friction
//Checkboxes for 
  //position vectors
  //velocity vector
  //displacement vector
  //acceleration
  //tracers
  //axis
  //grid

// A Mover object
Mover mover;
ArrayList<Mover> movers;
Axis axis;

//boolean controls
public boolean paused= false;
public boolean mouse_vector_vis = true;
public boolean location_vector_vis = true;
public boolean velocity_vector_vis = false;
public boolean acceleration_vector_vis = false;
public float damping = 0.002;
public boolean axis_vis = true;
public boolean grid_vis = true;
public boolean tracer_vis = true;
//public boolean mouse_vector_vis;
//public boolean mouse_vector_vis;
//public boolean mouse_vector_vis;
//gui g;

void setup() {
  size(1200,720);
  smooth();
  mover = new Mover();
  movers = new ArrayList<Mover>(); 
  axis = new Axis(40,40);
  //g = new gui();
  createGUI();  
}

void keyPressed(){
   if (key == ' '){
      paused = ! paused;
   } 
}

void mousePressed(){
 // if (mouseButton == LEFT){
 //    movers.add(new Mover());
 // }
 // if (mouseButton == RIGHT && movers.size()>0){
 //    movers.remove(0);
 // } 
   
}


void draw() {
  if (!paused){
    background(0);
    if (grid_vis){axis.drawGrid();}
    if (axis_vis){axis.drawAxis();}
    // Update the location
    mover.update();
    // Display the Mover
    mover.display();
    for (Mover m : movers){
       m.update();
       m.display(); 
    }
  }
  else{
    textSize(50);
    fill(200, 30);
    text("***PAUSED***", width/2.-170, height/2.); 
  } 
}