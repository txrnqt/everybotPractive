package frc.robot.subsystems.Tank;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.controls.StrictFollower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.controller.DifferentialDriveFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.Constants;

public class TankReal implements TankIO {
    /**
     * initilize motors and sensors
     */
    private final TalonFX tankFrontRightLead = new TalonFX(Constants.Tank.FRONTRIGHT, "CANivore");
    private final TalonFX tankFrontLeftLead = new TalonFX(Constants.Tank.FRONTLEFT, "CANivore");
    private final TalonFX tankBackRightFollow = new TalonFX(Constants.Tank.BACKRIGHT, "CANivore");
    private final TalonFX tankBackLeftFollow = new TalonFX(Constants.Tank.BACKLEFT, "CANivore");
    private final Encoder leftENC = new Encoder(0, 0);
    private final AHRS gyro = new AHRS(Constants.Tank.navXID);
    private final StatusSignal<Double> tankRightLeadVelocity;
    private final StatusSignal<Double> tankLeftLeadVelocity;
    private final StatusSignal<Double> tankLeftPosition;
    private final StatusSignal<Double> tankRightPositon;

    private ProfiledPIDController controllerL = new ProfiledPIDController(Constants.Tank.TANK_KP,
        Constants.Tank.TANK_KI, Constants.Tank.TANK_KD, new TrapezoidProfile.Constraints(0, 0));
    private ProfiledPIDController controllerR = new ProfiledPIDController(Constants.Tank.TANK_KP,
        Constants.Tank.TANK_KI, Constants.Tank.TANK_KD, new TrapezoidProfile.Constraints(0, 0));

    private DifferentialDriveFeedforward feedforwardLeft =
        new DifferentialDriveFeedforward(Constants.Tank.TANK_KVL, Constants.Tank.TANK_KAL,
            Constants.Tank.TANK_KVA, Constants.Tank.TANK_KAA);

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
        tankLeftPosition = tankFrontLeftLead.getPosition();
        tankRightPositon = tankFrontRightLead.getPosition();

        /**
         * sets motor to netural mode
         */
        tankFrontLeftLead.setNeutralMode(NeutralModeValue.Brake);
        tankFrontRightLead.setNeutralMode(NeutralModeValue.Brake);
        tankBackLeftFollow.setNeutralMode(NeutralModeValue.Brake);
        tankBackRightFollow.setNeutralMode(NeutralModeValue.Brake);

        leftENC.setDistancePerPulse(0);
    }

    @Override
    /**
     * sets motor power
     */
    public void setVolatge(double leftV, double rightV) {
        tankBackLeftFollow.set(leftV);
        tankBackRightFollow.set(rightV);
    }

    public void setPID(Double left) {
        tankFrontLeftLead
            .set((controllerL.calculate(left)) 
            + feedforwardLeft.calculate(tankLeftLeadVelocity.getValueAsDouble(), 0, 0, 0, 0));
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
        inputs.tankRightPosition = tankRightPositon.getValueAsDouble();
        inputs.tankleftPosition = tankLeftPosition.getValueAsDouble();

        /**
         * refreshs motor data
         */
        BaseStatusSignal.refreshAll(tankRightLeadVelocity, tankLeftLeadVelocity, tankRightPositon,
            tankRightPositon);
    }


}
