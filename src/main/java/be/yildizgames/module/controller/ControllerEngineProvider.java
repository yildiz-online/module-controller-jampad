package be.yildizgames.module.controller;

import be.yildizgames.module.controller.jampad.JampadControllerManager;

public class ControllerEngineProvider {

    public ControllerEngine get() {
        return new JampadControllerManager();
    }

}
