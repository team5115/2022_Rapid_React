package frc.team5115.Commands.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import frc.team5115.Subsystems.*;

public class ReverseFeeder extends CommandBase{
    Feeder feeder;
    Timer timer;

    public ReverseFeeder(Feeder a){
        feeder =a;
        timer = new Timer();
    }

    public void initialize() {
        timer.reset();
        timer.start();
    }

    public void execute() {
        feeder.reverseFeeder();
    }

    public boolean isFinished() {   
        if(timer.get()>.25){
            feeder.stop();
            return true;
        }
        else{
            return false;
        }
        
    }

}