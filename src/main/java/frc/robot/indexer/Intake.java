package frc.robot.indexer;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Intake {

    private CANSparkMax intake = new CANSparkMax( 20, MotorType.kBrushless );
    private CANEncoder intakeEncoder = new CANEncoder( intake );

    public void set( double speed ){
        intake.set( speed );
    }

    public double getVelocity(){
        return intakeEncoder.getVelocity();
    }

}