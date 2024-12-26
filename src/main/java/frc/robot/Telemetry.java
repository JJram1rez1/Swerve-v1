package frc.robot;

import com.ctre.phoenix6.Utils;
import com.ctre.phoenix6.mechanisms.swerve.SwerveDrivetrain.SwerveDriveState;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.networktables.DoubleArrayPublisher;
import edu.wpi.first.networktables.DoublePublisher;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.StringPublisher;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import frc.lib.Elastic.Elastic;

public class Telemetry {
    private final double MaxSpeed;
    DriverStation m_station;


    public Telemetry(double maxSpeed) {
        this.MaxSpeed = maxSpeed;
    }


    private final NetworkTableInstance m_Instance = NetworkTableInstance.getDefault();

    private final NetworkTable m_Table = m_Instance.getTable("Odometry");
    private final DoubleArrayPublisher m_FieldPublisher = m_Table.getDoubleArrayTopic("robot pose").publish();
    private final StringPublisher m_FieldTypePublisher = m_Table.getStringTopic(".type").publish();

    private final NetworkTable m_DriveStats =  m_Instance.getTable("Driving");
    private final DoublePublisher m_VelocityX = m_DriveStats.getDoubleTopic("Velocity X").publish();
    private final DoublePublisher m_VelocityY = m_DriveStats.getDoubleTopic("Velocity Y").publish();
    private final DoublePublisher m_Speed = m_DriveStats.getDoubleTopic("Speed").publish();
    private final DoublePublisher m_OdometryPeriod = m_DriveStats.getDoubleTopic("Odometry Period").publish();

    private Pose2d m_LastPose = new Pose2d();
    private double m_LastTime = Utils.getCurrentTimeSeconds();

    /** These Mechanisms Represent the state of the swerve modules in code */
    private final Mechanism2d[] m_ModuleMechanisms = new Mechanism2d[] {
        new Mechanism2d(1, 1),
        new Mechanism2d(1, 1),
        new Mechanism2d(1, 1),
        new Mechanism2d(1, 1),
    };

    private final MechanismLigament2d[] m_ModuleSpeeds = new MechanismLigament2d[] {
        m_ModuleMechanisms[0].getRoot("Back Left Root Speed", 0.5, 0.5).append(new MechanismLigament2d("Back Left Speed", 0.5, 0)),
        m_ModuleMechanisms[1].getRoot("Front Left Root Speed", 0.5, 0.5).append(new MechanismLigament2d("Front Left Speed", 0.5, 0)),
        m_ModuleMechanisms[2].getRoot("Back Right Root Speed", 0.5, 0.5).append(new MechanismLigament2d("Back Right Speed", 0.5, 0)),
        m_ModuleMechanisms[3].getRoot("Front Right Root Speed", 0.5, 0.5).append(new MechanismLigament2d("Front Right Speed", 0.5, 0)),
    };

    private final MechanismLigament2d[] m_moduleDirections = new MechanismLigament2d[] {
        m_ModuleMechanisms[0].getRoot("RootDirection", 0.5, 0.5)
            .append(new MechanismLigament2d("Direction", 0.1, 0, 0, new Color8Bit(Color.kMaroon))),
        m_ModuleMechanisms[1].getRoot("RootDirection", 0.5, 0.5)
            .append(new MechanismLigament2d("Direction", 0.1, 0, 0, new Color8Bit(Color.kMaroon))),
        m_ModuleMechanisms[2].getRoot("RootDirection", 0.5, 0.5)
            .append(new MechanismLigament2d("Direction", 0.1, 0, 0, new Color8Bit(Color.kMaroon))),
        m_ModuleMechanisms[3].getRoot("RootDirection", 0.5, 0.5)
            .append(new MechanismLigament2d("Direction", 0.1, 0, 0, new Color8Bit(Color.kMaroon))),
    };

    public void telemeterize(SwerveDriveState state) {
        Pose2d pose = state.Pose;
        m_FieldTypePublisher.set("Field 2d");
        m_FieldPublisher.set(new double[] {
            pose.getX(),
            pose.getY(),
            pose.getRotation().getDegrees()
        });

        double m_CurrentTime = Utils.getCurrentTimeSeconds();
        double m_DiffTime = m_CurrentTime - m_LastTime;
        m_LastTime = m_CurrentTime;
        Translation2d m_DistanceDiff = pose.minus(m_LastPose).getTranslation();
        m_LastPose = pose;

        Translation2d m_Velocities = m_DistanceDiff.div(m_DiffTime);

        m_Speed.set(m_Velocities.getNorm());
        m_VelocityX.set(m_Velocities.getX());
        m_VelocityY.set(m_Velocities.getY());
        m_OdometryPeriod.set(state.OdometryPeriod);

        for (int i = 0; i < 4; ++i) {
            m_ModuleSpeeds[i].setAngle(state.ModuleStates[i].angle);
            m_moduleDirections[i].setAngle(state.ModuleStates[i].angle);
            m_ModuleSpeeds[i].setLength(state.ModuleStates[i].speedMetersPerSecond / (2 * MaxSpeed));

            SmartDashboard.putData("Module " + i, m_ModuleMechanisms[i]);
        }
    }
}
