package frc.robot.subsystems.Ball;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.DigitalInput;

public class BallReal implements BallIO {
    private final CANSparkMax ballMotor = new CANSparkMax(12, MotorType.kBrushless);
    private final DigitalInput beamBreak1 = new DigitalInput(0);
    private final DigitalInput beamBreak2 = new DigitalInput(0);

    public BallReal() {}

    /**
     * sets power for motors
     */
    public void setPower(double power) {
        ballMotor.set(power);
    }

    public void periodic() {}

    public void updateInputs() {}
}
