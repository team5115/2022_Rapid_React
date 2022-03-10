package frc.team5115.Commands.Shooter;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team5115.Subsystems.*;

public class DelayShootGroup extends SequentialCommandGroup{
    Intake intake;
    Feeder feeder;
    Shooter shooter;

    public DelayShootGroup(Intake a, Feeder b, Shooter c){
        intake = a;
        feeder = b;
        shooter = c;

        addCommands(
            //go to intake ball
            new ReverseFeeder(feeder),
            new Shoot(shooter),
            new AutoShoot(intake, feeder, shooter)
        );
    }
}