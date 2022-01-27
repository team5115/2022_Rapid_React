package frc.team5115.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team5115.Robot.RobotContainer;
import edu.wpi.first.wpilibj.Encoder; 

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

    private AHRS gyro;
    private Encoder encoder;
    


    public Drivetrain() {
        frontLeft = new TalonSRX(FRONT_LEFT_MOTOR_ID);
        frontRight = new TalonSRX(FRONT_RIGHT_MOTOR_ID);
        backLeft = new TalonSRX(BACK_LEFT_MOTOR_ID);
        backRight = new TalonSRX(BACK_RIGHT_MOTOR_ID);

        gyro= new AHRS(SPI.Port.kMXP);
        encoder = new Encoder(0,1);
        encoder.reset();
        gyro.resetDisplacement();
        
        frontLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        frontRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        backLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        backRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    }

    public void stop() {
        TankDrive(0, 0, 0);
    }

    public void TankDrive(double x, double y, double throttle) { 
        leftSpd = (x-y) * throttle;
        rightSpd = (x+y) * throttle;

        frontLeft.set(ControlMode.PercentOutput, leftSpd);
        frontRight.set(ControlMode.PercentOutput, rightSpd);
        backLeft.set(ControlMode.PercentOutput, leftSpd);
        backRight.set(ControlMode.PercentOutput, rightSpd);
    }
    public void MecanumSimpleDrive(double y, double x, double z) {
    
        frontLeftSpeed = (-x + y + z);
        backLeftSpeed = (-x + y - z);
        frontRightSpeed = (x +  y + z);
        backRightSpeed = (x + y - z);

       /*
        frontLeftSpeed = (-y + x + z);
        backLeftSpeed = (-y + x - z);
        frontRightSpeed = (y +  x + z);
        backRightSpeed = (y + x - z);*/
        
        frontLeft.set(ControlMode.PercentOutput, frontLeftSpeed);
        frontRight.set(ControlMode.PercentOutput, frontRightSpeed);
        backLeft.set(ControlMode.PercentOutput, backLeftSpeed);
        backRight.set(ControlMode.PercentOutput, backRightSpeed);
    }

    public void FieldOrientedDrive(double strafe, double fwd, double rotate){
        double x;
        double y;
        double pi = 3.1415926;
        float gyro_degrees = gyro.getYaw();
        double gyro_radians = gyro_degrees * pi/180; 
  //      y = fwd * Math.cos(gyro_radians) + strafe * Math.sin(gyro_radians);
  //      x = -fwd * Math.sin(gyro_radians) +  strafe * Math.cos(gyro_radians);

        x = strafe*Math.cos(gyro_radians) + fwd*Math.sin(gyro_radians);
        y = strafe*Math.sin(gyro_radians) - fwd*Math.cos(gyro_radians);

        frontLeftSpeed = (y + x + rotate);
        backLeftSpeed = (y - x + rotate);
        frontRightSpeed = (-y + x + rotate);
        backRightSpeed = (-y - x + rotate);
/*

        frontLeftSpeed = (-y + x);
        backLeftSpeed = (-y + x);
        frontRightSpeed = (y + x);
        backRightSpeed = (y + x);
       
        System.out.println(gyro.getYaw());
        */
        frontLeft.set(ControlMode.PercentOutput, frontLeftSpeed);
        frontRight.set(ControlMode.PercentOutput, frontRightSpeed);
        backLeft.set(ControlMode.PercentOutput, backLeftSpeed);
        backRight.set(ControlMode.PercentOutput, backRightSpeed);
        
    }

    public void autodrive(){
        System.out.println("STARTING AUTO DRIVE");
        frontLeft.set(ControlMode.PercentOutput, 0.5);
        frontRight.set(ControlMode.PercentOutput, 0.5);
        backLeft.set(ControlMode.PercentOutput, 0.5);
        backRight.set(ControlMode.PercentOutput, 0.5);
    }

    public void getDistance(){
        Systme.
        //System.out.println(100*gyro.getDisplacementX());
    }

    
}


