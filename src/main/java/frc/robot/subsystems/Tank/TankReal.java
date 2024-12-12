package frc.robot.subsystems.Tank;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.controls.StrictFollower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import frc.robot.Constants;

public class TankReal implements TankIO {
    /**
     * initilize motors and sensors
     */
    private final TalonFX tankFrontRightLead = new TalonFX(Constants.Tank.FRONTRIGHT, "CANivore");
    private final TalonFX tankFrontLeftLead = new TalonFX(Constants.Tank.FRONTLEFT, "CANivore");
    private final TalonFX tankBackRightFollow = new TalonFX(Constants.Tank.BACKRIGHT, "CANivore");
    private final TalonFX tankBackLeftFollow = new TalonFX(Constants.Tank.BACKLEFT, "CANivore");
    private final RelativeEncoder tankLefRelativeEncoder = new RelativeEncoder() {tankFrontLeftLead.getDeviceID();};
    private final AHRS gyro = new AHRS(Constants.Tank.navXID);
    private final StatusSignal<Double> tankRightLeadVelocity;
    private final StatusSignal<Double> tankLeftLeadVelocity;

    /** CHANGE THE 2ND AND 3RD VALUE TO ENCODER POSITIONS */
    private final DifferentialDriveOdometry odom = new DifferentialDriveOdometry(
        gyro.getRotation2d(), 0.0, 0.0, new Pose2d(0.0, 0.0, new Rotation2d()));

    public TankReal() {
        /**
         * set motors to follow each other
         */
        tankBackLeftFollow.setControl(new StrictFollower(tankFrontLeftLead.getDeviceID()));
        tankBackRightFollow.setControl(new StrictFollower(tankFrontRightLead.getDeviceID()));

        /**
         * get data from motors
         */
        tankRightLeadVelocity = tankFrontRightLead.getVelocity();
        tankLeftLeadVelocity = tankFrontLeftLead.getVelocity();

        /**
         * sets motor to netural mode
         */
        tankFrontLeftLead.setNeutralMode(NeutralModeValue.Brake);
        tankFrontRightLead.setNeutralMode(NeutralModeValue.Brake);
        tankBackLeftFollow.setNeutralMode(NeutralModeValue.Brake);
        tankBackRightFollow.setNeutralMode(NeutralModeValue.Brake);

        /**
         * reset encoders
         */

    }

    @Override
    /**
     * sets motor power
     */
    public void setVolatge(double leftV, double rightV) {
        tankBackLeftFollow.set(leftV);
        tankBackRightFollow.set(rightV);
    }

    public void periodic() {}

    public void updateInputs(TankIOInputs inputs) {
        /**
         * gets gyro data
         */
        inputs.yaw = gyro.getYaw();
        inputs.roll = gyro.getRoll();
        inputs.pitch = gyro.getPitch();

        /**
         * gets motor data
         */
        inputs.tankRightLeadVelocity = tankRightLeadVelocity.getValueAsDouble();
        inputs.tankLeftLeadVelocity = tankLeftLeadVelocity.getValueAsDouble();

        /**
         * refreshs motor data
         */
        BaseStatusSignal.refreshAll(tankRightLeadVelocity, tankLeftLeadVelocity);

        pose = odom.update(0.0, 0.0);
    }
}
