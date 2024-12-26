package frc.robot.subsystems.swerve;

import frc.lib.swerve.SwerveConfigs;

import com.ctre.phoenix6.hardware.Pigeon2;
import com.ctre.phoenix6.mechanisms.swerve.SwerveDrivetrain;
import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModule.DriveRequestType;

import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class Drivetrain extends SubsystemBase {

    private double m_MaxSpeed = 5.41; // This is our robots tuned max speed 
    private double m_MaxAngularRate = 1.5 * Math.PI; // Robots max angular Vel is 3/4 of a rotation

    private final CommandSwerveDrivetrain m_Drivetrain = SwerveConfigs.DriveTrain; //  This is the COmmand Drivebase it is located in Swerveconfigs

    private final SwerveRequest.Idle m_Idle = new SwerveRequest.Idle();
    private final SwerveRequest.SwerveDriveBrake m_Brake = new SwerveRequest.SwerveDriveBrake();
    private final SwerveRequest.PointWheelsAt m_Point = new SwerveRequest.PointWheelsAt();
    private final SwerveRequest.FieldCentric m_Drive = new SwerveRequest.FieldCentric().withDeadband(m_MaxSpeed * 0.1).withRotationalDeadband(m_MaxAngularRate * 0.1).withDriveRequestType(DriveRequestType.OpenLoopVoltage);

    private final CommandXboxController m_Driver = new CommandXboxController(0);

    /*private final SwerveDriveKinematics m_Kine;
    private final SwerveDriveOdometry m_Odom;

    private final Pigeon2 m_Gyro;

    */

    public Drivetrain() {
        /* wow so empty */
       // m_Kine = new SwerveDriveKinematics(null)
    }

    public void movementRequestorSwerve(double xSpeed, double ySpeed, double rotRate) {
        m_Drivetrain.applyRequest(
            () -> m_Drive
            .withVelocityX(xSpeed * m_MaxSpeed)
            .withVelocityY(ySpeed * m_MaxSpeed)
            .withRotationalRate(rotRate * m_MaxAngularRate)
        );
    }

    public void defaultDrive() {
        m_Drivetrain.applyRequest(
            () -> m_Drive
            .withVelocityX(m_Driver.getLeftY() * m_MaxSpeed)
            .withVelocityY(m_Driver.getLeftX() * m_MaxSpeed)
            .withRotationalRate(m_Driver.getRightX() * m_MaxAngularRate) // Im writing custom controller classes, this will look different later
        );        
    }

@Override
    public void periodic() {
        defaultDrive();
    }
}