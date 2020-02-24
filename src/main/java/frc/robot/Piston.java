package frc.robot;

import edu.wpi.first.wpilibj.Solenoid;

public class Piston{

    private Solenoid piston;

    public Piston( int channel ){
        piston = new Solenoid( 9, channel );
    }

    public void set( boolean on ){
        piston.set( on );
    }

    public boolean get(){
        return piston.get();
    }
}