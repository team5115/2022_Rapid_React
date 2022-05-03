package frc.team5115.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.team5115.Constants.*;
import edu.wpi.first.math.controller.PIDController;


public class Shooter {
    private TalonSRX leftShooter;
    private TalonSRX rightShooter;
    private double shootSpeed = 0.7;
    public double kP = 0;
    public double kI = 0;
    public double KD = 0;
    
    //base line speed = .75

    public Shooter(){
        leftShooter = new TalonSRX(LEFT_SHOOTER_MOTOR_ID);
        PIDController pid = new PIDController(kP, kI, KD);
        rightShooter = new TalonSRX(RIGHT_SHOOTER_MOTOR_ID);
    }

    public void forwardShoot(Double speed){
        leftShooter.set(ControlMode.PercentOutput, speed);
        rightShooter.set(ControlMode.PercentOutput, -speed);
    }

    public void stop(){
        leftShooter.set(ControlMode.PercentOutput, 0);
        rightShooter.set(ControlMode.PercentOutput, 0);
    }
    public void PidMove(double kP, double kI, double KD){
    PIDController pid = new PIDController(kP, kI, kD);

    }
}
