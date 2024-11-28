package frc.robot.subsystems.Tank;


import org.littletonrobotics.junction.Logger;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Tank extends SubsystemBase {
    private TankIO io;
    private TankIOInputsAutoLogged inputs = new TankIOInputsAutoLogged();

    public Tank(TankIO tankIO) {
        this.io = tankIO;
    }

    @Override
    public void periodic() {
        Logger.processInputs("Tank", inputs);
        io.updateInputs(inputs);

    }

    public void setPower(double leftPower, double rightPower) {
        Logger.recordOutput("Tank/leftPower", leftPower);
        Logger.recordOutput("Tank/rightPower", rightPower);
        io.setPower(leftPower, rightPower);
    }

    public Command tankCMD(XboxController controller) {
        return run(() -> {
            setPower(controller.getLeftY(), controller.getRightY());
        });
    }
}

