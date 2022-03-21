package frc.team5115.Commands.Subsystems.Shooter.Auto;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team5115.Commands.Subsystems.Shooter.ReverseFeeder;
import frc.team5115.Commands.Subsystems.Shooter.ReverseFeederAuto;
import frc.team5115.Commands.Subsystems.Shooter.Shoot;
import frc.team5115.Subsystems.*;

public class DelayShootGroupAuto extends SequentialCommandGroup{
    Intake intake;
    Feeder feeder;
    Shooter shooter;

    public DelayShootGroupAuto(Intake a, Feeder b, Shooter c){
        intake = a;
        feeder = b;
        shooter = c;

        addCommands(
            //go to intake ball
            new ReverseFeederAuto(intake, feeder),
            new Shoot(shooter),
            new AutoShoot(intake, feeder, shooter),
            new AutoShoot2(intake, feeder, shooter)
        );
    }
}