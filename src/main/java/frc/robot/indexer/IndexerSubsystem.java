/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.indexer;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Piston;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.default_commands.Index;
import frc.robot.shooter.ShooterValues;

/**
 * Add your docs here.
 */
public class IndexerSubsystem extends Subsystem {

  private Piston intakePiston = new Piston( 1 );

  public BeamSensors beamSensors = new BeamSensors();
  public IndexerMotors indexer = new IndexerMotors();
  public Intake intake = new Intake();

  private boolean[] newBallFound = { false, false, false };

  private int balls  = 0;
  private int ballsNotIndexed = 0;

  //balls not yet inside the robot body
  private int ballsNotCounted = 0;
  
  private double secsSinceLastShot = 0;

  private boolean emptyMagazine = false;

  public void checkBeams(){
    newBallFound = beamSensors.newBallFound();
  }
  

  public void reset(){
    balls = 0;
    ballsNotIndexed = 0;
    ballsNotCounted = 0;
    for( int i = 0; i < 3; i++ ){
      indexer.resetPosition( i );
    }
  }

  public void setPiston( boolean on ){
    intakePiston.set( on );
  }

  public void disposeOfBalls( boolean dispose, boolean countSecs, boolean beginIndexing ){
    if( balls <= 0 ){
      return;
    }

    if( dispose ){
      if( !beamSensors.getSensor( 2 ) )
        indexer.setTop( IndexerValues.TOP_SPIT );
      else{
        if( countSecs ) secsSinceLastShot = 0;
      }
    }else{
      if( beamSensors.getSensor( 2 ) && beginIndexing ){
        indexer.setBottom( IndexerValues.BOTTOM_SPIT );
        indexer.setMiddle( IndexerValues.MIDDLE_SPIT );
        indexer.setTop( IndexerValues.TOP_SPIT );
      }else{
        indexer.setTop( 0 );
        indexer.setMiddle( 0 );
        indexer.setBottom( 0 );
    }
  }

  }

  public void emptyMagazine( boolean start, boolean cancel ){
    if( start ){
      emptyMagazine = true;
    }
    if( cancel ){
      emptyMagazine = false; 
    }

    secsSinceLastShot += RobotMap.robot.getPeriod();

    if( emptyMagazine )
      Robot.indexerSubsystem.disposeOfBalls( 
          Math.abs( Robot.shooterSubsystem.getVelocity() - ShooterValues.FAR_SPEED )
          < ShooterValues.FAR_DEADZONE, true,  Robot.shooterSubsystem.getVelocity() > 30000 );


    if( balls == 0 ){
      emptyMagazine = false;
    }
  }

  public void disposeOfBalls( double secsBetweenShots ){


    secsSinceLastShot += RobotMap.robot.getPeriod();

    disposeOfBalls( secsSinceLastShot > secsBetweenShots, true, true );

  }

  public void abort(){

    balls = 0;
    ballsNotIndexed = 0;
    ballsNotCounted = 0;

    indexer.setTop( -1);
    indexer.setMiddle( -1);
    indexer.setBottom( -1 );
    
  }

  public void indexBalls( double topSpeed, double middleSpeed, double bottomSpeed ){

    if( newBallFound[0] ){
      indexer.resetPosition( 0 );
      indexer.resetPosition( 1 );
      ballsNotIndexed++;
      ballsNotCounted++;
      balls++;
     
    }
    
    if( newBallFound[2] ){
      balls--;
    }

    if( newBallFound[1] ){
      indexer.resetPosition( 0 );
      ballsNotCounted--;

      if( ballsNotCounted < 0 ){
        ballsNotIndexed = 0;
        balls++;
        ballsNotIndexed++;
      }

    }

    if( balls < 0 )
      balls = 0;
    
    if( ballsNotCounted < 0 )
      ballsNotCounted = 0;
    
    if( ballsNotIndexed < 0 )
      ballsNotIndexed = 0;
    
    if( balls >= 5 ){
      intakePiston.set( true );
      return;
    }else{
      intakePiston.set( false );
    }

    switch( ( ballsNotIndexed > 0 )? balls + 1 - ballsNotIndexed : balls ){
      case 1: 
        if( indexer.getPosition( 0 ) > IndexerValues.BEAM_TO_STALL && ballsNotCounted == 0 ){
          if( indexer.getPosition( 1 ) < IndexerValues.INDEXER_POS ){
            indexer.setMiddle( middleSpeed );
          }else{
            ballsNotIndexed--;
          }
        }else{
          indexer.setBottom( bottomSpeed );
        }
        break;
      case 2: 
        if( indexer.getPosition( 0 ) < IndexerValues.BEAM_TO_STALL || ballsNotCounted != 0)
          indexer.setBottom( bottomSpeed );
        else
          ballsNotIndexed--;
        break;
      case 3: 
        if( ballsNotCounted > 0 ){
          indexer.setBottom( bottomSpeed );
        }
        else
          ballsNotIndexed--;
        break;
      case 4:
        if( ballsNotCounted > 0 ){
          indexer.setBottom( bottomSpeed );
        }
        else
          ballsNotIndexed--;
      case 5: ballsNotIndexed--;

    }

  }

  public void useRobotBody( double intakeSpeed, double indexerSpeed ){
    //intake.set( intakeSpeed );
    intake.set( 0 );
    indexBalls( 0, 0, 0 );
    //indexBalls( indexerSpeed );
  }

  public boolean getBeamSensor( int sensor ){
    try{
     return beamSensors.getSensor( sensor );
    }catch( IllegalArgumentException e ){
      return false;
    }
  }

  
  public int getBalls(){
    return balls + ballsNotCounted;
  }

  public double getIntakeVelocity(){
    return intake.getVelocity();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new Index());
  }
}
