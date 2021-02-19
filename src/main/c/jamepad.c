#include "jamepad.h"
#include <SDL.h>

#ifdef __cplusplus
extern "C" {
#endif

SDL_Event event;

JNIEXPORT jlong JNICALL Java_com_studiohartman_jamepad_ControllerIndex_nativeConnectController
  (JNIEnv *, jobject, jint index) {
        return (jlong) SDL_GameControllerOpen(index);
}

JNIEXPORT void JNICALL Java_com_studiohartman_jamepad_ControllerIndex_nativeClose
  (JNIEnv *, jobject, jlong controllerPtr) {
        SDL_GameController* pad = (SDL_GameController*) controllerPtr;
        if(pad && SDL_GameControllerGetAttached(pad)) {
            SDL_GameControllerClose(pad);
        }
        pad = NULL;
    }

JNIEXPORT jboolean JNICALL Java_com_studiohartman_jamepad_ControllerIndex_nativeIsConnected
  (JNIEnv *, jobject, jlong controllerPtr) {
    SDL_GameController* pad = (SDL_GameController*) controllerPtr;
    if (pad && SDL_GameControllerGetAttached(pad)) {
        return JNI_TRUE;
    }
    return JNI_FALSE;
}

JNIEXPORT jboolean JNICALL Java_com_studiohartman_jamepad_ControllerIndex_nativeDoVibration
  (JNIEnv *, jobject, jlong controllerPtr, jint leftMagnitude, jint rightMagnitude, jint duration_ms) {
    SDL_Joystick* joystick = SDL_GameControllerGetJoystick((SDL_GameController*) controllerPtr);
    return SDL_JoystickRumble(joystick, leftMagnitude, rightMagnitude,  duration_ms) == 0;
}

JNIEXPORT jboolean JNICALL Java_com_studiohartman_jamepad_ControllerIndex_nativeCheckButton
  (JNIEnv *, jobject, jlong controllerPtr, jint buttonIndex) {
    SDL_GameControllerUpdate();
    SDL_GameController* pad = (SDL_GameController*) controllerPtr;
    return SDL_GameControllerGetButton(pad, (SDL_GameControllerButton) buttonIndex);
}

JNIEXPORT jint JNICALL Java_com_studiohartman_jamepad_ControllerIndex_nativeCheckAxis
  (JNIEnv *, jobject, jlong controllerPtr, jint axisIndex) {
    SDL_GameControllerUpdate();
    SDL_GameController* pad = (SDL_GameController*) controllerPtr;
    return SDL_GameControllerGetAxis(pad, (SDL_GameControllerAxis) axisIndex);
}

JNIEXPORT jstring JNICALL Java_com_studiohartman_jamepad_ControllerIndex_nativeGetName
  (JNIEnv *env, jobject, jlong controllerPtr) {
    SDL_GameController* pad = (SDL_GameController*) controllerPtr;
    return env->NewStringUTF(SDL_GameControllerName(pad));
}

JNIEXPORT jboolean JNICALL Java_com_studiohartman_jamepad_ControllerManager_nativeInitSDLGamepad
  (JNIEnv *, jobject) {
    if (SDL_Init(SDL_INIT_EVENTS | SDL_INIT_JOYSTICK | SDL_INIT_GAMECONTROLLER) != 0) {
        printf("NATIVE METHOD: SDL_Init failed: %s\n", SDL_GetError());
        return JNI_FALSE;
    }

            //We don't want any controller connections events (which are automatically generated at init)
            //since they interfere with us detecting new controllers, so we go through all events and clear them.
    while (SDL_PollEvent(&event));
    return JNI_TRUE;
}


JNIEXPORT void JNICALL java_com_studiohartman_jamepad_ControllerManager_nativeCloseSDLGamepad(JNIEnv *env, jobject object) {
            SDL_Quit();
        }

JNIEXPORT jint JNICALL java_com_studiohartman_jamepad_ControllerManager_nativeGetNumRollers(JNIEnv *env, jobject object) {
            int numJoysticks = SDL_NumJoysticks();

            int numGamepads = 0;

            for(int i = 0; i < numJoysticks; i++) {
                if(SDL_IsGameController(i)) {
                    numGamepads++;
                }
            }

            return numGamepads;
        }
		
JNIEXPORT jboolean JNICALL Java_com_studiohartman_jamepad_ControllerManager_nativeControllerConnectedOrDisconnected
  (JNIEnv *, jobject) {
    SDL_JoystickUpdate();
    while (SDL_PollEvent(&event)) {
        if (event.type == SDL_JOYDEVICEADDED || event.type == SDL_JOYDEVICEREMOVED) {
            return JNI_TRUE;
        }
    }
    return JNI_FALSE;
}

JNIEXPORT jboolean JNICALL Java_com_studiohartman_jamepad_ControllerManager_nativeAddMappingsFromFile
  (JNIEnv *env, jobject, jstring jpath) {
			const char* path = env->GetStringUTFChars(jpath, 0);
	
            if(SDL_GameControllerAddMappingsFromFile(path) < 0) {
                printf("NATIVE METHOD: Failed to load mappings from \"%s\"\n", path);
                printf("               %s\n", SDL_GetError());
				env->ReleaseStringUTFChars(jpath, path);
                return JNI_FALSE;
            }

            return JNI_TRUE;
        }

#ifdef __cplusplus
}
#endif