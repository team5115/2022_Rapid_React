package frc.team5115.Commands.NewAuto.BallFinder;

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
import edu.wpi.first.wpilibj.Timer;


public class AdjustDistanceToBall extends CommandBase{

    Drivetrain drivetrain;
    Intake intake;
    Timer timer;
    public AdjustDistanceToBall(Drivetrain drivetrain, Intake intake){
        this.drivetrain = drivetrain;
        this.intake = intake;
        timer = new Timer();
    }


    @Override
        public void initialize(){
            timer.reset();
            timer.start();
        }

    @Override
        public void execute() {
            intake.forwardIntake();
            drivetrain.autodrive();

           // System.out.println("driving to ball ty is: " + drivetrain.getY());
            
        }

        @Override 
        public void end(boolean interupted){
            drivetrain.stop();
            intake.stop();
        }
    @Override
        public boolean isFinished() {
               /* if(drivetrain.backRightEncoder > 200){
                    System.out.println("stop drive and intake");
                    return true;
                    
                }
                return false;
                */
                if(timer.get()>3){
                    return true;
                }
                else{
                    return false;
                }
            }
    
}