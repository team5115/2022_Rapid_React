package frc.team5115.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.team5115.Constants.*;

public class Intake extends SubsystemBase{
    private TalonSRX intake;
    private double intakeSpeed = .6;

    public Intake(){
        intake = new TalonSRX(INTAKE_MOTOR_ID);    
    }

    public void forwardIntake(){
        intake.set(ControlMode.PercentOutput, intakeSpeed);
    }

    public void reverseIntake(double reverseSpeed){
        intake.set(ControlMode.PercentOutput, reverseSpeed);
    }

    public void preventSliding(){
        intake.set(ControlMode.PercentOutput, 0.1);
    }

    public void stop(){
        intake.set(ControlMode.PercentOutput, 0);
    }
    
}
