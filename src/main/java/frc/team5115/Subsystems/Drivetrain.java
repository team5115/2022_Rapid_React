package frc.team5115.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team5115.Robot.RobotContainer;
import static frc.team5115.Constants.kD;
import static java.lang.Math.tan;
import static java.lang.Math.toRadians;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.AnalogInput;

import static frc.team5115.Constants.*;

public class Drivetrain extends SubsystemBase{

    private TalonSRX frontLeft;
    private TalonSRX frontRight;
    private TalonSRX backLeft;
    private TalonSRX backRight;

    private double frontLeftSpeed;
    private double frontRightSpeed;
    private double backLeftSpeed;
    private double backRightSpeed;
    public NetworkTable ShooterCam;
    public NetworkTableEntry ty;
    public NetworkTableEntry tx;
    public NetworkTableEntry tv;

    private double rightSpd;
    private double leftSpd;

    public double d;
    public double AverageDistanceDetector1;
    public double distancefromrobot;
    public boolean balldetected;

    public double backLeftEncoder;
    public double backRightEncoder;

    public double throttle;
   // public double xAngle;
    //public double detector;
 
    
    public Drivetrain() {
        frontLeft = new TalonSRX(FRONT_LEFT_MOTOR_ID);
        frontRight = new TalonSRX(FRONT_RIGHT_MOTOR_ID);
        backLeft = new TalonSRX(BACK_LEFT_MOTOR_ID);
        backRight = new TalonSRX(BACK_RIGHT_MOTOR_ID);

        AverageDistanceDetector1 = 0;
        balldetected = false;

        
        
        frontLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        frontRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        backLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        backRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

        //xAngle = tx.getDouble(0); 
        //detector = ty.getDOuble(0;

        throttle = 1;
    }

    public void stop() {
        plugAndChugDrive(0, 0, 0, 0);
    }

    public void resetGyro(){
      //  gyro.reset();
    }

    public void TankDrive(double x, double y, double throttle) { 
        leftSpd = (x-y) * throttle;
        rightSpd = (x+y) * throttle;
        plugAndChugDrive(leftSpd, rightSpd, leftSpd, rightSpd);
    }

    public void MecanumSimpleDrive(double y, double x, double z) {
    
        frontLeftSpeed = (-x + y + z);
        backLeftSpeed = (-x + y - z);
        frontRightSpeed = (x +  y + z);
        backRightSpeed = (x + y - z);

        plugAndChugDrive(frontLeftSpeed, frontRightSpeed, backLeftSpeed, backRightSpeed);
    }

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

    public void plugAndChugDrive(double frontleftspeed, double frontrightspeed, double backleftspeed, double backrightspeed){
        frontLeft.set(ControlMode.PercentOutput, frontleftspeed*throttle);
        frontRight.set(ControlMode.PercentOutput, frontrightspeed*throttle);
        backLeft.set(ControlMode.PercentOutput, backleftspeed*throttle);
        backRight.set(ControlMode.PercentOutput, backrightspeed*throttle);
    }

    public void autodrive(){
        plugAndChugDrive(0.30, -0.30, 0.30, -0.30);
    }

    public void backwardsdrive(){
        plugAndChugDrive(-0.30, -0.30, -0.30, -0.30);
    }

    public void printEncoderDistance(){
        //backLeftEncoder = -backLeft.getSelectedSensorPosition()*ENCODER_CALIBRATION;
        backRightEncoder = backRight.getSelectedSensorPosition()*ENCODER_CALIBRATION;
        backLeftEncoder = backRight.getSelectedSensorPosition()*ENCODER_CALIBRATION;
        System.out.println(backRightEncoder);
        }
    
    public void resetEncoder(){
            backLeft.setSelectedSensorPosition(0);
            backRight.setSelectedSensorPosition(0);
        }
    
    public void DistanceDetectionAverage(){
        ShooterCam = NetworkTableInstance.getDefault().getTable("limelight");
            ty = ShooterCam.getEntry("ty");
            tx = ShooterCam.getEntry("tx");
            tv = ShooterCam.getEntry("tv");
           //System.out.print(ty.getDouble(0));
           //System.out.print(tx.getDouble(0));
        } 

    public void AdjustAngle(){
        double xangle = -tx.getDouble(0); 
        double detector = tv.getDouble(0);
        leftSpd = -xangle*kD;
        if(leftSpd > 0.3){
            leftSpd = 0.3;
            System.out.println("capping speed");
        }
        if(leftSpd < -0.3){
            leftSpd = -0.3;
            System.out.println("capping speed");
        }
        rightSpd = leftSpd;
        if(!(detector == 1)){
            leftSpd = 0;
            rightSpd = 0;
            System.out.print("nothing detected");
        }

        plugAndChugDrive(leftSpd, rightSpd, leftSpd, rightSpd);
        System.out.println("left speed "+ leftSpd);
        System.out.println("right speed "+ rightSpd);
    }

    public double getX(){
        return tx.getDouble(0);
    }

    public double getY(){
        return ty.getDouble(0);
    }

    public void kidMode(){
        throttle = 0.3;
    }

    public void adultMode(){
        throttle =1;
    }

    // public double getDistanceFromHub(){
    //     double yAngle = ty.getDouble(0);
    //     d = (AUTO_HIGH_GOAL_HEIGHT - AUTO_CAMERA_HEIGHT) / tan(toRadians(yAngle + AUTO_CAMERA_ANGLE));
    //     return d;
    // }

    public void AdjustDistance(){
        
        DistanceDetectionAverage();
        double dectector = tv.getDouble(0);
        if(dectector == 1){
        /**d = (AUTO_HIGH_GOAL_HEIGHT - AUTO_CAMERA_HEIGHT) / tan(toRadians(yangle + AUTO_CAMERA_ANGLE));
       
            leftSpd = (d-HUB_DISTANCE)*hD;
            rightSpd = -(d - HUB_DISTANCE)*hD;
            */
            double yangle = ty.getDouble(0); 

            leftSpd = -(TARGET_ANGLE - yangle)*hD;
            if(leftSpd > 0.3){
                leftSpd = 0.3;
                System.out.println("adjust dist capping speed");
            }
            if(leftSpd<-0.3){
                leftSpd =-0.3;
                System.out.println("adjust dist capping speed");
            }
            rightSpd = -leftSpd;
        }
        else{
            leftSpd = 0;
            rightSpd = 0;
            System.out.println("adjust distance stopped");
        }
        plugAndChugDrive(leftSpd, rightSpd, leftSpd, rightSpd);
        
    }
}
