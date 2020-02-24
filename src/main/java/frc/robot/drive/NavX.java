package frc.robot.drive;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;


public class NavX{

    private AHRS navX = new AHRS( SPI.Port.kMXP );

    public double getNavX(){
        return navX.getAngle();
    }

    public void resetNavX(){
        navX.reset();
    }
    
}