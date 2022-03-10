package frc.team5115.Commands.Shooter;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team5115.Subsystems.Intake;
import frc.team5115.Subsystems.*;
import edu.wpi.first.wpilibj.Timer;

public class Shoot extends CommandBase{
    public Shooter shooter;
    public Timer timer;

    public Shoot(Shooter c){
       shooter = c;
       timer = new Timer();
    }

    public void initialize() {
       timer.reset();
       timer.start();
    }

    public void execute() {
        shooter.forwardShoot();
    }

    public boolean isFinished() {
        if(timer.get()<.75){
        return false;
        }
        else{
            return true;
        }
    }
    
}
