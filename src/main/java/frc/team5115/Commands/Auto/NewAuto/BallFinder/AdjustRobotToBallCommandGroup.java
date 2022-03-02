package frc.team5115.Commands.Auto.NewAuto.BallFinder;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team5115.Subsystems.Limelight.*;
import frc.team5115.Subsystems.Drivetrain.*;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.team5115.Subsystems.*;
import frc.team5115.Commands.Auto.NewAuto.Adjust.AdjustDistance;
import frc.team5115.Commands.Auto.NewAuto.Adjust.AdjustDriveCommandGroup;
import frc.team5115.Commands.Auto.NewAuto.Adjust.Stop;
import frc.team5115.Commands.Auto.NewAuto.BallFinder.AdjustRobotToBallCommandGroup;
import frc.team5115.Subsystems.*;
import static frc.team5115.Constants.*;
import frc.team5115.Robot.*;

public class AdjustRobotToBallCommandGroup extends SequentialCommandGroup {
        Drivetrain drivetrain;
    public AdjustRobotToBallCommandGroup(Drivetrain drivetrain){
        this.drivetrain = drivetrain;
        addCommands(
            new AdjustAngleToBall(drivetrain),
            new AdjustDistanceToBall(drivetrain),
            new Stop(drivetrain))
        ;

    }
}
