package frc.team5115.Commands.Auto.NewAuto;
//import frc.team5115.Commands.Intake.*;
import frc.team5115.Commands.Shooter.AutoShoot;
import frc.team5115.Commands.Shooter.DelayShootGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team5115.Commands.Stopeverything;
import frc.team5115.Commands.AutoDrive;
import frc.team5115.Commands.Auto.NewAuto.Adjust.AdjustDriveCommandGroup;
import frc.team5115.Commands.Auto.NewAuto.AdjustSubsitute.DriveToPoint;
import frc.team5115.Commands.Auto.NewAuto.BallFinder.AdjustRobotToBallCommandGroup;
import frc.team5115.Subsystems.*;


public class AutoCommandGroup extends SequentialCommandGroup {
    Drivetrain drivetrain;
    Limelight limelight;
    Intake intake;
    Feeder feeder;
    Shooter shooter;

    public AutoCommandGroup(Intake a, Feeder b, Shooter c, Drivetrain drivetrain){
        intake = a;
        feeder = b;
        shooter = c;
        addCommands(
            //shoot preloaded ball
            new AdjustRobotToBallCommandGroup(drivetrain, intake),
            //new AdjustDriveCommandGroup(drivetrain),
            //Substitute for limelight code
            new DriveToPoint(drivetrain),
            new DelayShootGroup(intake, feeder, shooter)
        );
    }

}
