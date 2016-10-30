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
      ellipse (tail.get(i).x, tail.get(i).y, i/(float)tail.size() * 48. + 3, i/(float)tail.size()*48. + 3);
    }
  }
}