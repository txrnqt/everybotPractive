package frc.robot.subsystems.Tank;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.TalonFX;
import com.kauailabs.navx.frc.AHRS;
import frc.robot.Constants;

public class TankReal implements TankIO {
    /**
     * initilize motors and sensors
     */
    private final TalonFX tankFrontRightLead = new TalonFX(4);
    private final TalonFX tankFrontLeftLead = new TalonFX(2);
    private final TalonFX tankBackRightFollow = new TalonFX(5);
    private final TalonFX tankBackLeftFollow = new TalonFX(3);
    private final AHRS gyro = new AHRS(Constants.Tank.navXID);
    private final StatusSignal<Double> tankRightLeadPositon;
    private final StatusSignal<Double> tankRightLeadVelocity;
    private final StatusSignal<Double> tankRightLeadVoltage;
    private final StatusSignal<Double> tankLeftLeadPositon;
    private final StatusSignal<Double> tankLeftLeadVelocity;
    private final StatusSignal<Double> tankLeftLeadVoltage;

    public TankReal() {
        /**
         * set motors to follow each other
         */
        tankBackLeftFollow.set(tankFrontLeftLead.getDeviceID());
        tankBackRightFollow.set(tankFrontRightLead.getDeviceID());

        /**
         * get data from motors
         */
        tankRightLeadPositon = tankFrontRightLead.getPosition();
        tankRightLeadVelocity = tankFrontRightLead.getVelocity();
        tankRightLeadVoltage = tankFrontRightLead.getMotorVoltage();
        tankLeftLeadPositon = tankFrontLeftLead.getPosition();
        tankLeftLeadVelocity = tankFrontLeftLead.getVelocity();
        tankLeftLeadVoltage = tankFrontLeftLead.getMotorVoltage();

    }

    public void setPower(double powerLeft, double powerRight) {
        tankBackLeftFollow.set(powerLeft);
        tankBackRightFollow.set(powerRight);
    }

    public void periodic() {}

    public void updateInputs() {
        /**
         * gets gyro data
         */
        TankIOInputs.yaw = gyro.getYaw();
        TankIOInputs.roll = gyro.getRoll();
        TankIOInputs.pitch = gyro.getPitch();

        /**
         * refreshs motor data
         */
        BaseStatusSignal.refreshAll(tankRightLeadPositon, tankRightLeadVelocity,
            tankRightLeadVoltage, tankLeftLeadPositon, tankLeftLeadVelocity, tankLeftLeadVoltage);
    }
}
