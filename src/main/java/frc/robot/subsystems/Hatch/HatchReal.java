package frc.robot.subsystems.Hatch;

import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants;

public class HatchReal implements HatchIO {
    private CANSparkMax hatchMotor =
        new CANSparkMax(Constants.Hatch.HATCHMOTOR, MotorType.kBrushless);
    private DigitalInput touchSensor = new DigitalInput(Constants.Hatch.TOUCHSENSOR);
    private CANcoder hatchCancoder = new CANcoder(Constants.Hatch.CANCODER);


    public HatchReal() {
        // as
    }

    public void setPower(double power) {
        hatchMotor.set(power);
    }

    public void updateInputs() {}


}
