package frc.team5115.Commands.Auto.NewAuto.Adjust;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team5115.Subsystems.Limelight.*;
import frc.team5115.Subsystems.Drivetrain.*;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.team5115.Subsystems.*;
import static frc.team5115.Constants.*;
import frc.team5115.Robot.*;
import edu.wpi.first.networktables.NetworkTableInstance;


public class AdjustAngle extends CommandBase {
    Drivetrain drivetrain;

      

    public AdjustAngle(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
    }

    @Override
        public void initialize(){
            //NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(1);
        }

    @Override
        public void execute() {
            drivetrain.AdjustAngle();
        }
    @Override
        public boolean isFinished() {
            if(Math.abs(drivetrain.getX()) < 0.5){
                return true;
            }
            else{
                return false;
            }
        }
}