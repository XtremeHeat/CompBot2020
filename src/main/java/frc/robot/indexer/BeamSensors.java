package frc.robot.indexer;

import edu.wpi.first.wpilibj.DigitalInput;

public class BeamSensors{

    private DigitalInput[] positions = new DigitalInput[3];

    private boolean[] previousOutput = { true, true, true };

    public BeamSensors(){
        for( int i = 0; i < positions.length; i++ ){
            positions[i] = new DigitalInput( i );
        }
    }

    public boolean getSensor( int position ){
        if( position >= 3 || position < 0 )
            throw new IllegalArgumentException( "Invalid position" );
        else
            return positions[ position ].get();
    } 

    public boolean[] newBallFound(){
        boolean[] returnArray = {false, false, false};

        for( int i = 0; i < returnArray.length - 1; i++ ){
            if( previousOutput[i] && !getSensor( i ) ){
                returnArray[i] = true;
            }else{
                returnArray[i] = false;
            }

            previousOutput[i] = getSensor( i );
        }

        if( !previousOutput[2] && getSensor( 2 ) ){
            returnArray[2] = true;
        }else{
            returnArray[2] = false;
        }

        previousOutput[2] = getSensor( 2 );

        return returnArray;
    }


}