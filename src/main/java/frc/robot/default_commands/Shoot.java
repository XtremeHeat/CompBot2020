/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.default_commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.shooter.ShooterValues;

public class Shoot extends Command {
  public Shoot() {
    // Use requires() here to declare subsystem dependencies
    requires( Robot.shooterSubsystem );
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if( Robot.driverStick.getLeftBumper() ){
      Robot.shooterSubsystem.setShooter( .4 );
      Robot.indexerSubsystem.disposeOfBalls( 0.1 );
    }else if( Robot.driverStick.getA() ){
      Robot.shooterSubsystem.setShooterPID( ShooterValues.FAR_SPEED );
      //Robot.shooterSubsystem.shooter.setMotors( 0.5 );
    }else{
      Robot.shooterSubsystem.setShooter( 0 ); 
    }
    
    Robot.shooterSubsystem.setPistons( !Robot.driverStick.getB() );
  }


  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
