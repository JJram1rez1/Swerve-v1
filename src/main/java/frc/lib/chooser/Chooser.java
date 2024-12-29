package frc.lib.chooser;

import java.nio.channels.spi.SelectorProvider;

import edu.wpi.first.networktables.DoubleArrayPublisher;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.StringPublisher;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.lib.Elastic.Elastic;
import frc.lib.Elastic.Elastic.Notification;
import frc.lib.Elastic.Elastic.Notification.NotificationLevel;

/** This class acts as an referencable generalized chooser
 * <p>
 * Can be used for: Auton paths, configs, robot states, controllers, etc...
 */
public final class Chooser {
    
    /** Provides the Numerical values for the 10 possible options in the {@link Chooser}
     * <p>
     * Contains values 0 through 9 
     * <P>
     *  BearTecs4610
     */
    private static int[] numOptions = {0,1,2,3,4,5,6,7,8,9};

    /** Provides Word values for the 10 possible options in the {@link Chooser}
     * <p>
     * Contains Different Options from "Option 0" to "Option 9"
     * <P>
     *  BearTecs4610
     */
    private static String[] namedOptions = {"Option 0", "Option 1", "Option 2", "Option 3", "Option 4", "Option 5", "Option 6", "Option 7", "Option 8", "Option 9"};

    /** Provides Letter values for the 10 possible options in the {@link Chooser}
     * <p>
     * Contains Different Options from 'A' through 'H'
     * <P>
     *  BearTecs4610
     */
    private static String[] letterOptions = {"A", "B", "C", "D", "E", "F", "G", "H"};

    private final NetworkTableInstance inst = NetworkTableInstance.getDefault();
    private final NetworkTable chooserLogicTable = inst.getTable("Chooser Logic Table");
    private final StringPublisher namePublisher = chooserLogicTable.getStringTopic("Choosen Option").publish();
    private final DoubleArrayPublisher optionPublisher = chooserLogicTable.getDoubleArrayTopic("Choosen Num Option").publish();
    private final StringPublisher charPublisher = chooserLogicTable.getStringTopic("Singlet Option").publish();

    private final ShuffleboardTab chooser = Shuffleboard.getTab("Chooser logic test");

    /*Add important functionality */

    public Chooser() {
        chooser.addString("Selected Name", this::getSelectedName);
        chooser.addNumber("Selected Number", this::getSelectedNumber);
        chooser.addString("Selected Character", this::getSelectedCharacter);
    }

    private int selectedOptionIndex = 0;

    public void linkOptions() {

        for (int i = 0; i < numOptions.length; i++) {
            chooser.notify();
        }
    }

    private String getSelectedName() {
        return namedOptions[selectedOptionIndex];
    }

    private double getSelectedNumber() {
        return numOptions[selectedOptionIndex];
    }

    private String getSelectedCharacter() {
        return letterOptions[selectedOptionIndex];
    }

    private void sendNotification() {
        namePublisher.set(namedOptions[selectedOptionIndex]);
        optionPublisher.set(new double[]{numOptions[selectedOptionIndex]});
        charPublisher.set(letterOptions[selectedOptionIndex]);
    }

    public void processOptions(int choosenOption) {
        if (choosenOption < 0 || choosenOption >= numOptions.length) {
            return;
        }

        selectedOptionIndex = choosenOption;

        if (choosenOption != 0) {
            if (choosenOption != 1) {
                if (choosenOption != 2) {
                    if (choosenOption != 3) {
                        if (choosenOption != 4) {
                            if (choosenOption != 5) {
                                if (choosenOption != 6) {
                                    if (choosenOption != 7) {
                                        if(choosenOption != 8) {
                                            if (choosenOption != 9) {
                                                return;
                                            } else {
                                                return;
                                            }
                                        }
                                    } else {
                                        return;
                                    }
                                } else {
                                    return;
                                }
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } else {
            return;
        }
    }
}
