package frc.robot.subsystems.Hatch;

import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants;

public class HatchReal implements HatchIO {
    private CANSparkMax hatchMotor =
        new CANSparkMax(Constants.Hatch.HATCHMOTOR, MotorType.kBrushless);
    private DigitalInput touchSensor = new DigitalInput(Constants.Hatch.TOUCHSENSOR);
    private CANcoder hatchCancoder = new CANcoder(Constants.Hatch.CANCODER);
    private AbsoluteEncoder hatchWristEnc = hatchMotor.getAbsoluteEncoder();
    // private StatusSignal<Double> can = hatchCancoder;


    public HatchReal() {
        hatchWristEnc.setPositionConversionFactor(1);
        hatchMotor.setIdleMode(IdleMode.kBrake);
    }

    @Override
    public void setVolatge(double v) {
        hatchMotor.setVoltage(v);
    }

    public void updateInputs(HatchIOInputs inputs) {
        // inputs.positon = hatchCancoder.getPosition();
        inputs.hatchAbsoluteENCRawValue = hatchCancoder.getPosition().getValueAsDouble();
        inputs.touchSensor = touchSensor.get();
    }
}
