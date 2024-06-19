// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrainSubsystem;

public class DriveCommand extends Command {
  /** Creates a new DriveCommand. */

  // Initialize DoubleSuppliers speed and theta as well as the DriveTrainSubsystem
  private DoubleSupplier speed;
  private DoubleSupplier theta;
  private DriveTrainSubsystem drive;

  public DriveCommand(DoubleSupplier speed, DoubleSupplier theta, DriveTrainSubsystem drive) {

    // Set the values of the empty containers we made outside of the constructor to the values we recieve 
    // when the DriveCommand is instantiated somewhere
    this.speed = speed;
    this.theta = theta;
    this.drive = drive;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    // Execute command runs periodically - here, you're calling the method arcadeDrive
    // from DriveTrainSubsystem
    drive.arcadeDrive(speed.getAsDouble(), theta.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

}
