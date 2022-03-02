package frc.team5115.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.team5115.Constants.*;

public class Shooter {
    private TalonSRX leftShooter;
    private TalonSRX rightShooter;
    private double shootSpeed = 0.75;
    //base line speed = .75

    public Shooter(){
        leftShooter = new TalonSRX(1);
        rightShooter = new TalonSRX(2);
    }

    public void forwardShoot(){
        leftShooter.set(ControlMode.PercentOutput, shootSpeed);
        rightShooter.set(ControlMode.PercentOutput, -shootSpeed);
    }

    public void stop(){
        leftShooter.set(ControlMode.PercentOutput, 0);
        rightShooter.set(ControlMode.PercentOutput, 0);
    }

}
