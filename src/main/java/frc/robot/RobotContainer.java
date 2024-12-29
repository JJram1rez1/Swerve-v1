// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.lib.Elastic.Elastic;
import frc.lib.swerve.SwerveConfigs;
import frc.lib.swerve.*;
import frc.robot.subsystems.swerve.CommandSwerveDrivetrain;

public class RobotContainer {

  private final CommandSwerveDrivetrain drivebase = SwerveConfigs.DriveTrain;
  private double maxSpeed = SwerveConfigs.kSpeedAt12VoltsMPS;

// add controller
// add field centric drive
// add brake and point commands

  Telemetry logger = new Telemetry(maxSpeed);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
