package frc.robot.shooter;

import frc.robot.limelights.Limelight;

public class ShooterLimelight extends Limelight{

  public ShooterLimelight(){
      initializeLimelight( "limelight-intake" );
  }

  public double getRawDistance(){
    return ( ShooterValues.TOWER_HEIGHT - ( ShooterValues.TARGET_HEIGHT / 4 ) - ShooterValues.LIMELIGHT_HEIGHT ) / Math.sin( ShooterValues.intoRadians( ShooterValues.LIMELIGHT_ANGLE + getY() ) ) ;
  }
    
  //NO HARDWARE ZOOM ALLOWED!!!
  public double getDistance(){
    return ( ShooterValues.TOWER_HEIGHT - ( ShooterValues.TARGET_HEIGHT / 4 ) - ShooterValues.LIMELIGHT_HEIGHT ) / Math.tan( ShooterValues.intoRadians( ShooterValues.LIMELIGHT_ANGLE + getY() ) );
  }

  public double getTargetSpeed( ShooterLimelight shooterLimelight){
    double targetSpeed = 0;
    
    DistanceEquation distance = new DistanceEquation( targetSpeed, ShooterValues.SHOOTER_ANGLE, ShooterValues.SHOOTER_HEIGHT, false );
    
    return distance.getTargetVelocity( getDistance() + ShooterValues.THREE_POINT_DISTANCE, ShooterValues.TOWER_HEIGHT ) * ShooterValues.INVERSE_RELATION;
  }
}