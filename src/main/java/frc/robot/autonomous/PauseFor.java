/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class PauseFor extends Command {
  private double millisWaited = 0;

  private double waitFor = 0; 

  public PauseFor( double pause ) {
    waitFor = pause;
    // Use requires() here to declare subsystem dependencies
    requires( Robot.driveSubsystem );
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.driveSubsystem.cheesyDrive( 0, 0 );
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    millisWaited += ( RobotMap.robot.getPeriod() );
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return millisWaited >= waitFor;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
