package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.LEDs;

public class LEDsCMD extends Command {
    private LEDs leds;
    private int ledLength;
    private int flashingDelay = 0;
    private Color color;
    private Color altColor;

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
            leds.setColor(color);
        }, this);
    }
}
