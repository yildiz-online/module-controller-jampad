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

import be.yildizgames.module.controller.Controller;
import be.yildizgames.module.controller.ControllerEngine;
import com.studiohartman.jamepad.ControllerManager;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Gregory Van den Borre
 */
public class JampadControllerManager implements ControllerEngine {

    private final Controller[] controllers = new JamPadControllerRunner[4];

    public JampadControllerManager() {
        super();
        loadForArm64();
        var controllerManager = new ControllerManager();
        controllerManager.initSDLGamepad();
        for(int i = 0; i < controllers.length; i++) {
            controllers[i] = new JamPadControllerRunner(i, controllerManager);
        }
    }

    /**
     * 64 bits arm lib does not exists in the jamepad jar, so load the added one in this library.
     */
    private static void loadForArm64() {
        var arch = System.getProperty("os.arch");
        var isARM = arch.startsWith("arm") || arch.startsWith("aarch64");
        var is64Bit = arch.contains("64") || arch.startsWith("armv8");
        var libName = "libjamepadArm64.so";
        if(is64Bit && isARM) {
            try {
                var url = JampadControllerManager.class.getResource("/" + libName);
                File nativeLibFile = new File("libjamepadArm64.so");
                if(Files.notExists(Path.of(libName))) {
                    try (var in = url.openStream()) {
                        Files.copy(in, nativeLibFile.toPath());
                    }
                }
                System.load(nativeLibFile.getAbsolutePath());
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
    }

    @Override
    public final Controller getController1() {
        return controllers[0];
    }

    @Override
    public final Controller getController2() {
        return controllers[1];
    }

    @Override
    public final Controller getController3() {
        return controllers[2];
    }

    @Override
    public final Controller getController4() {
        return controllers[3];
    }
}
