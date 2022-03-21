package frc.team5115.Commands.Subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import frc.team5115.Subsystems.*;

public class ReverseFeeder extends CommandBase{
    Feeder feeder;
    Intake intake;
    Timer timer;

    public ReverseFeeder(Intake a, Feeder b){
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
        intake.reverseIntake(-.25);
    }

    public boolean isFinished() {   
        if(timer.get()>.25){
            feeder.stop();
            intake.stop();
            return true;
        }
        else{
            return false;
        }
        
    }


}
