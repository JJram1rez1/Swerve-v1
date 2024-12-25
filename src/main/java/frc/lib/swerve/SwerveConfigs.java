package frc.lib.swerve;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.Pigeon2Configuration;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.mechanisms.swerve.SwerveDrivetrainConstants;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModuleConstants;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModule.ClosedLoopOutputType;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModuleConstants.SteerFeedbackType;

import edu.wpi.first.math.util.Units;
import frc.robot.swerve.CommandSwerveDrivetrain;

import com.ctre.phoenix6.mechanisms.swerve.SwerveModuleConstantsFactory;

public class SwerveConfigs {

    private static final double kSlipCurrentA = 150.00;
    private static final double kSpeedAt12VoltsMPS = 5.41; // Robots max speed in metric units
    private static final double kCoupleRatio = 0; // Every 1 Rotation of the Azimuth gear in this many drive motor turns // need to tune this again??
    private static final double kDriveGearRatio = 5.9;
    private static final double kSteerGearRatio = 7;
    private static final double kWheelRadiusInches = 2;
    private static final double kSteerInertia = 0.000001;
    private static final double kDriveInertia = 0.001;
    private static final double kSteerFrictionVoltage = 0.25;
    private static final double kDriveFrictionVoltage = 0.25;

    private static final boolean kInvertLeftSide = false;
    private static final boolean kInvertRightSide = true;

    private static final String kCANBusName = "SwerveBus";
    private static final int kPigeonId = 14;


    private static final Slot0Configs steerGains = new Slot0Configs()
        .withKP(100).withKI(0).withKD(0.2)
        .withKS(0).withKV(1.5).withKA(0);

    private static final Slot0Configs driveGains = new Slot0Configs()
        .withKP(3).withKI(0).withKD(0)
        .withKS(0).withKV(0).withKA(0);

    private static final ClosedLoopOutputType steerClosedLoopOutput = ClosedLoopOutputType.Voltage;
    private static final ClosedLoopOutputType driveClosedLoopOutput = ClosedLoopOutputType.Voltage;

    private static final TalonFXConfiguration driveInitialConfigs = new TalonFXConfiguration();
    private static final TalonFXConfiguration steerInitialConfigs = new TalonFXConfiguration()
        .withCurrentLimits(
            new CurrentLimitsConfigs()
            .withStatorCurrentLimit(60)
            .withStatorCurrentLimitEnable(true)
        );
    private static final CANcoderConfiguration cancoderInitialConfigs = new CANcoderConfiguration();
    private static final Pigeon2Configuration pigeon2InitialConfigs = new Pigeon2Configuration();

    public static final SwerveDrivetrainConstants DrivetrainConstants = new SwerveDrivetrainConstants()
            .withCANbusName(kCANBusName)
            .withPigeon2Id(kPigeonId)
            .withPigeon2Configs(pigeon2InitialConfigs);

    public static final SwerveModuleConstantsFactory ConstantCreator = new SwerveModuleConstantsFactory()
            .withDriveMotorGearRatio(kDriveGearRatio)
            .withSteerMotorGearRatio(kSteerGearRatio)
            .withWheelRadius(kWheelRadiusInches)
            .withSlipCurrent(kSlipCurrentA)
            .withSteerMotorGains(steerGains)
            .withDriveMotorGains(driveGains)
            .withSteerMotorClosedLoopOutput(steerClosedLoopOutput)
            .withDriveMotorClosedLoopOutput(driveClosedLoopOutput)
            .withSpeedAt12VoltsMps(kSpeedAt12VoltsMPS)
            .withSteerInertia(kSteerInertia)
            .withDriveInertia(kDriveInertia)
            .withSteerFrictionVoltage(kSteerFrictionVoltage)
            .withDriveFrictionVoltage(kDriveFrictionVoltage)
            .withFeedbackSource(SteerFeedbackType.FusedCANcoder)
            .withCouplingGearRatio(kCoupleRatio)
            .withDriveMotorInitialConfigs(driveInitialConfigs)
            .withSteerMotorInitialConfigs(steerInitialConfigs)
            .withCANcoderInitialConfigs(cancoderInitialConfigs);

        private static final int kFrontLeftDriveMotorId = 32;
        private static final int kFrontLeftSteerMotorId = 6;
        private static final int kFrontLeftEncoderId = 11;
        private static final double kFrontLeftEncoderOffset = -0.047119140625;
        private static final boolean kFrontLeftSteerInvert = true;
    
        private static final double kFrontLeftXPosInches = 13.5;
        private static final double kFrontLeftYPosInches = 13.5;

        private static final int kFrontRightDriveMotorId = 34;
        private static final int kFrontRightSteerMotorId = 8;
        private static final int kFrontRightEncoderId = 12;
        private static final double kFrontRightEncoderOffset = -0.264404296875;
        private static final boolean kFrontRightSteerInvert = true;
    
        private static final double kFrontRightXPosInches = 13.5;
        private static final double kFrontRightYPosInches = -13.5;

        private static final int kBackLeftDriveMotorId = 40;
        private static final int kBackLeftSteerMotorId = 4;
        private static final int kBackLeftEncoderId = 10;
        private static final double kBackLeftEncoderOffset = -0.22021484375;
        private static final boolean kBackLeftSteerInvert = true;
    
        private static final double kBackLeftXPosInches = -13.5;
        private static final double kBackLeftYPosInches = 13.5;

        private static final int kBackRightDriveMotorId = 33;
        private static final int kBackRightSteerMotorId = 2;
        private static final int kBackRightEncoderId = 9;
        private static final double kBackRightEncoderOffset = -0.0927734375;
        private static final boolean kBackRightSteerInvert = true;
    
        private static final double kBackRightXPosInches = -13.5;
        private static final double kBackRightYPosInches = -13.5;


    private static final SwerveModuleConstants FrontLeft = ConstantCreator.createModuleConstants(
            kFrontLeftSteerMotorId, kFrontLeftDriveMotorId, kFrontLeftEncoderId, kFrontLeftEncoderOffset, Units.inchesToMeters(kFrontLeftXPosInches), Units.inchesToMeters(kFrontLeftYPosInches), kInvertLeftSide)
            .withSteerMotorInverted(kFrontLeftSteerInvert);
    private static final SwerveModuleConstants FrontRight = ConstantCreator.createModuleConstants(
            kFrontRightSteerMotorId, kFrontRightDriveMotorId, kFrontRightEncoderId, kFrontRightEncoderOffset, Units.inchesToMeters(kFrontRightXPosInches), Units.inchesToMeters(kFrontRightYPosInches), kInvertRightSide)
            .withSteerMotorInverted(kFrontRightSteerInvert);
    private static final SwerveModuleConstants BackLeft = ConstantCreator.createModuleConstants(
            kBackLeftSteerMotorId, kBackLeftDriveMotorId, kBackLeftEncoderId, kBackLeftEncoderOffset, Units.inchesToMeters(kBackLeftXPosInches), Units.inchesToMeters(kBackLeftYPosInches), kInvertLeftSide)
            .withSteerMotorInverted(kBackLeftSteerInvert);
    private static final SwerveModuleConstants BackRight = ConstantCreator.createModuleConstants(
            kBackRightSteerMotorId, kBackRightDriveMotorId, kBackRightEncoderId, kBackRightEncoderOffset, Units.inchesToMeters(kBackRightXPosInches), Units.inchesToMeters(kBackRightYPosInches), kInvertRightSide)
            .withSteerMotorInverted(kBackRightSteerInvert);

    public static final CommandSwerveDrivetrain DriveTrain = new CommandSwerveDrivetrain(DrivetrainConstants, FrontLeft,
            FrontRight, BackLeft, BackRight);
}
