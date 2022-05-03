package frc.team5115.Commands.Subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team5115.Subsystems.Intake;
import frc.team5115.Subsystems.*;
import edu.wpi.first.wpilibj.Timer;

public class AllShoot extends CommandBase{
    public Intake intake;
    public Feeder feeder;
    public Shooter shooter;
    public Timer timer;
    
    public AllShoot(Intake a, Feeder b, Shooter c){
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
        shooter.forwardShoot(.65);
        feeder.forwardFeeder();
        intake.forwardIntake();
        System.out.println("all shoot");
    }

    public boolean isFinished() {
        return false;
        
    }
    
}
