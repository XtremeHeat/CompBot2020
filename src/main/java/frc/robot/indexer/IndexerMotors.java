package frc.robot.indexer;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class IndexerMotors{

    private CANSparkMax[] motors = { new CANSparkMax( 21, MotorType.kBrushless ),  
        new CANSparkMax( 22, MotorType.kBrushless ), 
        new CANSparkMax( 23, MotorType.kBrushless ) };

    

    private CANEncoder[] encoders = { new CANEncoder(  motors[0] ),
        new CANEncoder( motors[1] ),
        new CANEncoder( motors[2] )};

    public IndexerMotors(){

        motors[0].setInverted( true );
        motors[0].setSmartCurrentLimit( 40, 80 );

        motors[1].setInverted( false );
        motors[1].setSmartCurrentLimit( 40, 80 );

        motors[2].setInverted( true );
        motors[2].setSmartCurrentLimit( 40, 80 );
    }

    public void setBottom( double speed ){
        motors[0].set( speed );
    }

    public void setMiddle( double speed ){
        motors[1].set( speed );
    }

    public void setTop( double speed ){
        motors[2].set( speed );
    }

    public void resetPosition( int encoder ){
        if( encoder < 0 || encoder >= 3 ){
            throw new IllegalArgumentException( "Invalid encoder" );
        }else{
            encoders[ encoder ].setPosition( 0 );
        }
    }

    public double getPosition( int encoder ){
        if( encoder < 0 || encoder >= 3 ){
            throw new IllegalArgumentException( "Invalid encoder" );
        }else{
            return encoders[encoder].getPosition();
        }
    }

}