/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  
  private Joystick stick;
  private JoystickButton[] buttons = new JoystickButton[10];

  private int[] secsWaited = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
  private boolean[] toggle = new boolean[10];

  public OI( int port ){
    stick = new Joystick( port );

    for( int i = 0; i < buttons.length ; i++ ){
      buttons[i] = new JoystickButton( stick, i + 1 );
    }

  }
  public boolean getA(){
    return buttons[0].get();
  }

  public boolean getB(){
    return buttons[1].get();
  }

  public boolean getX(){
    return buttons[2].get();
  }

  public boolean getY(){
    return buttons[3].get();
  }

  public boolean getLeftBumper(){
    return buttons[4].get();
  }

  public double getRawAxis( int axis ){
    return stick.getRawAxis( axis );
  }

 
  

}
