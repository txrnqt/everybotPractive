package frc.robot.subsystems.Tank;


import org.littletonrobotics.junction.Logger;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;

public class Tank extends SubsystemBase {
    private TankIO io;
    private TankIOInputsAutoLogged inputs = new TankIOInputsAutoLogged();

    public Tank(TankIO tankIO) {
        this.io = tankIO;
        io.updateInputs(inputs);
    }

    @Override
    public void periodic() {
        Logger.processInputs("Tank", inputs);
        io.updateInputs(inputs);
    }

    PIDController tankPidControllerL =
        new PIDController(Constants.Tank.TANK_KP, Constants.Tank.TANK_KI, Constants.Tank.TANK_KD);

    PIDController tankPidControllerR =
        new PIDController(Constants.Tank.TANK_KP, Constants.Tank.TANK_KI, Constants.Tank.TANK_KD);

    public void setVolatge(double leftV, double rightV) {
        Logger.recordOutput("Tank/leftPower", leftV);
        Logger.recordOutput("Tank/rightPower", rightV);
        io.setVolatge(leftV, rightV);
    }

    public void setPIDVoltage(double leftSetPoint, double rightSetPoint) {
        double pidL =
            tankPidControllerL.calculate(inputs.tankLeftLeadVelocity * Constants.Tank.MAX_SPEED);
        double pidR =
            tankPidControllerR.calculate(inputs.tankRightLeadVelocity * Constants.Tank.MAX_SPEED);
        setVolatge(pidL, pidR);
    }

    public Command tankCMD(CommandXboxController driver) {
        return run(() -> {
            setPIDVoltage(driver.getLeftY(), driver.getRightY());
        });
    }
}

