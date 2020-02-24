package frc.robot.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Shooter {

    private TalonSRX leftShooter1 = new TalonSRX( 10 );
    private VictorSPX leftShooter2 = new VictorSPX( 11 );

    private TalonSRX rightShooter1 = new TalonSRX( 12 );
    private VictorSPX rightShooter2 = new VictorSPX( 13 );

    public double getShaftSpeed(){
        return -leftShooter1.getSelectedSensorVelocity();
    }

    public Shooter(){
        leftShooter1.setInverted( true );
        rightShooter2.setInverted( true );
    }

    public void setMotors( double speed ){
        leftShooter1.set( ControlMode.PercentOutput, speed );
        leftShooter2.set( ControlMode.PercentOutput, speed );
        
        rightShooter1.set( ControlMode.PercentOutput, speed );
        rightShooter2.set( ControlMode.PercentOutput, speed );
    }
}