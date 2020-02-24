/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.drive;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.default_commands.DriveCommand;

/**
 * Add your docs here.
 */
public class DriveSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private Side leftSide = new Side( 1, 2, 3, true );
  private Side rightSide = new Side( 4, 5, 6, false );

  private NavX navX = new NavX();

  public double getNavX(){
    return navX.getNavX();
  }

  public void setLeft( double speed ){
    leftSide.set( speed );
  }

  public void setRight( double speed ){
    rightSide.set( speed );
  }

  public void cheesyDrive( double speed, double turn ){

    speed = ( Math.abs( speed ) > DriveValues.DEADZONE )? speed : 0;
    turn = ( Math.abs( turn ) > DriveValues.DEADZONE )? turn : 0;

    leftSide.set( speed + turn );
    rightSide.set( speed - turn );

  }



  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DriveCommand());
  }
}
