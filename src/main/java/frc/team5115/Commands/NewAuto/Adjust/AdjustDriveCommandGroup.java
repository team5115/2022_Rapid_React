package frc.team5115.Commands.NewAuto.Adjust;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team5115.Subsystems.Limelight.*;
import frc.team5115.Subsystems.Drivetrain.*;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.team5115.Subsystems.*;
import static frc.team5115.Constants.*;
import frc.team5115.Robot.*;

public class AdjustDriveCommandGroup extends SequentialCommandGroup {
        Limelight limelight;
        Drivetrain drivetrain;
        Camera camera;

    

    public AdjustDriveCommandGroup(Drivetrain drivetrain, Camera camera){
        this.drivetrain = drivetrain;
        addCommands(
        //Adjusts Angle
        new AdjustAngle(drivetrain),

       new AdjustDistance(drivetrain, camera)

     //   new Stop(drivetrain)
        );
        
}
}
