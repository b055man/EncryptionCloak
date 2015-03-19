package com.mooo.bossman.encryptioncloak;

/**
 * Created by b055man on 18/03/2015.
 */
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;

public class EncryptionFaker implements IXposedHookLoadPackage {
    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
        //XposedBridge.log("Loaded app: " + lpparam.packageName);

        if (lpparam.packageName.equals("com.google.android.apps.enterprise.dmagent")) {
            findAndHookMethod("android.app.admin.DevicePolicyManager", lpparam.classLoader, "getStorageEncryptionStatus", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("AAAA being asked for encryption! by: " + lpparam.packageName);
                    param.setResult(android.app.admin.DevicePolicyManager.ENCRYPTION_STATUS_ACTIVE);
                }
            });
        }
    }
}
