/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TurnToAngle extends Command {

  private double targetAngle;
  private double maxSpeed;

  public TurnToAngle( double targetAngle, double maxSpeed ) {
    // Use requires() here to declare subsystem dependencies
    this.targetAngle = targetAngle;
    this.maxSpeed = maxSpeed;

    requires( Robot.driveSubsystem );
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double error = targetAngle - Robot.driveSubsystem.getNavX();
    double speed = error * AutoValues.TURN_KP;

    if( speed > 0 && speed > maxSpeed ){
      speed = maxSpeed;
    }
    if( speed < 0 && speed < -maxSpeed ){
      speed = -maxSpeed;
    }

    Robot.driveSubsystem.setLeft( speed );
    Robot.driveSubsystem.setRight( -speed );
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs( targetAngle - Robot.driveSubsystem.getNavX() ) <= AutoValues.TURN_DEADZONE;
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
