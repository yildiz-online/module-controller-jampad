/*
 *
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2018 Grégory Van den Borre
 *
 * More infos available: https://www.yildiz-games.be
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

package be.yildizgames.module.controller.internal;

import be.yildizgames.module.controller.Controller;
import be.yildizgames.module.controller.ControllerCurrentState;
import be.yildizgames.module.controller.ControllerListener;
import be.yildizgames.module.controller.ControllerMapper;

public abstract class ControllerRunner implements Runnable, Controller {

    private final Thread thread;

    private ControllerMapper mapper = new DefaultControllerMapper();

    /**
     * Flag to use or not the controller.
     */
    private boolean use;

    private final ControllerCurrentStateContainer stateContainer = new ControllerCurrentStateContainer();

    protected ControllerRunner() {
        super();
        this.thread = new Thread(this);
    }

    @Override
    public final ControllerCurrentState getState() {
        return this.stateContainer;
    }

    @Override
    public final void addListener(ControllerListener l) {
        this.stateContainer.addListener(l);
    }

    @Override
    public final void use() {
        this.use = true;
        this.thread.start();
    }

    @Override
    public final void map(ControllerMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean isUsed() {
        return this.use;
    }

    @Override
    public final void run() {
        while(use) {
            if(!updateIfConnected()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    protected abstract boolean down();

    protected abstract boolean up();

    protected abstract boolean right();

    protected abstract boolean left();

    protected abstract boolean button1();

    protected abstract boolean button2();

    protected abstract boolean button3();

    protected abstract boolean button4();

    protected abstract boolean buttonStart();

    protected abstract boolean buttonSelect();

    private boolean updateIfConnected() {
        boolean connected = this.isConnected();
        this.stateContainer.connected(connected);
        if(connected) {
            this.stateContainer.button(this.mapper.button1(), button1());
            this.stateContainer.button(this.mapper.button2(), button2());
            this.stateContainer.button(this.mapper.button3(), button3());
            this.stateContainer.button(this.mapper.button4(), button4());

            this.stateContainer.button(this.mapper.buttonStart(), buttonStart());
            this.stateContainer.button(this.mapper.buttonSelect(), buttonSelect());

            this.stateContainer.padLeft(left());
            this.stateContainer.padRight(right());
            this.stateContainer.padUp(up());
            this.stateContainer.padDown(down());

            return true;
        }
        return false;
    }
}