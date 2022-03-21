package frc.team5115.Commands.NewAuto.Adjust;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team5115.Subsystems.Limelight.*;
import frc.team5115.Subsystems.Drivetrain.*;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.team5115.Subsystems.*;

public class AdjustAngle extends CommandBase {
    Drivetrain drivetrain;

      

    public AdjustAngle(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
    }

    @Override
        public void initialize(){
        }

    @Override
        public void execute() {
            drivetrain.AdjustAngle();
            System.out.print("adjust angle x: " + drivetrain.getX());
        }

    @Override 
        public void end(boolean interputed){
            System.out.println("adjust angle is finished");
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