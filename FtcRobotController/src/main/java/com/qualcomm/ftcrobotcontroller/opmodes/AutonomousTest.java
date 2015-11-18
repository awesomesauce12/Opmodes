package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by Kashyap on 11/9/15.
 */
public class AutonomousTest extends OpMode{
    DcMotor frontMotorLeft;
    DcMotor frontMotorRight;
    DcMotor backMotorLeft;
    DcMotor backMotorRight;
    DcMotor axleMotorFront;
    DcMotor axleMotorBack;


    @Override
    public void init() {
        frontMotorLeft = hardwareMap.dcMotor.get("motor");
        frontMotorRight = hardwareMap.dcMotor.get("motor_2");
        backMotorLeft = hardwareMap.dcMotor.get("motor_3");
        backMotorRight = hardwareMap.dcMotor.get("motor_4");
        axleMotorFront = hardwareMap.dcMotor.get("motor_5");
        axleMotorBack = hardwareMap.dcMotor.get("motor_6");
    }

    @Override
    public void loop() {
        moveMotor(frontMotorLeft, 200, 0.5);
    }

    public void moveMotor(DcMotor motor, int position, double power) {
        motor.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motor.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motor.setTargetPosition(position);
        motor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        motor.setPower(power);
    }

    public void moveRobot(int position, double powerLeft, double powerRight, String direction) {
        if(direction.equals("forward")) {
            moveMotor(frontMotorLeft, position, powerLeft);
            moveMotor(frontMotorRight, position, powerRight);
            moveMotor(backMotorLeft, position, powerLeft);
            moveMotor(backMotorRight, position, powerRight);
        } else if(direction.equals("backward")) {
            moveMotor(frontMotorLeft, -position, powerLeft);
            moveMotor(frontMotorRight, -position, powerRight);
            moveMotor(backMotorLeft, -position, powerLeft);
            moveMotor(backMotorRight, -position, powerRight);
        }
    }

    public void turn(double degrees) {

    }
}