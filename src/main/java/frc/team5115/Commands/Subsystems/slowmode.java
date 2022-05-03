package frc.team5115.Commands.Subsystems;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team5115.Subsystems.*;

public class slowmode extends CommandBase{
    Drivetrain drivetrain;

    public slowmode(Drivetrain x){
        drivetrain = x;
        }

        public void initialize() {
            drivetrain.oliviaMode();
        }

        public void end(){
            drivetrain.adultMode();
            System.out.println("speedy mode");
        }
}
