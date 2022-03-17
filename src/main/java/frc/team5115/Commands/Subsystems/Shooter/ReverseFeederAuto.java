package frc.team5115.Commands.Subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import frc.team5115.Subsystems.*;

public class ReverseFeederAuto extends CommandBase{
    Feeder feeder;
    Timer timer;
    Intake intake;

    public ReverseFeederAuto(Intake a, Feeder b){
        intake = a;
        feeder = b;
        timer = new Timer();
    }

    public void initialize() {
        timer.reset();
        timer.start();
    }

    public void execute() {
        feeder.reverseFeeder();
        intake.preventSliding();
    }

    public void end(boolean interupted){
        intake.stop();
        feeder.stop();
    }
    public boolean isFinished() {   
        if(timer.get()>.25){
            return true;
        }
        else{
            return false;
        }
        
    }

}