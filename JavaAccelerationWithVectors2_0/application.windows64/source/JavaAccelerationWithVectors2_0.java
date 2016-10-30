import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import g4p_controls.*; 
import g4p_controls.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class JavaAccelerationWithVectors2_0 extends PApplet {





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
public float damping = 0.002f;
public boolean axis_vis = true;
public boolean grid_vis = true;
public boolean tracer_vis = true;
//public boolean mouse_vector_vis;
//public boolean mouse_vector_vis;
//public boolean mouse_vector_vis;
//gui g;

public void setup() {
  
  
  mover = new Mover();
  movers = new ArrayList<Mover>(); 
  axis = new Axis(40,40);
  //g = new gui();
  createGUI();  
}

public void keyPressed(){
   if (key == ' '){
      paused = ! paused;
   } 
}

public void mousePressed(){
 // if (mouseButton == LEFT){
 //    movers.add(new Mover());
 // }
 // if (mouseButton == RIGHT && movers.size()>0){
 //    movers.remove(0);
 // } 
   
}


public void draw() {
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
    text("***PAUSED***", width/2.f-170, height/2.f); 
  } 
}
class Axis {

  int xScl;
  int yScl;
  int xLines;
  int yLines;

  Axis(int x, int y) {
    setAxis(x, y);
  } 

  public void setAxis(int x, int y) {
    xScl = x;
    yScl = y;
    xLines = width/xScl;
    yLines = height/yScl;
  }  

  public void drawAxis() {
    //draw coord axis
    strokeWeight(1);
    stroke(150, 70);
    line(0, height/2, width, height/2);
    line(width/2, 0, width/2, height);
    strokeWeight(2);
    for (int i = 0; i <= width; i += xScl) {
      line (i, height/2 - 5, i, height/2 + 5);
    }
    for (int j = 0; j <= height; j += yScl) {
      line (width/2 - 5, j, width/2 + 5, j);
    }
  }

  public void drawGrid() {
    strokeWeight(0.5f);
    stroke(150, 70);    
    for (int i = 0; i <= width; i += xScl) {
      line (i, 0, i, height);
    }
    for (int j = 0; j <= height; j += yScl) {
      line (0, j, width, j);
    }
  }
}

class Mover {

  // The Mover tracks location, velocity, and acceleration 
  PVector center;//goes to the center of the screen
  PVector mouse;
  PVector location;
  PVector locArrow;//points from center to location vector
  PVector velocity;
  PVector velArrow;
  PVector displacement;
  PVector acceleration;
  PVector accArrow;
  PVector mouseArrow;
  // The Mover's maximum speed
  float topspeed;

  Tracer tracers;

  Mover() {
    // Start in the center
    center = new PVector(width/2, height/2);
    mouse = new PVector(mouseX, mouseY);
    location = new PVector(width/2, height/2);
    displacement = new PVector();
    velocity = new PVector(0, 0);
    acceleration = new PVector();
    topspeed = 5;
    tracers = new Tracer(location);
  }

  public void update() {

    // Compute a vector that points from location to mouse
    mouse = new PVector(mouseX, mouseY);
    mouseArrow = PVector.sub(mouse,center);
    mouse_X.setText((int)mouseArrow.x+"");
    mouse_Y.setText((int)mouseArrow.y*-1+"");//y is oppposite orientation
    mouse_mag.setText((int)mouseArrow.mag()+"");
    mouse_ang.setText((int)(mouseArrow.heading()*-180/PI)+"");//angle is the reverse orientation
    
    //mouseArrow = 
    displacement = PVector.sub(mouse, location);
    acceleration = PVector.sub(mouse, location);
    // Set magnitude of acceleration
    acceleration.div(1000.f);
    accArrow = new PVector(acceleration.x,acceleration.y);
    accArrow.mult(frameRate);
    acc_X.setText((int)(accArrow.x)+"");
    acc_Y.setText((int)(accArrow.y*-1)+"");//y is oppposite orientation
    acc_mag.setText((int)(accArrow.mag())+"");
    acc_ang.setText((int)(accArrow.heading()*-180/PI)+"");//angle is the reverse orientation


    // Velocity changes according to acceleration
    velocity.add(acceleration);
    velArrow = new PVector(velocity.x,velocity.y);
    velArrow.mult(frameRate);
    vel_X.setText((int)(velArrow.x)+"");
    vel_Y.setText((int)(velArrow.y*-1)+"");//y is oppposite orientation
    vel_mag.setText((int)(velArrow.mag())+"");
    vel_ang.setText((int)(velArrow.heading()*-180/PI)+"");//angle is the reverse orientation
    
    // Limit the velocity by topspeed
    //if off the screen, decrease the speed;
    //  if ((abs(width/2 -location.x)>width/2)&&(abs(height/2 -location.y)>height/2)){
    //  println(damping+"");
    velocity.mult(1.f-damping);
    // }
    // velocity.limit(topspeed);
    // Location changes by velocity
    location.add(velocity);
    locArrow = PVector.sub(location,center);
    loc_X.setText((int)locArrow.x+"");
    loc_Y.setText((int)locArrow.y*-1+"");//y is oppposite orientation
    loc_mag.setText((int)locArrow.mag()+"");
    loc_ang.setText((int)(locArrow.heading()*-180/PI)+"");//angle is the reverse orientation
  }

  public void makeArrowHead(PVector vec, float direction) {
    int arrowSize = 8;
    pushMatrix();
    translate(vec.x, vec.y);
    rotate(direction);
    triangle(0.f, 0.f, - 8.f, - 4.f, - 8, + 4.f);     
    popMatrix();
  }

  public void display() {
    //update and draw the tracers
    if (tracer_vis){tracers.update();}
    stroke(255);

    //draw the puck
    strokeWeight(2);
    fill(127, 127, 127, 70);
    ellipse(location.x, location.y, 50, 50);
    //Draw the position vectors
    strokeWeight(3);
    if (mouse_vector_vis){
      stroke(150, 150, 150);
      fill(255, 255, 0);    
 
      line(center.x, center.y, mouse.x, mouse.y); 
     // makeArrowHead(PVector.add(PVector.sub(mouse, center), center),-mouse.heading());
    }
    if (location_vector_vis)
    {
    stroke(255, 255, 0);
       line(center.x, center.y, location.x, location.y);
      stroke(0,255,0);
      line(center.x, center.y, location.x, center.y);//projection along the x axis
      line(location.x, center.y, location.x, location.y);//projection along the y axis
    //  makeArrowHead(PVector.add(PVector.sub(location, center), center), 0);
    }
    if (velocity_vector_vis)
    { 
      stroke(255, 0, 0);
      fill(255, 0, 0);
      PVector scaledVel = new PVector (location.x+6*velocity.x, location.y+6*velocity.y);
      line(location.x, location.y, scaledVel.x, scaledVel.y);//velocity vector scaled for visibility
      makeArrowHead(scaledVel, velocity.heading());
    }
    //stroke(0, 255, 0);
    // line(location.x, location.y, location.x+displacement.x, location.y+displacement.y);//displacement vector
    if (acceleration_vector_vis){
      stroke(0, 255, 255);
      PVector scaledAcc= new PVector(location.x+100*acceleration.x,location.y+100*acceleration.y);
      line(location.x, location.y, scaledAcc.x,scaledAcc.y );//acceleration vector
      makeArrowHead(scaledAcc, acceleration.heading());
    }
  }
}
class Tracer {

  ArrayList <PVector> tail;
  ArrayList <PVector> breadcrumbs;
  int maxLength = 75;//(int)frameRate;//gives a second worth of a tracer
  PVector loc; //the location of the thing wanting tracers
  boolean filled; 


  public Tracer(PVector vec) {
    tail = new ArrayList<PVector>();  
    breadcrumbs = new ArrayList<PVector>();
    loc = vec;
    filled = false;
  }

  public void update() {
    noStroke();

    //every thrid frame
    if (frameCount % 4 ==0) {
      breadcrumbs.add(new PVector(loc.x, loc.y));
      if (filled&&breadcrumbs.size()> maxLength){breadcrumbs.remove(0);}      
    } 
      //if the mover is on the screen
      //  if ((abs(width/2 -loc.x)<=width/2)&&(abs(height/2 -loc.y)<=height/2)){
      //add a new PVector to the queue based on its position
      tail.add(new PVector(loc.x, loc.y));
      //  }
      //if the queue is too big
      if (tail.size()>=maxLength)
      {
        filled = true;
      }
      

      
      if (filled) {
        //remove the oldest one
        tail.remove(0);
      }
    

    //for each Pvector in teh queue
    for (int i = 0; i < tail.size (); i++)
    {
      fill(255);
      if (i<breadcrumbs.size()){
        ellipse(breadcrumbs.get(i).x, breadcrumbs.get(i).y, 3, 3);
      }
      //set the fill as 100- its index
      fill(0, 0, 255, i);
      //draw a circle
      ellipse (tail.get(i).x, tail.get(i).y, i/(float)tail.size() * 48.f + 3, i/(float)tail.size()*48.f + 3);
    }
  }
}
/* =========================================================
 * ====                   WARNING                        ===
 * =========================================================
 * The code in this tab has been generated from the GUI form
 * designer and care should be taken when editing this file.
 * Only add/edit code inside the event handlers i.e. only
 * use lines between the matching comment tags. e.g.

 void myBtnEvents(GButton button) { //_CODE_:button1:12356:
     // It is safe to enter your event code here  
 } //_CODE_:button1:12356:
 
 * Do not rename this tab!
 * =========================================================
 */

public void mouse_clicked(GCheckbox source, GEvent event) { //_CODE_:MousePosition:895855:
//  println("MousePosition - GCheckbox >> GEvent." + event + " @ " + millis());
  mouse_vector_vis = !mouse_vector_vis;
} //_CODE_:MousePosition:895855:

public void location_clicked(GCheckbox source, GEvent event) { //_CODE_:LocationPosition:620801:
//  println("LocationPosition - GCheckbox >> GEvent." + event + " @ " + millis());
  location_vector_vis = !location_vector_vis;
  
} //_CODE_:LocationPosition:620801:

public void velocity_clicked(GCheckbox source, GEvent event) { //_CODE_:VelocityVector:786220:
//  println("VelocityVector - GCheckbox >> GEvent." + event + " @ " + millis());
    velocity_vector_vis = !velocity_vector_vis;

} //_CODE_:VelocityVector:786220:

public void acceleration_clicked(GCheckbox source, GEvent event) { //_CODE_:AccelerationVector:939220:
//  println("AccelerationVector - GCheckbox >> GEvent." + event + " @ " + millis());
    acceleration_vector_vis = !acceleration_vector_vis;

} //_CODE_:AccelerationVector:939220:

public void slider1_change1(GSlider source, GEvent event) { //_CODE_:slider1:819921:
//  println("slider1 - GSlider >> GEvent." + event + " @ " + millis());
  damping = (float)slider1.getValueF();
} //_CODE_:slider1:819921:

public void showGrid_clicked(GCheckbox source, GEvent event) { //_CODE_:ShowGrid:223626:
//  println("ShowGrid - GCheckbox >> GEvent." + event + " @ " + millis());
  grid_vis = !grid_vis;
} //_CODE_:ShowGrid:223626:

public void showAxis_clicked(GCheckbox source, GEvent event) { //_CODE_:ShowAxis:268350:
//  println("ShowAxis - GCheckbox >> GEvent." + event + " @ " + millis());
      axis_vis = !axis_vis;
} //_CODE_:ShowAxis:268350:

public void panel1_Click1(GPanel source, GEvent event) { //_CODE_:panel1:580054:
  println("panel1 - GPanel >> GEvent." + event + " @ " + millis());
} //_CODE_:panel1:580054:

public void FrameRate_change(GSlider source, GEvent event) { //_CODE_:FrameRate:732280:
  println("FrameRate - GSlider >> GEvent." + event + " @ " + millis());
  frameRate(FrameRate.getValueI());
} //_CODE_:FrameRate:732280:

public void pause_click(GButton source, GEvent event) { //_CODE_:Pause:925319:
  //println("Pause - GButton >> GEvent." + event + " @ " + millis());
  paused = !paused;
} //_CODE_:Pause:925319:

public void tracers_clicked(GCheckbox source, GEvent event) { //_CODE_:ShowTracers:279754:
//  println("ShowTracers - GCheckbox >> GEvent." + event + " @ " + millis());
  tracer_vis=!tracer_vis;
} //_CODE_:ShowTracers:279754:



// Create all the GUI controls. 
// autogenerated do not edit
public void createGUI(){
  G4P.messagesEnabled(false);
  G4P.setGlobalColorScheme(GCScheme.BLUE_SCHEME);
  G4P.setCursor(ARROW);
  if(frame != null)
    frame.setTitle("Rowbottom's Awesome Vector Explorer");
  MousePosition = new GCheckbox(this, 10, 10, 150, 20);
  MousePosition.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
  MousePosition.setText("Mouse Position Vector");
  MousePosition.setLocalColorScheme(GCScheme.YELLOW_SCHEME);
  MousePosition.setOpaque(true);
  MousePosition.addEventHandler(this, "mouse_clicked");
  MousePosition.setSelected(true);
  LocationPosition = new GCheckbox(this, 10, 30, 150, 20);
  LocationPosition.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
  LocationPosition.setText("Location Pos Vector");
  LocationPosition.setLocalColorScheme(GCScheme.YELLOW_SCHEME);
  LocationPosition.setOpaque(true);
  LocationPosition.addEventHandler(this, "location_clicked");
  LocationPosition.setSelected(true);
  VelocityVector = new GCheckbox(this, 10, 50, 150, 20);
  VelocityVector.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
  VelocityVector.setText("Velocity Vector");
  VelocityVector.setLocalColorScheme(GCScheme.RED_SCHEME);
  VelocityVector.setOpaque(true);
  VelocityVector.addEventHandler(this, "velocity_clicked");
  AccelerationVector = new GCheckbox(this, 10, 70, 150, 20);
  AccelerationVector.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
  AccelerationVector.setText("Acceleration Vector");
  AccelerationVector.setLocalColorScheme(GCScheme.CYAN_SCHEME);
  AccelerationVector.setOpaque(true);
  AccelerationVector.addEventHandler(this, "acceleration_clicked");
  slider1 = new GSlider(this, 10, 660, 150, 50, 10.0f);
  slider1.setShowValue(true);
  slider1.setShowLimits(true);
  slider1.setLimits(0.002f, 0.0f, 0.01f);
  slider1.setNbrTicks(6);
  slider1.setStickToTicks(true);
  slider1.setShowTicks(true);
  slider1.setNumberFormat(G4P.DECIMAL, 3);
  slider1.setLocalColorScheme(GCScheme.GOLD_SCHEME);
  slider1.setOpaque(false);
  slider1.addEventHandler(this, "slider1_change1");
  DampingLabel = new GLabel(this, 10, 640, 150, 20);
  DampingLabel.setText("Friction Damping");
  DampingLabel.setLocalColorScheme(GCScheme.GOLD_SCHEME);
  DampingLabel.setOpaque(false);
  ShowGrid = new GCheckbox(this, 1070, 10, 120, 20);
  ShowGrid.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
  ShowGrid.setText("Show Grid");
  ShowGrid.setLocalColorScheme(GCScheme.GOLD_SCHEME);
  ShowGrid.setOpaque(false);
  ShowGrid.addEventHandler(this, "showGrid_clicked");
  ShowGrid.setSelected(true);
  ShowAxis = new GCheckbox(this, 1070, 30, 120, 20);
  ShowAxis.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
  ShowAxis.setText("Show Axis");
  ShowAxis.setLocalColorScheme(GCScheme.GOLD_SCHEME);
  ShowAxis.setOpaque(false);
  ShowAxis.addEventHandler(this, "showAxis_clicked");
  ShowAxis.setSelected(true);
  panel1 = new GPanel(this, 160, 10, 405, 125, "Vector Properties");
  panel1.setCollapsed(true);
  panel1.setText("Vector Properties");
  panel1.setOpaque(true);
  panel1.addEventHandler(this, "panel1_Click1");
  Loc_label = new GLabel(this, 0, 40, 80, 20);
  Loc_label.setTextAlign(GAlign.RIGHT, GAlign.MIDDLE);
  Loc_label.setText("Location");
  Loc_label.setOpaque(false);
  X_header = new GLabel(this, 80, 20, 80, 20);
  X_header.setText("X component");
  X_header.setOpaque(false);
  Y_header = new GLabel(this, 160, 20, 80, 20);
  Y_header.setText("Y Component");
  Y_header.setOpaque(false);
  M_header = new GLabel(this, 240, 20, 80, 20);
  M_header.setText("Magnitude");
  M_header.setOpaque(false);
  A_header = new GLabel(this, 320, 20, 80, 20);
  A_header.setText("Angle");
  A_header.setOpaque(false);
  Mouse_label = new GLabel(this, 0, 60, 80, 20);
  Mouse_label.setTextAlign(GAlign.RIGHT, GAlign.MIDDLE);
  Mouse_label.setText("Mouse");
  Mouse_label.setOpaque(false);
  Vel_Label = new GLabel(this, 0, 80, 80, 20);
  Vel_Label.setTextAlign(GAlign.RIGHT, GAlign.MIDDLE);
  Vel_Label.setText("Velocity");
  Vel_Label.setLocalColorScheme(GCScheme.RED_SCHEME);
  Vel_Label.setOpaque(false);
  Acc_label = new GLabel(this, 0, 100, 80, 20);
  Acc_label.setTextAlign(GAlign.RIGHT, GAlign.MIDDLE);
  Acc_label.setText("Acceleration");
  Acc_label.setLocalColorScheme(GCScheme.CYAN_SCHEME);
  Acc_label.setOpaque(false);
  loc_X = new GLabel(this, 80, 40, 80, 20);
  loc_X.setText("0.0");
  loc_X.setOpaque(false);
  loc_Y = new GLabel(this, 160, 40, 80, 20);
  loc_Y.setText("0.0");
  loc_Y.setOpaque(false);
  loc_mag = new GLabel(this, 240, 40, 80, 20);
  loc_mag.setText("0.0");
  loc_mag.setOpaque(false);
  loc_ang = new GLabel(this, 320, 40, 80, 20);
  loc_ang.setText("0.0");
  loc_ang.setOpaque(false);
  mouse_X = new GLabel(this, 80, 60, 80, 20);
  mouse_X.setText("0.0");
  mouse_X.setOpaque(false);
  mouse_Y = new GLabel(this, 160, 60, 80, 20);
  mouse_Y.setText("0.0");
  mouse_Y.setOpaque(false);
  mouse_mag = new GLabel(this, 240, 60, 80, 20);
  mouse_mag.setText("0.0");
  mouse_mag.setOpaque(false);
  mouse_ang = new GLabel(this, 320, 60, 80, 20);
  mouse_ang.setText("0.0");
  mouse_ang.setOpaque(false);
  vel_X = new GLabel(this, 80, 80, 80, 20);
  vel_X.setText("0.0");
  vel_X.setLocalColorScheme(GCScheme.RED_SCHEME);
  vel_X.setOpaque(false);
  vel_Y = new GLabel(this, 160, 80, 80, 20);
  vel_Y.setText("0.0");
  vel_Y.setLocalColorScheme(GCScheme.RED_SCHEME);
  vel_Y.setOpaque(false);
  vel_mag = new GLabel(this, 240, 80, 80, 20);
  vel_mag.setText("0.0");
  vel_mag.setLocalColorScheme(GCScheme.RED_SCHEME);
  vel_mag.setOpaque(false);
  vel_ang = new GLabel(this, 320, 80, 80, 20);
  vel_ang.setText("0.0");
  vel_ang.setLocalColorScheme(GCScheme.RED_SCHEME);
  vel_ang.setOpaque(false);
  acc_X = new GLabel(this, 80, 100, 80, 20);
  acc_X.setText("0.0");
  acc_X.setLocalColorScheme(GCScheme.CYAN_SCHEME);
  acc_X.setOpaque(false);
  acc_Y = new GLabel(this, 160, 100, 80, 20);
  acc_Y.setText("0.0");
  acc_Y.setLocalColorScheme(GCScheme.CYAN_SCHEME);
  acc_Y.setOpaque(false);
  acc_mag = new GLabel(this, 240, 100, 80, 20);
  acc_mag.setText("0.0");
  acc_mag.setLocalColorScheme(GCScheme.CYAN_SCHEME);
  acc_mag.setOpaque(false);
  acc_ang = new GLabel(this, 320, 100, 80, 20);
  acc_ang.setText("0.0");
  acc_ang.setLocalColorScheme(GCScheme.CYAN_SCHEME);
  acc_ang.setOpaque(false);
  panel1.addControl(Loc_label);
  panel1.addControl(X_header);
  panel1.addControl(Y_header);
  panel1.addControl(M_header);
  panel1.addControl(A_header);
  panel1.addControl(Mouse_label);
  panel1.addControl(Vel_Label);
  panel1.addControl(Acc_label);
  panel1.addControl(loc_X);
  panel1.addControl(loc_Y);
  panel1.addControl(loc_mag);
  panel1.addControl(loc_ang);
  panel1.addControl(mouse_X);
  panel1.addControl(mouse_Y);
  panel1.addControl(mouse_mag);
  panel1.addControl(mouse_ang);
  panel1.addControl(vel_X);
  panel1.addControl(vel_Y);
  panel1.addControl(vel_mag);
  panel1.addControl(vel_ang);
  panel1.addControl(acc_X);
  panel1.addControl(acc_Y);
  panel1.addControl(acc_mag);
  panel1.addControl(acc_ang);
  FrameRate = new GSlider(this, 930, 660, 260, 50, 10.0f);
  FrameRate.setShowValue(true);
  FrameRate.setShowLimits(true);
  FrameRate.setLimits(60, 1, 100);
  FrameRate.setNbrTicks(10);
  FrameRate.setStickToTicks(false);
  FrameRate.setNumberFormat(G4P.INTEGER, 0);
  FrameRate.setLocalColorScheme(GCScheme.GOLD_SCHEME);
  FrameRate.setOpaque(false);
  FrameRate.addEventHandler(this, "FrameRate_change");
  framerate_label = new GLabel(this, 930, 640, 260, 20);
  framerate_label.setText("Frame Rate (Speed of Simulation)");
  framerate_label.setLocalColorScheme(GCScheme.GOLD_SCHEME);
  framerate_label.setOpaque(false);
  Pause = new GButton(this, 560, 680, 80, 30);
  Pause.setText("Play / Pause");
  Pause.setTextBold();
  Pause.addEventHandler(this, "pause_click");
  ShowTracers = new GCheckbox(this, 1070, 50, 120, 20);
  ShowTracers.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
  ShowTracers.setText("Show Tracers");
  ShowTracers.setLocalColorScheme(GCScheme.GOLD_SCHEME);
  ShowTracers.setOpaque(false);
  ShowTracers.addEventHandler(this, "tracers_clicked");
  ShowTracers.setSelected(true);
}

// Variable declarations 
// autogenerated do not edit
GCheckbox MousePosition; 
GCheckbox LocationPosition; 
GCheckbox VelocityVector; 
GCheckbox AccelerationVector; 
GSlider slider1; 
GLabel DampingLabel; 
GCheckbox ShowGrid; 
GCheckbox ShowAxis; 
GPanel panel1; 
GLabel Loc_label; 
GLabel X_header; 
GLabel Y_header; 
GLabel M_header; 
GLabel A_header; 
GLabel Mouse_label; 
GLabel Vel_Label; 
GLabel Acc_label; 
GLabel loc_X; 
GLabel loc_Y; 
GLabel loc_mag; 
GLabel loc_ang; 
GLabel mouse_X; 
GLabel mouse_Y; 
GLabel mouse_mag; 
GLabel mouse_ang; 
GLabel vel_X; 
GLabel vel_Y; 
GLabel vel_mag; 
GLabel vel_ang; 
GLabel acc_X; 
GLabel acc_Y; 
GLabel acc_mag; 
GLabel acc_ang; 
GSlider FrameRate; 
GLabel framerate_label; 
GButton Pause; 
GCheckbox ShowTracers; 
  public void settings() {  size(1200,720);  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--hide-stop", "JavaAccelerationWithVectors2_0" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
