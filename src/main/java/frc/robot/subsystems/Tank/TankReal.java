package frc.robot.subsystems.Tank;

import com.ctre.phoenix6.hardware.TalonFX;

public class TankReal implements TankIO {
    /**
     * initilize motors
     */
    private final TalonFX tankFrontRightLead = new TalonFX(4);
    private final TalonFX tankFrontLeftLead = new TalonFX(2);
    private final TalonFX tankBackRightFollow = new TalonFX(5);
    private final TalonFX tankBackLeftFollow = new TalonFX(3);

    public TankReal() {
        /**
         * set motors to follow each other
         */
        tankBackLeftFollow.set(tankFrontLeftLead.getDeviceID());
        tankBackRightFollow.set(tankFrontRightLead.getDeviceID());
    }

    public void setPower(double powerLeft, double powerRight) {
        tankBackLeftFollow.set(powerLeft);
        tankBackRightFollow.set(powerRight);
    }

    public void periodic() {}

    public void updateInputs() {}
}
