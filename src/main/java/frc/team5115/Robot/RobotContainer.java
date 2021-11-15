package frc.team5115.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.team5115.Commands.Auto.DriveForward;
import frc.team5115.Subsystems.*;
import static frc.team5115.Constants.*;
import frc.team5115.Robot.*;

public class RobotContainer {

    // The robot's subsystems and commands are defined here...
    //Subsystems
    public Drivetrain drivetrain;
    public final Shooter shooter = new Shooter();
    public final Intake intake = new Intake();
    public final Climber climber = new Climber();
    public final Feeder feeder = new Feeder();
    public final DriveForward DriveForward = new DriveForward(drivetrain);
    public final Joystick joy = new Joystick(0);
   // public final Mecanum cart = new Mecanum();
  

    public RobotContainer() {
        // Configure the button bindings
        //sets the navx to work.
        drivetrain = new Drivetrain(this);
        configureButtonBindings();
    }

    private void configureButtonBindings() {

        new JoystickButton(joy, SHOOTER_BUTTON_ID).whenHeld(new InstantCommand(shooter::shoot)).whenReleased(new InstantCommand(shooter::stopShoot));
        new JoystickButton(joy, WINCH_BUTTON_ID).whenHeld(new InstantCommand(climber::StartWinch)).whenReleased(new InstantCommand(climber::StopClimb));
        new JoystickButton(joy, INTAKE_BUTTON_ID).whenHeld(new InstantCommand(intake::driverIntake).alongWith(new InstantCommand(feeder::moveCells))).whenReleased(new InstantCommand(intake::stopIntake).alongWith(new InstantCommand(feeder::stopCells)));
        new JoystickButton(joy, INTAKE_REVERSE_ID).whenHeld(new InstantCommand(intake::spitout).alongWith(new InstantCommand(feeder::spit))).whenReleased(new InstantCommand(intake::stopIntake).alongWith(new InstantCommand(feeder::stopCells)));
       
        //cart.setDefaultCommand(new mecanumDefaultCommand(cart, joy).perpetually());
        drivetrain.setDefaultCommand(new driveDefaultCommand(drivetrain, joy).perpetually());
    }

   static class driveDefaultCommand extends CommandBase {

        Drivetrain drivetrain;
        Joystick joystick;

        public driveDefaultCommand(Drivetrain drivetrain, Joystick joystick) {
            addRequirements(drivetrain);
            this.drivetrain = drivetrain;
            this.joystick = joystick;
        }

        @Override
        public void execute() {
           drivetrain.simpleDrive(joystick.getRawAxis(XBOX_X_AXIS_ID), joystick.getRawAxis(XBOX_Y_AXIS_ID), joystick.getRawAxis(XBOX_Z_AXIS_ID));
            //drivetrain.drive(-.5, -.5, 1);
        }
    }
/*
    static class mecanumDefaultCommand extends CommandBase {

        Mecanum cart;
        Joystick joystick;
        double gyroangle = 0;

        public mecanumDefaultCommand(Mecanum cart, Joystick joystick) {
            addRequirements(cart);
            this.cart = cart;
            this.joystick = joystick;
        }

        @Override
        public void execute() {
           cart.simpleDrive(joystick.getRawAxis(XBOX_X_AXIS_ID), joystick.getRawAxis(XBOX_Y_AXIS_ID), joystick.getRawAxis(XBOX_Z_AXIS_ID));
            //drivetrain.drive(-.5, -.5, 1);
        }
    }
    */

    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return DriveForward;
    }
    public void startTeleop(){
            //bind the wheels.
            System.out.println("Starting teleop");
        }

}