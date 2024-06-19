// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.DriveConstants;
import frc.robot.commands.DriveCommand;

public class DriveTrainSubsystem extends SubsystemBase {
  /** Creates a new DriveTrainSubsystem. */

  // Initializing the four motor controllers, which control how much voltage
  // is given to the motors. We never use the motors directly in our code, only the
  // motor controllers.
  private CANSparkMax frontLeft;
  private CANSparkMax frontRight;
  private CANSparkMax backLeft;
  private CANSparkMax backRight;

  // Initializing our differential drive, which is the type of drivetrain we have 
  // (as opposed to a tank drive).
  private DifferentialDrive drive;

  public DriveTrainSubsystem() {

    // Instantiation of motor controllers.
    frontLeft = new CANSparkMax(DriveConstants.FRONT_LEFT_ID, MotorType.kBrushless);
    frontRight = new CANSparkMax(DriveConstants.FRONT_RIGHT_ID, MotorType.kBrushless);
    backLeft = new CANSparkMax(DriveConstants.BACK_LEFT_ID, MotorType.kBrushless);
    backRight = new CANSparkMax(DriveConstants.FRONT_RIGHT_ID, MotorType.kBrushless);

    // Back motors follow front motors
    backLeft.follow(frontLeft);
    backRight.follow(frontRight);

    // Invert the right side of the drivetrain, as the motors on that side are installed the other way so 
    // their clockwise would not be the same as the clockwise of the motors on the left side of the drivetrain, so to 
    // fix this we invert
    frontRight.setInverted(true);
    backRight.setInverted(true);

    // Instantiate the Differential Drive.
    drive = new DifferentialDrive(frontLeft, frontRight);


    configureMotors(frontLeft);
    configureMotors(frontRight);
    configureMotors(backLeft);
    configureMotors(backRight);
  }

  public void arcadeDrive(double speed, double theta) {
    drive.arcadeDrive(speed, theta);
  }

  // Restore factory defaults, set the idle mode to brake, set the current limit to 40, and burn the configurations into the motor
  public void configureMotors(CANSparkMax motor) {
    motor.restoreFactoryDefaults();
    motor.setIdleMode(IdleMode.kBrake);
    motor.setSmartCurrentLimit(DriveConstants.DRIVE_CURRENT_LIMIT);
    motor.burnFlash();
  }


 /*  Swerve stuff, disregard:
  public SwerveModuleState getState() {
    return new SwerveModuleState();
  }

  public void setDesiredState(SwerveModuleState state) {
    state = SwerveModuleState.optimize(state, getState().angle);
  }*/



  



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
