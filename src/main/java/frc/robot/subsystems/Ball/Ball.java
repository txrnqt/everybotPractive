package frc.robot.subsystems.Ball;

import org.littletonrobotics.junction.Logger;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Ball extends SubsystemBase {
    private BallIO io;
    private BallIOInputsAutoLogged inputs = new BallIOInputsAutoLogged();

    public Ball(BallIO ballIO) {
        this.io = ballIO;
    }

    @Override
    public void periodic() {
        Logger.processInputs("Ball", inputs);
        io.updateInputs(inputs);
    }

    public void setPower(double power) {
        Logger.recordOutput("Ball/power", power);
        io.setPower(power);
    }

    /**
     * sets ball intake
     */
    public Command intake() {
        return run(() -> {
            setPower(1);
        }).until(() -> inputs.hasBall).withTimeout(0);
    }

    /**
     * shoots ball
     */
    public Command shoot() {
        return run(() -> {
            setPower(1);
        }).unless(() -> !inputs.readyToShoot).withTimeout(0);
    }
}
