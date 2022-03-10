package frc.team5115.Robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.team5115.Subsystems.*;
import static frc.team5115.Constants.*;
//import frc.team5115.Commands.Intake.ColorIntakeBall;
//import frc.team5115.Commands.Intake.IntakeBall;
import frc.team5115.Commands.Ultrasonic;
import frc.team5115.Commands.Limelight.Update;
import frc.team5115.Commands.Stopeverything;
import frc.team5115.Commands.Auto.NewAuto.Adjust.AdjustDriveCommandGroup;
import frc.team5115.Commands.Auto.NewAuto.AutoCommandGroup;
import frc.team5115.Commands.*;
import frc.team5115.Commands.Shooter.AutoShoot;
import frc.team5115.Commands.Shooter.DelayShootGroup;
import edu.wpi.first.wpilibj.Timer;

public class RobotContainer {

    public Drivetrain drivetrain;
    public Intake intake;
    public Shooter shooter;
    public Feeder feeder;
    public Climber climber;
    public final Joystick joy = new Joystick(0);
    public final Limelight limelight = new Limelight();
    public Camera camera; 
    public Timer timer;
    public AutoCommandGroup autocommandgroup;
    public Ultrasonic ultrasonic;

    public RobotContainer() {
        drivetrain = new Drivetrain();
        intake = new Intake();
        shooter = new Shooter();
        feeder = new Feeder();
        climber = new Climber();
        camera = new Camera();
        autocommandgroup = new AutoCommandGroup(intake, feeder, shooter, drivetrain);
        ultrasonic = new Ultrasonic(drivetrain);

        timer = new Timer();
        timer.reset();
        configureButtonBindings();
    }

    public void configureButtonBindings() {
        new JoystickButton( joy, INTAKE_BUTTON_ID).whileHeld(new InstantCommand(intake::forwardIntake)).whenReleased(new InstantCommand(intake::stop));
        new JoystickButton(joy, SHOOTER_BUTTON_ID).whileHeld(new AutoShoot(intake,feeder,shooter)).whenReleased(new Stopeverything(intake, feeder, shooter));

        new JoystickButton(joy, LEFT_CLIMBER_UP_BUTTON_ID).whileHeld(new InstantCommand(climber::leftForwardClimb)).whenReleased(new InstantCommand(climber::leftStop));
        new JoystickButton(joy, RIGHT_CLIMBER_UP_BUTTON_ID).whileHeld(new InstantCommand(climber::rightForwardClimb)).whenReleased(new InstantCommand(climber::rightStop));
        new JoystickButton(joy, LEFT_CLIMBER_DOWN_BUTTON_ID).whileHeld(new InstantCommand(climber::leftReverseClimb)).whenReleased(new InstantCommand(climber::leftStop));
        new JoystickButton(joy, RIGHT_CLIMBER_DOWN_BUTTON_ID).whileHeld(new InstantCommand(climber::rightReverseClimb)).whenReleased(new InstantCommand(climber::rightStop));
        
        new JoystickButton(joy, 7).whenPressed(new InstantCommand(camera::setNeutralAngle));


        

   
    }

    public void setUltraDefault(){
        //ultrasonic.schedule();
        
    }


    public void Camera(){
        if(joy.getPOV()>-1){
        if(joy.getPOV() < 46 || joy.getPOV() > 314){
            camera.setShootAngle();
        } 
        if(joy.getPOV() > 134  && joy.getPOV() < 226){
            camera.setClimbAngle();
        } 
    }
    }
    

    public void setDriveDefault(){
        drivetrain.setDefaultCommand(new driveDefaultCommand(drivetrain, joy, intake).perpetually());
    }

   static class driveDefaultCommand extends CommandBase {
        Drivetrain drivetrain;
        Joystick joy;
        Intake intake;

        public driveDefaultCommand(Drivetrain drivetrain, Joystick joystick, Intake intake) {
            addRequirements(drivetrain);
            this.drivetrain = drivetrain;
            joy = joystick;
            this.intake = intake;
        }

        @Override
        public void execute() {
            drivetrain.printEncoderDistance();
            //intake.colorPrint();
           drivetrain.MecanumSimpleDrive(joy.getRawAxis(4), joy.getRawAxis(1), joy.getRawAxis(0));
           //drivetrain.FieldOrientedDrive(joy.getRawAxis(JOY_Z_AXIS_ID), joy.getRawAxis(JOY_Y_AXIS_ID), joy.getRawAxis(JOY_X_AXIS_ID));
            
        }    
    }


    public void startTeleop(){
        System.out.println("Starting teleop");
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(0);
        new Stopeverything(intake, feeder, shooter);
        //camera.setNeutralAngle();
        drivetrain.resetEncoder();
        if (autocommandgroup != null) {
            autocommandgroup.cancel();
          }

    }

    public void startAuto(){

        drivetrain.resetEncoder();
        if (autocommandgroup != null) {
            autocommandgroup.schedule();
          }
          
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(0);
        System.out.println("starting auto :)");
    }

    public void autoPeriod(){
        drivetrain.printEncoderDistance();
    }

}