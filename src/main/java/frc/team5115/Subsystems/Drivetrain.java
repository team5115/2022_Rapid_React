package frc.team5115.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team5115.Robot.RobotContainer;

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

    private double rightSpd;
    private double leftSpd;


    public Drivetrain(RobotContainer x) {
        frontLeft = new TalonSRX(FRONT_LEFT_MOTOR_ID);
        frontRight = new TalonSRX(FRONT_RIGHT_MOTOR_ID);
        backLeft = new TalonSRX(BACK_LEFT_MOTOR_ID);
        backRight = new TalonSRX(BACK_RIGHT_MOTOR_ID);

        frontLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        frontRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        backLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        backRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    }

    public void stop() {
        drive(0, 0, 0);
    }

    public void drive(double x, double y, double throttle) { 
        System.out.println("x = " + x);
        System.out.println("y = " + y);
        leftSpd = (x-y) * throttle;
        rightSpd = (x+y) * throttle;
        System.out.println("Setting Right Pair to :" + (int) rightSpd * 100);
        System.out.println("Setting Left Pair to :" + (int) leftSpd * 100);

        frontLeft.set(ControlMode.PercentOutput, leftSpd);
        frontRight.set(ControlMode.PercentOutput, rightSpd);
        backLeft.set(ControlMode.PercentOutput, leftSpd);
        backRight.set(ControlMode.PercentOutput, rightSpd);
    }
    public void simpleDrive(double y, double x, double z) {
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

    public void autodrive(){
        System.out.println("STARTING AUTO DRIVE");
        frontLeft.set(ControlMode.PercentOutput, 0.5);
        frontRight.set(ControlMode.PercentOutput, 0.5);
        backLeft.set(ControlMode.PercentOutput, 0.5);
        backRight.set(ControlMode.PercentOutput, 0.5);
    }

    public void XBoxDrive(Joystick joy) {
        double x = joy.getRawAxis(XBOX_X_AXIS_ID);
        double y = -joy.getRawAxis(XBOX_Y_AXIS_ID);

        double throttle1 = joy.getRawAxis(XBOX_THROTTLE_1_ID);
        double throttle2 = joy.getRawAxis(XBOX_THROTTLE_2_ID);

        //throttle is between 0.5 and 1
        double throttle = (1 - throttle1) + (1 - throttle2);
        throttle /= 2;
        //throttle is between 0 (dont move) and 1, (full move)
        throttle = ((1 - MIN_XBOX_THROTTLE) * throttle) + MIN_XBOX_THROTTLE;
    
    }

}


