package frc.robot.shooter;

public class DistanceEquation{

    private double vi;
    private double theta;
    private double yi;

    private double vx;
    private double vy;

    public DistanceEquation( double initialVelocity, double theta, double initialY, boolean inRadians ){
       if( !inRadians ){
           theta = ShooterValues.intoRadians( theta );
       }

       initialVelocity = initialVelocity / ShooterValues.INVERSE_RELATION;
        
       vi = initialVelocity;
       this.theta = theta;
       yi = initialY;

       vx = vi * Math.cos( theta );
       vy = vi * Math.sin( theta );
    }

    public double getY( double x ){
        return -4.9 * ( ( x * x ) / ( vx * vx ) ) + vy * ( x / vx ) + yi;
    }

    public double getTargetVelocity( double x, double y ){
        double top = 4.9 * Math.pow( x, 2 );
        double cosSquared = Math.pow( Math.cos( theta ), 2 );
        double otherBottom = ( y - yi - ( x * Math.sin( theta ) / Math.cos( theta ) ) ) * -1;

        double bottom = cosSquared * otherBottom;

        return Math.sqrt( top / bottom );
    }

    


}