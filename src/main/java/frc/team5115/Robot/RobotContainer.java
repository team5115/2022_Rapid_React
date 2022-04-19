package frc.team5115.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.team5115.Subsystems.*;
import static frc.team5115.Constants.*;

import frc.team5115.Commands.Subsystems.Limelight.Update;
import frc.team5115.Commands.Subsystems.Shooter.DelayShootGroup1;
import frc.team5115.Commands.Subsystems.Shooter.ReverseFeeder;
import frc.team5115.Commands.Subsystems.Shooter.Auto.DelayShootGroupAuto;
import frc.team5115.Commands.Subsystems.Climber.*;
import frc.team5115.Commands.*;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.team5115.Commands.NewAuto.AutoCommandGroup;
import frc.team5115.Commands.NewAuto.Adjust.AdjustDistance;
import frc.team5115.Commands.NewAuto.Adjust.AdjustDriveCommandGroup;

public class RobotContainer {

    public Drivetrain drivetrain;
    public Pneumatics pneumatics;
    public Intake intake;
    public Shooter shooter;
    public Feeder feeder;
    public Climber climber;
    public final Joystick joy = new Joystick(0);
    public final Limelight limelight = new Limelight();
    public Camera camera; 
    public Timer timer;
    public AutoCommandGroup autocommandgroup;

    public RobotContainer() {
        drivetrain = new Drivetrain();
        intake = new Intake();

        shooter = new Shooter();
        feeder = new Feeder();
        climber = new Climber();
        pneumatics = new Pneumatics();
        camera = new Camera();

        autocommandgroup = new AutoCommandGroup(intake, feeder, shooter, drivetrain, camera);
        timer = new Timer();
        timer.reset();
        configureButtonBindings();
    }

    public void configureButtonBindings() {
        new JoystickButton( joy, INTAKE_BUTTON_ID).whileHeld(new InstantCommand(intake::forwardIntake)).whenReleased(new InstantCommand(intake::stop));
        //new JoystickButton(joy, SHOOTER_BUTTON_ID).whileHeld(new DelayShootGroupAuto(intake, feeder, shooter)).whenReleased(new Stopeverything(intake, feeder, shooter));
        new JoystickButton(joy, SHOOTER_BUTTON_ID).whileHeld(new DelayShootGroup1(intake, feeder, shooter)).whenReleased(new Stopeverything(intake, feeder, shooter));
        
      /*  new JoystickButton(joy, LEFT_CLIMBER_UP_BUTTON_ID).whileHeld(new LeftForwardClimb(climber)).whenReleased(new InstantCommand(climber::leftStop));
        new JoystickButton(joy, RIGHT_CLIMBER_UP_BUTTON_ID).whileHeld(new RightForwardClimb(climber)).whenReleased(new InstantCommand(climber::rightStop));
        new JoystickButton(joy, LEFT_CLIMBER_DOWN_BUTTON_ID).whileHeld(new LeftReverseClimb(climber)).whenReleased(new InstantCommand(climber::leftStop));
        new JoystickButton(joy, RIGHT_CLIMBER_DOWN_BUTTON_ID).whileHeld(new RightReverseClimb(climber)).whenReleased(new InstantCommand(climber::rightStop));
      */
        new JoystickButton(joy, LEFT_CLIMBER_UP_BUTTON_ID).whileHeld(new InstantCommand(climber::leftForwardClimb)).whenReleased(new InstantCommand(climber::leftStop));
        new JoystickButton(joy, RIGHT_CLIMBER_UP_BUTTON_ID).whileHeld(new InstantCommand(climber::rightForwardClimb)).whenReleased(new InstantCommand(climber::rightStop));
        new JoystickButton(joy, LEFT_CLIMBER_DOWN_BUTTON_ID).whileHeld(new InstantCommand(climber::leftReverseClimb)).whenReleased(new InstantCommand(climber::leftStop));
        new JoystickButton(joy, RIGHT_CLIMBER_DOWN_BUTTON_ID).whileHeld(new InstantCommand(climber::rightReverseClimb)).whenReleased(new InstantCommand(climber::rightStop));
        //new JoystickButton(joy, 7).whileHeld(new InstantCommand(intake::reverseIntake(-.25)).alongWith(new InstantCommand(feeder::reverseFeeder))).whenReleased(new Stopeverything(intake, feeder, shooter));
        //new JoystickButton(joy, 8).whenPressed(new ReverseFeeder(intake, feeder));

        new JoystickButton(joy, 1).whenPressed(new InstantCommand(pneumatics::forward)).whenReleased(new InstantCommand(pneumatics::stop));
        new JoystickButton(joy, 2).whenPressed(new InstantCommand(pneumatics::backward)).whenReleased(new InstantCommand(pneumatics::stop));

        new JoystickButton(joy, 9).whileHeld(new InstantCommand(drivetrain::oliviaMode)).whenReleased(new InstantCommand(drivetrain::adultMode));
   
        new JoystickButton(joy, 10).whenPressed(new AdjustDriveCommandGroup(drivetrain, camera));

    }

    public void configureLimelight(){
      drivetrain.setDefaultCommand(new Update(limelight).perpetually());}

    public void setDriveDefault(){
        drivetrain.setDefaultCommand(new driveDefaultCommand(drivetrain, joy).perpetually());
    }

   static class driveDefaultCommand extends CommandBase {
        Drivetrain drivetrain;
        Joystick joy;
        Camera camera;

        public driveDefaultCommand(Drivetrain drivetrain, Joystick joystick) {
            addRequirements(drivetrain);
            this.drivetrain = drivetrain;
            joy = joystick;
          
        }

        @Override
        public void execute() {
           drivetrain.MecanumSimpleDrive(joy.getRawAxis(4), joy.getRawAxis(1), joy.getRawAxis(0), joy.getRawAxis(2));
           drivetrain.DistanceDetectionAverage();
     
        }    
    }


    public void startTeleop(){
        System.out.println("Starting teleop");
        new Stopeverything(intake, feeder, shooter);
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(0);
        //drivetrain.resetEncoder();
        if (autocommandgroup != null) {
            autocommandgroup.cancel();
          }
  
    }

    public void startAuto(){
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(1);
        //drivetrain.resetEncoder();
        if (autocommandgroup != null) {
            autocommandgroup.schedule();
          }
        
    }

    public void autoPeriod(){
      //  drivetrain.printEncoderDistance();
       // drivetrain.DistanceDetectionAverage();

        }
    public void teleopPeriodic(){
        //climber.print();
    }

    }
