/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot.BeaconScorer;
import org.firstinspires.ftc.teamcode.robot.ClimberScorer;
import org.firstinspires.ftc.teamcode.robot.Drivetrain;
import org.firstinspires.ftc.teamcode.robot.Dumper;
import org.firstinspires.ftc.teamcode.robot.Intake;
import org.firstinspires.ftc.teamcode.robot.Lift;
import org.firstinspires.ftc.teamcode.robot.ZiplineScorer;

/**
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a PushBot
 * It includes all the skeletal structure that all iterative OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Teleop", group="Iterative Opmode")  // @Autonomous(...) is the other common choice

public class ClassifiedTeleop extends OpMode
{
    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();

    Lift lift = new Lift();
    Drivetrain drivetrain = new Drivetrain();
    Intake intake = new Intake();
    Dumper dumper = new Dumper();
    ZiplineScorer ziplineScorer = new ZiplineScorer();
    ClimberScorer climberScorer = new ClimberScorer();
    BeaconScorer beaconScorer = new BeaconScorer();

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        lift.init(hardwareMap);
        try {
            drivetrain.init(hardwareMap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        intake.init(hardwareMap);
        dumper.init(hardwareMap);
        ziplineScorer.init(hardwareMap);
        climberScorer.init(hardwareMap);
        beaconScorer.init(hardwareMap);

        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        telemetry.addData("Status", "Running: " + runtime.toString());

        drivetrain.tankDrive(-gamepad1.left_stick_y, -gamepad1.right_stick_y);//drivetrain.arcadeDrive(gamepad1.left_stick_y, gamepad1.right_stick_x);
        lift.setSpeed(-gamepad2.left_stick_y);


        if(gamepad1.left_bumper)
            intake.inward();

        if(gamepad1.right_bumper)
            intake.outward();

        if(!(gamepad1.right_bumper || gamepad1.left_bumper))
            intake.stop();



        if(gamepad2.left_bumper)
            ziplineScorer.setLeftOut();
        else
            ziplineScorer.setLeftIn();

        if(gamepad2.right_bumper)
            ziplineScorer.setRightOut();
        else
            ziplineScorer.setRightIn();


        if(gamepad2.a)
            climberScorer.score();

        if(gamepad2.b)
            climberScorer.reset();

        if(gamepad2.y)
            climberScorer.raise();;


        if(gamepad2.dpad_left)
            dumper.setLeft();

        if(gamepad2.dpad_right)
            dumper.setRight();

        if(!(gamepad2.dpad_left || gamepad2.dpad_right))
            dumper.setNeutral();
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
