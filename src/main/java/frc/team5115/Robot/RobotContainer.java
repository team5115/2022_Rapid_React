package frc.team5115.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.team5115.Subsystems.*;
import static frc.team5115.Constants.*;
import frc.team5115.Robot.*;

public class RobotContainer {

    public Drivetrain drivetrain;
    public Intake intake;
    public Shooter shooter;
    public Feeder feeder;
    public Climber climber;
    public final Joystick joy = new Joystick(0);

    public RobotContainer() {
        drivetrain = new Drivetrain();
        intake = new Intake();
        shooter = new Shooter();
        feeder = new Feeder();
        climber = new Climber();
        configureButtonBindings();
    }

    private void configureButtonBindings() {
       new JoystickButton( joy, INTAKE_BUTTON_ID).whileHeld(new InstantCommand(intake::forwardIntake).alongWith(new InstantCommand(feeder::forwardFeeder))).whenReleased(new InstantCommand(intake::stop).alongWith(new InstantCommand(feeder::stop)));
       new JoystickButton(joy, SHOOTER_BUTTON_ID).whileHeld(new InstantCommand(shooter::forwardShoot)).whenReleased(new InstantCommand(shooter::stop));
       new JoystickButton(joy, CLIMBER_BUTTON_ID).whileHeld(new InstantCommand(climber::forwardClimb)).whenReleased(new InstantCommand(climber::stop));

       drivetrain.setDefaultCommand(new driveDefaultCommand(drivetrain, joy).perpetually());  
    }

   static class driveDefaultCommand extends CommandBase {
        Drivetrain drivetrain;
        Joystick joy;

        public driveDefaultCommand(Drivetrain drivetrain, Joystick joystick) {
            addRequirements(drivetrain);
            this.drivetrain = drivetrain;
            joy = joystick;
        }

        @Override
        public void execute() {
            drivetrain.MecanumSimpleDrive(joy.getRawAxis(4), joy.getRawAxis(1), joy.getRawAxis(0));
            drivetrain.getDistance();
           //System.out.println("starting drive train");
           //drivetrain.autodrive();
           //drivetrain.TankDrive(0.3,0.3,1);
           //drivetrain.FieldOrientedDrive(joy.getRawAxis(JOY_X_AXIS_ID), joy.getRawAxis(JOY_Y_AXIS_ID), joy.getRawAxis(JOY_Z_AXIS_ID));
            
        }    
    }


    public void startTeleop(){
            System.out.println("Starting teleop");
        }

}