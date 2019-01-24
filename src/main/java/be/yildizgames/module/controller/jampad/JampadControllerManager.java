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

package be.yildizgames.module.controller.jampad;

import be.yildizgames.module.controller.internal.ControllerCurrentStateContainer;
import com.studiohartman.jamepad.ControllerState;
import com.studiohartman.jamepad.ControllerManager;

public class JampadControllerManager {

    private final Thread thread = new Thread(new ControllerRunner(0));

    private ControllerManager controllers;

    public void initialize() {
        this.controllers = new ControllerManager();
        controllers.initSDLGamepad();
    }

    public final void start() {
        this.thread.start();
    }

    private class ControllerRunner implements Runnable {

        private final int id;

        /**
         * Flag to use or not the controller.
         */
        private boolean use;

        private final ControllerCurrentStateContainer stateContainer = new ControllerCurrentStateContainer();

        private ControllerRunner(final int id) {
            super();
            this.id = id;
        }

        @Override
        public void run() {
            while(true) {
                if(!use && !updateIfConnected()) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        private boolean updateIfConnected() {
            ControllerState currState = controllers.getState(id);
            this.stateContainer.connected(currState.isConnected);
            if(currState.isConnected) {
                this.stateContainer.button1(currState.a);
                this.stateContainer.button2(currState.b);
                this.stateContainer.button3(currState.x);
                this.stateContainer.button4(currState.y);

                this.stateContainer.buttonStart(currState.start);

                this.stateContainer.padLeft(currState.dpadLeft);
                this.stateContainer.padRight(currState.dpadRight);
                this.stateContainer.padUp(currState.dpadUp);
                this.stateContainer.padDown(currState.dpadDown);

                return true;
            }
            return false;
        }
    }

}


}
