package frc.team5115.Commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team5115.Subsystems.Drivetrain;
import frc.team5115.Subsystems.Intake;
import edu.wpi.first.wpilibj.Timer;

public class AutoDrive extends CommandBase {
    Drivetrain drivetrain;
    Timer timer;

    public AutoDrive(Drivetrain drivetrain){
        this.drivetrain = drivetrain;
        timer = new Timer();

    }
    
    public void initialize() {
        timer.reset();
        timer.start();
    }

    public void execute(){
        drivetrain.autodrive();
    }

    public void end (boolean interupted){
        drivetrain.stop();

    }
    
    public boolean isFinished() {
        if(timer.get()>5){
            return true;
        }
            return false;
        //return drivetrain.autoDriveFinished();
      }
}
