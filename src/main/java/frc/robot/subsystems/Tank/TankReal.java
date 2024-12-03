package frc.robot.subsystems.Tank;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.kauailabs.navx.frc.AHRS;
import frc.robot.Constants;

public class TankReal implements TankIO {
    /**
     * initilize motors and sensors
     */
    private final TalonFX tankFrontRightLead = new TalonFX(Constants.Tank.FRONTRIGHT);
    private final TalonFX tankFrontLeftLead = new TalonFX(Constants.Tank.FRONTLEFT);
    private final TalonFX tankBackRightFollow = new TalonFX(Constants.Tank.BACKRIGHT);
    private final TalonFX tankBackLeftFollow = new TalonFX(Constants.Tank.BACKLEFT);
    private final AHRS gyro = new AHRS(Constants.Tank.navXID);
    private final StatusSignal<Double> tankRightLeadVelocity;
    private final StatusSignal<Double> tankLeftLeadVelocity;

    public TankReal() {
        /**
         * set motors to follow each other
         */
        tankBackLeftFollow.set(tankFrontLeftLead.getDeviceID());
        tankBackRightFollow.set(tankFrontRightLead.getDeviceID());

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
        inputs.tankRightLeadVelocityRadPerSec = tankRightLeadVelocity.getValueAsDouble();
        inputs.tankLeftLeadVelocityRadPerSec = tankLeftLeadVelocity.getValueAsDouble();

        /**
         * refreshs motor data
         */
        BaseStatusSignal.refreshAll(tankRightLeadVelocity, tankLeftLeadVelocity);
    }
}
