package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by Nikhil on 11/13/2015.
 */
public class BeaconScorer {


    public Servo rightButtonServo;
    public Servo leftButtonServo;
    public ColorSensor colorSensor;

    double rightButtonServoPressed = 0.40;
    double leftButtonServoPressed = 0.60;
    double leftButtonServoReset = 0.40;
    double rightButtonServoReset = 0.60;
    
    boolean rightIsPressed = false;
    boolean leftIsPressed = false;

    double servoStop = 0.5;

    long turnTime = 870;

    public BeaconScorer(){
    }

    public void init(HardwareMap hardwareMap){

        colorSensor = hardwareMap.colorSensor.get("colorSensor");
        rightButtonServo = hardwareMap.servo.get("rightButtonServo");
        leftButtonServo = hardwareMap.servo.get("leftButtonServo");

        rightButtonServo.setPosition(servoStop);
        leftButtonServo.setPosition(servoStop);
        colorSensor.enableLed(true);

    }

    public void pressRightButton() throws InterruptedException {

        if(!rightIsPressed) {
            rightButtonServo.setPosition(rightButtonServoPressed);
            TimeUnit.MILLISECONDS.sleep(turnTime);
            rightButtonServo.setPosition(servoStop);
            rightIsPressed = true;
        }
    }

    public void pressLeftButton() throws InterruptedException {

        if(!leftIsPressed) {
            leftButtonServo.setPosition(leftButtonServoPressed);
            TimeUnit.MILLISECONDS.sleep(turnTime);
            leftButtonServo.setPosition(servoStop);
            leftIsPressed = true;
        }

    }

    public void resetButtonPressers() throws InterruptedException {

        if(leftIsPressed) {
            leftButtonServo.setPosition(leftButtonServoReset);
            TimeUnit.MILLISECONDS.sleep(turnTime);
            leftButtonServo.setPosition(servoStop);
            leftIsPressed = false;
        }
        
        if(rightIsPressed) {
            rightButtonServo.setPosition(rightButtonServoReset);
            TimeUnit.MILLISECONDS.sleep(turnTime);
            leftButtonServo.setPosition(servoStop);
            rightIsPressed = false;
        }

        rightButtonServo.setPosition(servoStop);
    }

    public String getBeaconColor() {

        colorSensor.enableLed(false);

        if(colorSensor.blue() == 0 && colorSensor.red() == 0)
            return "Null";

        colorSensor.enableLed(true);

        if(colorSensor.blue() > colorSensor.red())
            return "Blue";
        else if(colorSensor.red() > colorSensor.blue())
            return "Red";
        else
            return "Null";
    }
}




