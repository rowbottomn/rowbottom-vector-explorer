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
  slider1 = new GSlider(this, 10, 660, 150, 50, 10.0);
  slider1.setShowValue(true);
  slider1.setShowLimits(true);
  slider1.setLimits(0.002, 0.0, 0.01);
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
  FrameRate = new GSlider(this, 930, 660, 260, 50, 10.0);
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