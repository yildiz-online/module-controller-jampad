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

package be.yildizgames.module.controller;

/**
 * Notify when the controller is updated.
 *
 * @author Grégory Van den Borre
 */
public interface ControllerListener {

    /**
     * Triggered when the controller gets connected.
     */
    default void controllerConnected() {}

    /**
     * Triggered when the controller gets disconnected.
     */
    default void controllerDisconnected() {}

    /**
     * Triggered when the controller button 1 is pressed.
     */
    default void controllerPress1() {}

    /**
     * Triggered when the controller button 1 is released.
     */
    default void controllerRelease1() {}

    /**
     * Triggered when the controller button 2 is pressed.
     */
    default void controllerPress2() {}

    /**
     * Triggered when the controller button 2 is released.
     */
    default void controllerRelease2() {}

    /**
     * Triggered when the controller button 3 is pressed.
     */
    default void controllerPress3() {}

    /**
     * Triggered when the controller button 3 is released.
     */
    default void controllerRelease3() {}

    /**
     * Triggered when the controller button 4 is pressed.
     */
    default void controllerPress4() {}

    /**
     * Triggered when the controller button 4 is released.
     */
    default void controllerRelease4() {}

    /**
     * Triggered when the controller button start is pressed.
     */
    default void controllerPressStart() {}

    /**
     * Triggered when the controller button start is released.
     */
    default void controllerReleaseStart() {}

    /**
     * Triggered when the controller button select is pressed.
     */
    default void controllerPressSelect() {}

    /**
     * Triggered when the controller button select is released.
     */
    default void controllerReleaseSelect() {}

    /**
     * Triggered when the controller dpad left is pressed.
     */
    default void controllerPressLeft() {}

    /**
     * Triggered when the controller dpad left is released.
     */
    default void controllerReleaseLeft() {}

    /**
     * Triggered when the controller dpad up is pressed.
     */
    default void controllerPressUp() {}

    /**
     * Triggered when the controller dpad up is released.
     */
    default void controllerReleaseUp() {}

    /**
     * Triggered when the controller dpad right is pressed.
     */
    default void controllerPressRight() {}

    /**
     * Triggered when the controller dpad right is released.
     */
    default void controllerReleaseRight() {}

    /**
     * Triggered when the controller dpad down is pressed.
     */
    default void controllerPressDown() {}

    /**
     * Triggered when the controller dpad down is released.
     */
    default void controllerReleaseDown() {}

    default void controllerPressLeftStickLeft() {
    }

    default void controllerReleaseLeftStickLeft() {
    }

    default void controllerPressLeftStickRight() {
    }

    default void controllerReleaseLeftStickRight() {
    }

    default void controllerPressLeftStickUp() {
    }

    default void controllerReleaseLeftStickUp() {
    }

    default void controllerPressLeftStickDown() {
    }

    default void controllerReleaseLeftStickDown() {
    }
}
