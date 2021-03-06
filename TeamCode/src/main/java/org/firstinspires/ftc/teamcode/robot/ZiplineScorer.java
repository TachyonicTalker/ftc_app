package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.*;

/**
 * Created by Carlos on 11/14/2015.
 */
public class ZiplineScorer {

    public Servo leftServo;

    public double leftOut = 0.0;
    public double leftIn = 0.5;

    public Servo rightServo;

    public double rightOut = 1.0;
    public double rightIn = 0.5;

    public ZiplineScorer(){

    }

    public void init(HardwareMap hardwareMap){
        leftServo = hardwareMap.servo.get("leftZiplineServo");
        rightServo = hardwareMap.servo.get("rightZiplineServo");

        leftServo.setPosition(leftIn);
        rightServo.setPosition(rightIn);
    }

    public void setLeftIn(){
        leftServo.setPosition(leftIn);
    }

    public void setRightIn(){
        rightServo.setPosition(rightIn);
    }

    public void setLeftOut(){
        leftServo.setPosition(leftOut);
    }

    public void setRightOut(){
        rightServo.setPosition(rightOut);
    }
}
