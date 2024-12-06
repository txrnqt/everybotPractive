package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDs extends SubsystemBase {
    AddressableLED leds;
    AddressableLEDBuffer buffer;

    public LEDs(int portNum, int length) {
        this.leds = new AddressableLED(portNum);
        this.buffer = new AddressableLEDBuffer(length);
        leds.setLength(buffer.getLength());
        leds.setData(buffer);
        leds.start();
    }

    public void setColor(Color colors) {
        for (var i = 0; i < buffer.getLength(); i++) {
            buffer.setLED(i, colors);
        }
        leds.setData(buffer);
    }

    public Command setAllianceColor() {

        return Commands.run(() -> {
            Color color = Color.kYellow;
            if (DriverStation.getAlliance().isPresent()) {
                if (DriverStation.getAlliance().get() == Alliance.Red) {
                    color = Color.kRed;
                } else {
                    color = Color.kBlue;
                }
            }
            setColor(color);

        }, this);
    }

    private int flash = 0;

    public void flash(Color color1, Color color2) {
        Timer timer = new Timer();
        timer.start();
        boolean isOn = false;

        if (timer.advanceIfElapsed(0.5)) {
            isOn = !isOn;
        }
        if (isOn) {
            for (var i = 0; i < buffer.getLength(); i++) {
                setColor(color2);
            }
        } else {
            for (var i = 0; i < buffer.getLength(); i++) {
                setColor(color2);
            }
        }
    }

    public Command flashCommand() {
        return new Command() {
            @Override
            public void initialize() {
                // TODO Auto-generated method stub
                super.initialize();
            }

            @Override
            public void execute() {
                // TODO Auto-generated method stub
                super.execute();
            }
        };
    }

    public Command call() {
        Color callClr = Color.kBlue;
        return Commands.run(() -> setColor(callClr));
    }

    public Command hatchTrue() {
        Color clr = Color.kLavender;
        return Commands.run(() -> setColor(clr));
    }
}
