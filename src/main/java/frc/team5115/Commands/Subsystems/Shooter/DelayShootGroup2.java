package frc.team5115.Commands.Subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team5115.Commands.Subsystems.Shooter.ReverseFeederAuto;
import frc.team5115.Commands.Subsystems.Shooter.Shoot;
import frc.team5115.Subsystems.*;

public class DelayShootGroup2 extends SequentialCommandGroup{
    Intake intake;
    Feeder feeder;
    Shooter shooter;

    public DelayShootGroup2(Intake a, Feeder b, Shooter c){
        intake = a;
        feeder = b;
        shooter = c;

        addCommands(
            //go to intake ball
            new ReverseFeederAuto(intake, feeder),
            new Shoot(shooter),
            new SecondShoot1(intake, feeder, shooter),
            new SecondShoot2(intake, feeder, shooter)
        );
    }
}