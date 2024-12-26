package frc.robot.commands.requestor;

import com.ctre.phoenix6.mechanisms.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;

import edu.wpi.first.wpilibj2.command.Command;
import frc.lib.swerve.SwerveConfigs;
import frc.robot.subsystems.swerve.CommandSwerveDrivetrain;


public class MovementRequestor extends Command {

    private double m_xSpeed;
    private double m_ySpeed;
    private double m_rotRate;
    private double elapsedTime;

    private double m_MaxSpeed = 5.41; // This is our robots tuned max speed 
    private double m_MaxAngularRate = 1.5 * Math.PI; // Robots max angular Vel is 3/4 of a rotation

    private CommandSwerveDrivetrain m_Drivetrain = SwerveConfigs.DriveTrain; //  This is the COmmand Drivebase it is located in Swerveconfigs

    private SwerveRequest.Idle m_Idle = new SwerveRequest.Idle();
    private SwerveRequest.SwerveDriveBrake m_Brake = new SwerveRequest.SwerveDriveBrake();
    private SwerveRequest.PointWheelsAt m_Point = new SwerveRequest.PointWheelsAt();
    private SwerveRequest.FieldCentric m_Drive = new SwerveRequest.FieldCentric().withDeadband(m_MaxSpeed * 0.1).withRotationalDeadband(m_MaxAngularRate * 0.1).withDriveRequestType(DriveRequestType.OpenLoopVoltage);



    public MovementRequestor(double xSpeed, double ySpeed, double rotRate, CommandSwerveDrivetrain driveTrain) {
        this.m_xSpeed = xSpeed;
        this.m_ySpeed = ySpeed;
        this.m_rotRate = rotRate;
        this.m_Drivetrain = driveTrain;
        addRequirements(driveTrain);
    }
    
}
