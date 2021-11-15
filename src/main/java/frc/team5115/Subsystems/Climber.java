package frc.team5115.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team5115.Commands.StopClimb;
import static frc.team5115.Constants.WINCH_MOTOR_ID;

public class Climber extends SubsystemBase {
    TalonSRX winch;
    double climbspeed = 0.5;

    public Climber(){
        winch = new TalonSRX(WINCH_MOTOR_ID);
        setDefaultCommand(new StopClimb(this).perpetually());
    }

    public void StartWinch(){
        winch.set(ControlMode.PercentOutput, climbspeed);
    }

    public void StopClimb() {
        winch.set(ControlMode.PercentOutput, 0);
    }

}
