package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by Kashyap on 11/4/15.
 */
public class Autonomous extends OpMode {
    final static int ENCODER_CPR = 1120;
    final static double GEAR_RATIO = 1;
    final static double WHEEL_CIRCUMFERENCE = 7.85;

    DcMotor frontMotorLeft;
    DcMotor frontMotorRight;
    DcMotor backMotorLeft;
    DcMotor backMotorRight;
    DcMotor axleMotorFront;
    DcMotor axleMotorBack;

    public Autonomous() {

    }

    //Input: Distance in inches
    //Output: Distance in encoder pulses
    public void moveMotor(DcMotor motor, double distance, double power) {
        double encoderClicks = (distance / WHEEL_CIRCUMFERENCE) * GEAR_RATIO * ENCODER_CPR;
        int position = (int) encoderClicks;
        motor.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motor.setTargetPosition(position);
        motor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        motor.setPower(power);
    }

    public void moveMotorAlt(DcMotor motor, int position, double power) {
        motor.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motor.setTargetPosition(position);
        motor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        motor.setPower(power);
    }

    public void moveRobot(int distance, double power, String direction) {
        if(direction.equals("forward")) {
            moveMotor(frontMotorLeft, distance, power);
            moveMotor(frontMotorRight, distance, power);
            moveMotor(backMotorLeft, distance, power);
            moveMotor(backMotorRight, distance, power);
        } else if(direction.equals("backward")) {
            moveMotor(frontMotorLeft, distance, -power);
            moveMotor(frontMotorRight, distance, -power);
            moveMotor(backMotorLeft, distance, -power);
            moveMotor(backMotorRight, distance, -power);
        }
    }

    public void turn(int position, String direction) {
        if (direction.equals("left")) {
            moveMotorAlt(frontMotorLeft, position / 2, -0.5);
            moveMotorAlt(frontMotorRight, position / 2, 0.5);
            moveMotorAlt(backMotorLeft, position / 2, -0.5);
            moveMotorAlt(backMotorRight, position / 2, 0.5);
        } else if(direction.equals("right")) {
            moveMotorAlt(frontMotorLeft, position / 2, 0.5);
            moveMotorAlt(frontMotorRight, position / 2, -0.5);
            moveMotorAlt(backMotorLeft, position / 2, 0.5);
            moveMotorAlt(backMotorRight, position / 2, -0.5);
        }

    }


    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }
}