package frc.robot.subsystems.Tank;

import com.ctre.phoenix.Logger;
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
}

