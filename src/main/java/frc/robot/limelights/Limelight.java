package frc.robot.limelights;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {

    NetworkTable table;

    public void initializeLimelight( String name ){
        table = NetworkTableInstance.getDefault().getTable( name );
    }

    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    NetworkTableEntry tlong = table.getEntry("tlong");

    public boolean on = true;

    public double getX(){
        return tx.getDouble( 0.0 );
    }

    public double getY(){
        return ty.getDouble( 0.0 );
    }

    public double getArea(){
        return ta.getDouble( 0.0 );
    }

    public double getWidth(){
        return tlong.getDouble(0.0);
    }
}