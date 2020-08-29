package com.epam.university.java.core.task019;

public class RobotImpl implements Robot {
    public RobotPosition position;
    public RobotDirection direction;

    /**
     * Constructor.
     */
    public RobotImpl() {
        this.position = new RobotPositionImpl();
        position.setX(0);
        position.setY(0);
        direction = RobotDirection.UP;
    }

    /**
     * Get current robot position.
     *
     * @return position
     */
    @Override
    public RobotPosition getPosition() {
        return position;
    }

    /**
     * Set robot position.
     *
     * @param position position
     */
    @Override
    public void setPosition(RobotPosition position) {
        this.position = position;
    }

    /**
     * Invoke robot command.
     *
     * @param command command to invoke
     */
    @Override
    public void invokeAction(RobotCommand command) {
        switch (command) {
            case TURN_LEFT:
                turnLeft();
                break;
            case TURN_RIGHT:
                turnRight();
                break;
            case MOVE_FORWARD:
                moveForward();
                break;
            default:
                break;
        }
    }

    /**
     * Turn robot left.
     */
    public void turnLeft() {
        switch (direction) {
            case UP:
                direction = RobotDirection.LEFT;
                break;
            case LEFT:
                direction = RobotDirection.DOWN;
                break;
            case DOWN:
                direction = RobotDirection.RIGHT;
                break;
            case RIGHT:
                direction = RobotDirection.UP;
                break;
            default:
                break;
        }
    }

    /**
     * Turn robot right.
     */
    public void turnRight() {
        switch (direction) {
            case UP:
                direction = RobotDirection.RIGHT;
                break;
            case LEFT:
                direction =  RobotDirection.UP;
                break;
            case DOWN:
                direction =  RobotDirection.LEFT;
                break;
            case RIGHT:
                direction =  RobotDirection.DOWN;
                break;
            default:
                break;
        }
    }

    /**
     * Move robot forward.
     */
    public void moveForward() {
        switch (direction) {
            case UP:
                position.setY(position.getY() + 1);
                break;
            case LEFT:
                position.setX(position.getX() - 1);
                break;
            case DOWN:
                position.setY(position.getY() - 1);
                break;
            case RIGHT:
                position.setX(position.getX() + 1);
                break;
            default:
                break;
        }
    }
}
