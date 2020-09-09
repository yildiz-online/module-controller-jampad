/*
 * MIT License
 *
 * Copyright (c) 2019 Grégory Van den Borre
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package be.yildizgames.module.controller.internal;

import be.yildizgames.module.controller.ControllerEngine;
import be.yildizgames.module.controller.ControllerListener;
import be.yildizgames.module.controller.jampad.JampadControllerManager;

/**
 * @author Grégory Van den Borre
 */
public class Manual {

    public static void main(String [] args) {
        var engine = new JampadControllerManager();
        engine.getController1().use();
        engine.getController1().addListener(new ControllerListener() {
            @Override
            public void controllerPressLeft() {
                System.out.println("LEFT");
            }

            @Override
            public void controllerPressStart() {
                System.out.println("START");
            }

            @Override
            public void controllerPressSelect() {
                System.out.println("SELECT");
            }

            @Override
            public void controllerPressLeftStickDown() {
                System.out.println("LEFT STICK DOWN");
            }

            @Override
            public void controllerPressLeftStickUp() {
                System.out.println("LEFT STICK UP");
            }

            @Override
            public void controllerPressLeftStickLeft() {
                System.out.println("LEFT STICK LEFT");
            }

            @Override
            public void controllerPressLeftStickRight() {
                System.out.println("LEFT STICK RIGHT");
            }
        });
    }

}
