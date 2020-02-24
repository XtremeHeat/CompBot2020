package frc.robot.shooter;

public class ShooterValues{

    public static final double FAR_SPEED = 74000;
    public static final double FAR_DEADZONE = 500;

    public static final double kP = 0.0001;
    // 0.0001625: 1st tuning
    public static final double kI = 0.000007;
    // 0.000016: 1st tuning
    public static final double kD = 0;
    //0.00025: 1st tuning

    //dimensions for shooter: in degrees
    public static final double LIMELIGHT_ANGLE = 24.4;
    public static final double SHOOTER_ANGLE = 22.5;
    public static final double SHOOTER_HEIGHT = 3;

    public static final double SHOOT_WAIT_TIME = 300; //length of time of one shot( milliseconds )

    //in feet
    public static final double LIMELIGHT_HEIGHT = 3 + ( 5 / 12 );
    public static final double TARGET_HEIGHT = 2.5; //height of large target
     public static final double TOWER_HEIGHT = 8.1875;   //distance from floor to middle of hole
    public static final double THREE_POINT_DISTANCE = 2.4375;


    public static final double LIGHT_WAIT_TIME = 10;

    //inverse relationship between encoder value and speed
    public static final double INVERSE_RELATION = 2260;

    public static double intoRadians( double degrees ){
        return Math.PI * ( degrees / 180 );
      }
}