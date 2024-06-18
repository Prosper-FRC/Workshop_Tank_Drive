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
  private CANSparkMax frontLeft;
  private CANSparkMax frontRight;
  private CANSparkMax backLeft;
  private CANSparkMax backRight;

  private DifferentialDrive drive;

  public DriveTrainSubsystem() {

    frontLeft = new CANSparkMax(DriveConstants.FRONT_LEFT_ID, MotorType.kBrushless);
    frontRight = new CANSparkMax(DriveConstants.FRONT_RIGHT_ID, MotorType.kBrushless);
    backLeft = new CANSparkMax(DriveConstants.BACK_LEFT_ID, MotorType.kBrushless);
    backRight = new CANSparkMax(DriveConstants.FRONT_RIGHT_ID, MotorType.kBrushless);

    backLeft.follow(frontLeft);
    backRight.follow(frontRight);

    // Are both wheels supposed to be inverted? Test and find out, because they were for ladybug
    frontRight.setInverted(true);
    backRight.setInverted(true);

    drive = new DifferentialDrive(frontLeft, frontRight);


    configureMotors(frontLeft);
    configureMotors(frontRight);
    configureMotors(backLeft);
    configureMotors(backRight);
  }

  public void arcadeDrive(double speed, double theta) {
    drive.arcadeDrive(speed, theta);
  }

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
