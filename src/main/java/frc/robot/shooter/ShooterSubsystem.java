/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.shooter;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.PIDLoop;
import frc.robot.Piston;
import frc.robot.default_commands.Shoot;

/**
 * Add your docs here.
 */

public class ShooterSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private Piston hoodPiston = new Piston( 2 );

  private PIDLoop shooterLoop = new PIDLoop( ShooterValues.kP, ShooterValues.kI, ShooterValues.kD );
  public Shooter shooter = new Shooter();
  //private ShooterLimelight limelight = new ShooterLimelight();

  public void setPistons( boolean on ){
    hoodPiston.set( on );
  }

  /*
  public void setShooter(){
      shooter.setMotors( shooterLoop.getValue( limelight.getTargetSpeed( limelight ), shooter.getShaftSpeed() ));
      Robot.indexerSubsystem.shootBalls( 0.5, isStable() );
  }
  */

  public boolean isStable(){
    return shooterLoop.checkForStability( 100, 100 );
  }

  public void setShooter( double speed ){
    shooter.setMotors( speed );
    //Robot.indexerSubsystem.shootBalls( 0.5, isStable() );
  }

  public void setShooterPID( double targetSpeed ){
    shooter.setMotors( shooterLoop.getValue( targetSpeed, shooter.getShaftSpeed() ) );
  }

  public double getVelocity(){
    return shooter.getShaftSpeed();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new Shoot());
  }
}
