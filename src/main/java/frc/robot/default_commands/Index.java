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

public class Index extends Command {
  public Index() {
    // Use requires() here to declare subsystem dependencies
    requires( Robot.indexerSubsystem );
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {  //order 66

    Robot.indexerSubsystem.checkBeams();

    if( Robot.operatorStick.getA() ){
      Robot.indexerSubsystem.intake.set( 0.5 );
      //Robot.indexerSubsystem.useRobotBody( 0.5, 0.5);
    }else{
      Robot.indexerSubsystem.intake.set( 0 );
      //Robot.indexerSubsystem.useRobotBody( 0, 0.5);
    }

    if( Robot.operatorStick.getX() ){
      Robot.indexerSubsystem.indexer.setMiddle( 1 );
    }else{
      Robot.indexerSubsystem.indexer.setMiddle( 0 );
    }

    if( Robot.operatorStick.getY() ){
      Robot.indexerSubsystem.indexer.setBottom( .6 );
    }else{
      Robot.indexerSubsystem.indexer.setBottom( 0 );
    }

    if( Robot.operatorStick.getLeftBumper() ){
      Robot.indexerSubsystem.indexer.setTop( .3 );
    }else{
      Robot.indexerSubsystem.indexer.setTop( 0 );
    }

    Robot.indexerSubsystem.indexBalls( .5, 1, .6 );


    if( Robot.operatorStick.getB() ){
      Robot.indexerSubsystem.abort();
    }

    Robot.indexerSubsystem.emptyMagazine( Robot.driverStick.getA(),
     Robot.driverStick.getB() );
    
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
