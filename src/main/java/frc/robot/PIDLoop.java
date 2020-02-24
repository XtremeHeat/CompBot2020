package frc.robot;

public class PIDLoop{

    double kP;
    double kI;
    double kD;

    double error = 0;

    double previousError = 0;
    double integral = 0;

    private double millisStable = 0;

    public PIDLoop( double kP, double kI, double  kD ){
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
    }

    public double getValue( double target, double currentSpeed ){

        error = target - currentSpeed;

        double derivative = error - previousError;
        previousError = error;

        //insures that I is not changed if the value is very far away
        if( error * kP > -1 && error * kP < 1 ){
            integral += error;
        }

        double output = ( error * kP ) + ( integral * kI ) + ( derivative * kD );
        return output;
    }

    public boolean checkForStability( double waitFor, double deadzone ){

        boolean stable; 

        if( Math.abs( error ) < deadzone ){
            millisStable += ( 1000 * RobotMap.robot.getPeriod() );
        }else{
            millisStable = 0;
        }

        if( millisStable > waitFor ){
            stable = true;
        }else{
            stable =  false;
        }

        return stable;

    }


}