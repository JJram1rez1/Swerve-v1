package frc.lib.LEDs;

import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;

public class ledBuffer {
    byte[] priorityBuffer;
    AddressableLEDBuffer ledBuffer;

    public ledBuffer(int length) {
        priorityBuffer = new byte[length];
        ledBuffer = new AddressableLEDBuffer(length);
    }

    public AddressableLEDBuffer getLedBuffer() {
        return ledBuffer;
    }

    public boolean isHigherPriority(int indexer, int priority) {
        return priority >= priorityBuffer[indexer];
    }

    public void resetPriorityBuffer() {
        for (int i = 0; i < priorityBuffer.length; i++) {
            priorityBuffer[i] = 0;
            ledBuffer.setRGB(i, 0, 0, 0);
        }
    }

    public void setRGB(int index, int r, int g, int b, int priority) {
        if (isHigherPriority(index, priority)) {
            priorityBuffer[index] = (byte) priority;
            ledBuffer.setRGB(index, r, g, b);
        }
    }

    public void setHSV(int index, int h, int s, int v, int priority) {
        if (isHigherPriority(index, priority)) {
            priorityBuffer[index] = (byte) priority;
            ledBuffer.setHSV(index, h, s, v);
        }
    }

    public void setLED(int index, Color color, int priority) {
        if (isHigherPriority(index, priority)) {
            priorityBuffer[index] = (byte) priority;
            ledBuffer.setLED(index, color);
        }
    }

    public void setLED(int index, Color8Bit color, int priority) {
        if (isHigherPriority(index, priority)) {
            priorityBuffer[index] = (byte) priority;
            ledBuffer.setLED(index, color);
        }
    }

    public int getLength() {
        return ledBuffer.getLength();
    }

    public Color8Bit getLED8Bit(int index) {
        return ledBuffer.getLED8Bit(index);
    }

    public Color getLED(int index) {
        return ledBuffer.getLED(index);
    }
}
