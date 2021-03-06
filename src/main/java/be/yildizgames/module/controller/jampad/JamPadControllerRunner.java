/*
 *
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2019 Grégory Van den Borre
 *
 * More infos available: https://engine.yildiz-games.be
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 *  portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 *  WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 *  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 *
 *
 */

package be.yildizgames.module.controller.jampad;

import be.yildizgames.module.controller.ControllerModel;
import be.yildizgames.module.controller.internal.ControllerRunner;
import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;

/**
 * @author Gregory Van den Borre
 */
class JamPadControllerRunner extends ControllerRunner {

    private final int id;

    private final ControllerManager controllerManager;

    private ControllerState currState;

    private final JampadControllerModelMapper modelMapper = new JampadControllerModelMapper();

    JamPadControllerRunner(final int id, ControllerManager controllerManager) {
        super();
        this.id = id;
        this.controllerManager = controllerManager;
        this.currState = controllerManager.getState(this.id);
    }

    @Override
    protected boolean button1() {
        return currState.a;
    }

    @Override
    protected boolean button2() {
        return currState.b;
    }

    @Override
    protected boolean button3() {
        return currState.x;
    }

    @Override
    protected boolean button4() {
        return currState.y;
    }

    @Override
    protected boolean buttonL1() {
        return currState.lb;
    }

    @Override
    protected boolean buttonL2() {
        return currState.leftTrigger > 0;
    }

    @Override
    protected boolean buttonR1() {
        return currState.rb;
    }

    @Override
    protected boolean buttonR2() {
        return currState.rightTrigger > 0;
    }

    @Override
    protected boolean buttonStart() {
        return this.currState.start;
    }

    @Override
    protected boolean buttonSelect() {
        return this.currState.back;
    }

    @Override
    protected boolean down() {
        return currState.dpadDown;
    }

    @Override
    protected boolean up() {
        return currState.dpadUp;
    }

    @Override
    protected boolean right() {
        return currState.dpadRight;
    }

    @Override
    protected boolean left() {
        return currState.dpadLeft;
    }

    @Override
    protected final float leftStickVertical() {
        return currState.leftStickY;
    }

    @Override
    protected final float leftStickHorizontal() {
        return currState.leftStickX;
    }


    @Override
    public boolean isConnected() {
        this.currState = this.controllerManager.getState(id);
        return currState.isConnected;
    }

    @Override
    public ControllerModel getModel() {
        System.out.println(this.currState.controllerType);
        return this.modelMapper.map(this.currState.controllerType);
    }
}
