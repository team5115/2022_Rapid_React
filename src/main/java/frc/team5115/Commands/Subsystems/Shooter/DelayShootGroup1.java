package frc.team5115.Commands.Subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team5115.Subsystems.*;

public class DelayShootGroup1 extends SequentialCommandGroup{
    Intake intake;
    Feeder feeder;
    Shooter shooter;

    public DelayShootGroup1(Intake a, Feeder b, Shooter c){
        intake = a;
        feeder = b;
        shooter = c;

        addCommands(
            //go to intake ball
            new ReverseFeeder(intake, feeder),
            new Shoot(shooter),
            new AllShoot(intake, feeder, shooter)
        );
    }
}