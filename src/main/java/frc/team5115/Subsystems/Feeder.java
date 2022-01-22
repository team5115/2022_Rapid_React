package frc.team5115.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.team5115.Constants.*;

public class Feeder extends SubsystemBase{
    private TalonSRX feeder;
    private double feederSpeed = 0.5;

    public Feeder(){
        feeder = new TalonSRX(FEEDER_MOTOR_ID);
    }

    public void forwardFeeder(){
        feeder.set(ControlMode.PercentOutput, feederSpeed);
    }

    public void reverseFeeder(){
        feeder.set(ControlMode.PercentOutput, -feederSpeed);
    }

    public void stop(){
        feeder.set(ControlMode.PercentOutput, 0);
    }
}
