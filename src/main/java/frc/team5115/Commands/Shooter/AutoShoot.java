package frc.team5115.Commands.Shooter;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team5115.Subsystems.Intake;
import frc.team5115.Subsystems.*;
import edu.wpi.first.wpilibj.Timer;

public class AutoShoot extends CommandBase{
    public Intake intake;
    public Feeder feeder;
    public Shooter shooter;
    public Timer timer;
    
    public AutoShoot(Intake a, Feeder b, Shooter c){
        timer = new Timer();
       intake = a;
       feeder = b;
       shooter = c;
    }

    public void initialize() {
        timer.reset();
        timer.start();
    }


    public void execute() {
        shooter.forwardShoot();
        feeder.forwardFeeder();
        intake.forwardIntake();
        System.out.println("all shoot");
    }

    public void end(boolean interupted){
        shooter.stop();
        intake.stop();
        feeder.stop();
    }
    public boolean isFinished() {
        if(timer.get()>2){
            return true;
        }
        return false;
    }
    
}
