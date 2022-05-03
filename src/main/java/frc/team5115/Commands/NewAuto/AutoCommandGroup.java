package frc.team5115.Commands.NewAuto;
import frc.team5115.Commands.Subsystems.Shooter.Auto.AutoShoot;
import frc.team5115.Commands.Subsystems.Shooter.Auto.DelayShootGroupAuto;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team5115.Commands.Stopeverything;
import frc.team5115.Commands.NewAuto.Adjust.AdjustDriveCommandGroup;
import frc.team5115.Commands.NewAuto.BallFinder.AdjustAngleToSecondBall;
import frc.team5115.Commands.NewAuto.BallFinder.AdjustDistanceToBall;
import frc.team5115.Subsystems.*;
import frc.team5115.Commands.NewAuto.Adjust.AdjustDriveCommandGroup;


public class AutoCommandGroup extends SequentialCommandGroup {
  Drivetrain drivetrain;
  Limelight limelight;
  Intake intake;
  Feeder feeder;
  Shooter shooter;
  Camera camera;

  public AutoCommandGroup(Intake a, Feeder b, Shooter c, Drivetrain drivetrain, Camera camera){
      intake = a;
      feeder = b;
      this.camera = camera;
      shooter = c;
      this.drivetrain = drivetrain;
      addCommands(
          //shoot preloaded ball
          new AdjustDistanceToBall(drivetrain, intake),
          new AdjustDriveCommandGroup(drivetrain, camera),
          //Substitute for limelight code
          //new DriveToPoint(drivetrain),
          new DelayShootGroupAuto(intake, feeder, shooter)
          //new AdjustAngleToSecondBall(drivetrain),
          //new AdjustDistanceToBall(drivetrain, intake),
          //new AdjustDriveCommandGroup(drivetrain, camera),
          //new DelayShootGroupAuto(intake, feeder, shooter)
          
          );
          
    }

}
