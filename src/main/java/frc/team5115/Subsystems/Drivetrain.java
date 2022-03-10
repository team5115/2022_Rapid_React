package frc.team5115.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Encoder; 
import static frc.team5115.Constants.kD;
import static java.lang.Math.tan;
import static java.lang.Math.toRadians;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.AnalogInput;

import static frc.team5115.Constants.*;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;

public class Drivetrain extends SubsystemBase{

    private TalonSRX frontLeft;
    private TalonSRX frontRight;
    private TalonSRX backLeft;
    private TalonSRX backRight;

    private double frontLeftSpeed;
    private double frontRightSpeed;
    private double backLeftSpeed;
    private double backRightSpeed;

    private double rightSpd;
    private double leftSpd;

   // public AHRS gyro;

    public double d;
    public double distancefromrobot;
    public boolean balldetected;
    public NetworkTable ShooterCam;
    public NetworkTableEntry ty;
    public NetworkTableEntry tx;
    public NetworkTableEntry tv;
    //private AnalogInput DistanceDetector1;
    private double AverageDistanceDetector1;
    public double backLeftEncoder;
    public double backRightEncoder;
    
    public Drivetrain() {
        frontLeft = new TalonSRX(FRONT_LEFT_MOTOR_ID);
        frontRight = new TalonSRX(FRONT_RIGHT_MOTOR_ID);
        backLeft = new TalonSRX(BACK_LEFT_MOTOR_ID);
        backRight = new TalonSRX(BACK_RIGHT_MOTOR_ID);

      //  gyro= new AHRS(SPI.Port.kMXP);
        
        //gyro.reset();
        
        AverageDistanceDetector1 = 0;
        balldetected = false;
        
        frontLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        frontRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        backLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        backRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

    }

//-----start of drive methods-----
    //stops drivetrain
    public void stop() {
        plugAndChugDrive(0, 0, 0, 0);
    }

     /**
     * Sets speed for robots using tank drivetrains, allowing them to actually move
     * 
     * @param x 
     * @param y
     * @param throttle
     */
    public void TankDrive(double x, double y, double throttle) { 
        leftSpd = (x-y) * throttle;
        rightSpd = (x+y) * throttle;
        plugAndChugDrive(leftSpd, rightSpd, leftSpd, rightSpd);
    }

    /**
     * 
     * @param y
     * @param x
     * @param z
     */
    public void MecanumSimpleDrive(double y, double x, double z) {
    
        frontLeftSpeed = (-x + y + z);
        backLeftSpeed = (-x + y - z);
        frontRightSpeed = (x +  y + z);
        backRightSpeed = (x + y - z);

        plugAndChugDrive(frontLeftSpeed, frontRightSpeed, backLeftSpeed, backRightSpeed);
    }

    /**
     * sets robot to drive to field-oriented drive, instead of usual robot-oriented drive
     * field-oriented drive: driving in respect to the field itself
     * robotic-oriented drive: driving in respect to the robot
     *
     * @param strafe
     * @param fwd
     * @param rotate
     */
    public void FieldOrientedDrive(double strafe, double fwd, double rotate){
        double x;
        double y;
        double pi = 3.1415926;
       // float gyro_degrees = gyro.getYaw();
        double gyro_degrees = 0.5123;
        double gyro_radians = gyro_degrees * pi/180; 

        x = strafe*Math.cos(gyro_radians) + fwd*Math.sin(gyro_radians);
        y = strafe*Math.sin(gyro_radians) - fwd*Math.cos(gyro_radians);

        frontLeftSpeed = (y + x + rotate);
        backLeftSpeed = (y - x + rotate);
        frontRightSpeed = (-y + x + rotate);
        backRightSpeed = (-y - x + rotate);

        plugAndChugDrive(frontLeftSpeed, frontRightSpeed, backLeftSpeed, backRightSpeed);
    }

    /**
    * Streamlines process of setting speed of robot wheels 
    * 
    * @param frontLeftSpeed input speed value of front left wheel  
    * @param frontRightSpeed input speed value of front right wheel  
    * @param backLeftSpeed input speed value of back left wheel  
    * @param backRightSpeed input speed value of back right wheel  
    */
    public void plugAndChugDrive(double frontLeftSpeed, double frontRightSpeed, double backLeftSpeed, double backRightSpeed){
        frontLeft.set(ControlMode.PercentOutput, frontLeftSpeed);
        frontRight.set(ControlMode.PercentOutput, frontRightSpeed);
        backLeft.set(ControlMode.PercentOutput, backLeftSpeed);
        backRight.set(ControlMode.PercentOutput, backRightSpeed);
    }

    //SET speed for wheels for autodrive, used in AutoDrive.java and DriveForward.java 
    public void autodrive(){
        plugAndChugDrive(0.25, -0.25, 0.25, -0.25);
    }

    public void backwardsdrive(){
        plugAndChugDrive(-0.25, 0.25, -0.25, 0.25);
    }

//-----end of drive methods-----

//-----start of distance methods-----
    public void printEncoderDistance(){
    backLeftEncoder = -backLeft.getSelectedSensorPosition()*ENCODER_CALIBRATION;
    backRightEncoder = backRight.getSelectedSensorPosition()*ENCODER_CALIBRATION;
    System.out.println(backLeftEncoder);
    }

   /* public void DistanceDetectionRaw(){
      AverageDistanceDetector1 = DistanceDetector1.getVoltage()*ULTRASONIC_UNIT_CONVERSION;
        System.out.println("ultrasonic:    " + AverageDistanceDetector1);
    }

    public void DistanceDetectionAverage(){
        ShooterCam = NetworkTableInstance.getDefault().getTable("limelight");
            ty = ShooterCam.getEntry("ty");
            tx = ShooterCam.getEntry("tx");
            tv = ShooterCam.getEntry("tv");
        
        balldetected = !(DistanceDetector1.getVoltage()*ULTRASONIC_UNIT_CONVERSION>300);
        
        double n = 1000;
        double f = 0;

        for(int i = 0; i< n; i++){
            double j = DistanceDetector1.getVoltage()*ULTRASONIC_UNIT_CONVERSION;
            f =+ j;
        }

        AverageDistanceDetector1 = f/n;

        System.out.println("ultrasonic:    " + AverageDistanceDetector1);

    }
    */

    public void resetEncoder(){
        backLeft.setSelectedSensorPosition(0);
        backRight.setSelectedSensorPosition(0);
    }

//-----end of detection methods-----

//-----start of adjust methods-----
    public void AdjustAngle(){
            double xangle = -tx.getDouble(0); 
            double dectector = tv.getDouble(0);
            if(dectector == 1){
                if(xangle > 0){
                rightSpd = xangle*kD;
                leftSpd = -rightSpd;
                plugAndChugDrive(-rightSpd, -rightSpd, 0, 0);
            }
            else{
                leftSpd = -xangle*kD;
                rightSpd = leftSpd;
                plugAndChugDrive(rightSpd, rightSpd, 0, 0);
            }
        }
        
    }

    /*public void AdjustAngleToBall(){
        plugAndChugDrive(-0.25, 0.25, 0, 0);
    }

    public void AdjustDistanceToBall(){
        leftSpd = (Math.abs(AverageDistanceDetector1-150)*hD)/2;
        rightSpd = (Math.abs(AverageDistanceDetector1-150)*hD)/2;
        plugAndChugDrive(leftSpd, rightSpd, leftSpd, rightSpd);
    }*/

    public void AdjustDistance(){
        
        double yangle = ty.getDouble(0); 
        double dectector = tv.getDouble(0);
        if(dectector == 1){
        d = (AUTO_HIGH_GOAL_HEIGHT - AUTO_CAMERA_HEIGHT) / tan(toRadians(yangle + AUTO_CAMERA_ANGLE));
        if(d> HUB_DISTANCE){
       
            leftSpd = (d-HUB_DISTANCE)*hD;
            rightSpd = (d - HUB_DISTANCE)*hD;
        }
        else{
            leftSpd = (HUB_DISTANCE - d)*hD;
            rightSpd = (HUB_DISTANCE - d)*hD;
        }
        plugAndChugDrive(leftSpd, rightSpd, leftSpd, rightSpd);
        }
        
    }

//-----end of adjust methods-----

//-----start of get methods-----
    
    public double getX(){
        return tx.getDouble(0);
    }

    public double getDistanceFromHub(){
        double yAngle = ty.getDouble(0);
        d = (AUTO_HIGH_GOAL_HEIGHT - AUTO_CAMERA_HEIGHT) / tan(toRadians(yAngle + AUTO_CAMERA_ANGLE));
        return d;
    }


//-----end of get methods-----
}