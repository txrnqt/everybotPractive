package frc.robot.subsystems.Ball;

import java.util.function.BooleanSupplier;
import org.littletonrobotics.junction.Logger;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
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

    public boolean hasBall() {
        return io.hasBall();
    }

    public Command intake() {
        boolean hasball = hasBall();
        return run(() -> {
            setPower(1);
        }).until(hasball);
    }
}
