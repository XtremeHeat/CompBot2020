package frc.robot.drive;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Side {

    private CANSparkMax frontMotor; 
    private CANSparkMax middleMotor;
    private CANSparkMax backMotor;
    
    public Side( int frontID, int middleID, int backID, boolean invert ){
        frontMotor = new CANSparkMax( frontID, MotorType.kBrushless );
        middleMotor = new CANSparkMax( middleID, MotorType.kBrushless );
        backMotor = new CANSparkMax( backID, MotorType.kBrushless );
    
        frontMotor.setInverted( invert);
        middleMotor.setInverted( invert );
        backMotor.setInverted( invert );
    }

    //private CANEncoder shaftEncoder = new CANEncoder( middleMotor );

    public void set( double speed ){
        frontMotor.set( speed );
        middleMotor.set( speed );
        backMotor.set( speed );
    }

    /*
    public double getSpeed(){
        return shaftEncoder.getVelocity();
    }

    public double getPosition(){
        return shaftEncoder.getPosition();
    }

    public double getRPM(){
        return getSpeed() * shaftEncoder.getVelocityConversionFactor();
    }
    
    public double getRotations(){
        return getPosition() * shaftEncoder.getPositionConversionFactor();
    }
    */
}