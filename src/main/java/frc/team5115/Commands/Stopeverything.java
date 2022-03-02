package frc.team5115.Commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team5115.Subsystems.*;


public class Stopeverything extends CommandBase{
    public Intake intake;
    public Feeder feeder;
    public Shooter shooter;

    public Stopeverything(Intake a, Feeder b, Shooter c){
        intake = a;
        feeder = b;
        shooter = c;
    }
    public void initialize() {
        shooter.stop();
        intake.stop();
        feeder.stop();
    }

    

    @Override
    public boolean isFinished() {
        return true;
      }


}
