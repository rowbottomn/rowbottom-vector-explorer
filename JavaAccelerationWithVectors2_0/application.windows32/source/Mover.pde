
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

  void update() {

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
    acceleration.div(1000.);
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
    velocity.mult(1.-damping);
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

  void makeArrowHead(PVector vec, float direction) {
    int arrowSize = 8;
    pushMatrix();
    translate(vec.x, vec.y);
    rotate(direction);
    triangle(0., 0., - 8., - 4., - 8, + 4.);     
    popMatrix();
  }

  void display() {
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