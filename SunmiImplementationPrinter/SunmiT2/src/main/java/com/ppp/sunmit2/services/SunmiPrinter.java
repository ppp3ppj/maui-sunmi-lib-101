package com.ppp.sunmit2.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.RemoteException;

import com.ppp.sunmit2.contracts.SunmiCallback;

// woyou import aidl
import woyou.aidlservice.jiuiv5.ICallback;
import woyou.aidlservice.jiuiv5.IWoyouService;

public class SunmiPrinter {
    private static final SunmiPrinter printer = new SunmiPrinter();
    private IWoyouService woyouService = null;
    private final String UNREACHABLE = "unreachable";

    private ServiceConnection connService = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            woyouService = null;
        }
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            woyouService = IWoyouService.Stub.asInterface(service);
        }
    };

    private SunmiPrinter(){}


    public static SunmiPrinter getInstance() {
        return printer;
    }

    public void initPrinter(Context context){
        Intent intent=new Intent();
        intent.setPackage("woyou.aidlservice.jiuiv5");
        intent.setAction("woyou.aidlservice.jiuiv5.IWoyouService");
        context.startService(intent);
        context.bindService(intent, connService, Context.BIND_AUTO_CREATE);
    }

    // --------------------------------

    public int getFirmwareStatus() {
        if (woyouService != null) {
            try {
                return woyouService.getFirmwareStatus();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public String getServiceVersion() {
        if (woyouService != null) {
            try {
                return woyouService.getServiceVersion();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return UNREACHABLE;
    }

    public void printerInit(SunmiCallback callback) {
        if (woyouService != null) {
            try {
                woyouService.printerInit(makeCallback(callback));
            } catch (RemoteException e) {
                e.printStackTrace();
                ThrowErrorCallback(callback);
            }
        }
        else {
            ThrowErrorCallback(callback);
        }
    }

    public void printerSelfChecking(SunmiCallback callback) {
        if (woyouService != null) {
            try {
                woyouService.printerSelfChecking(makeCallback(callback));
            } catch (RemoteException e) {
                e.printStackTrace();
                ThrowErrorCallback(callback);
            }
        }
        else {
            ThrowErrorCallback(callback);
        }
    }

    public String getPrinterSerialNo() {
        if (woyouService != null) {
            try {
                return woyouService.getPrinterSerialNo();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return UNREACHABLE;
    }

    public String getPrinterVersion() {
        if (woyouService != null) {
            try {
                return woyouService.getPrinterVersion();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return UNREACHABLE;
    }

    public String getPrinterModal() {
        if (woyouService != null) {
            try {
                return woyouService.getPrinterModal();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return UNREACHABLE;
    }

    private void ThrowErrorCallback(SunmiCallback callback) {
        if (callback != null) {
            try {
                callback.onRaiseException(500, UNREACHABLE);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private ICallback makeCallback(final SunmiCallback callback) {
        if (callback != null) {
            return new ICallback.Stub() {
                @Override
                public void onRunResult(boolean isSuccess) throws RemoteException {
                    callback.onRunResult(isSuccess);
                }

                @Override
                public void onReturnString(String result) throws RemoteException {
                    callback.onReturnString(result);
                }

                @Override
                public void onRaiseException(int code, String msg) throws RemoteException {
                    callback.onRaiseException(code, msg);
                }

                @Override
                public void onPrintResult(int code, String msg) throws RemoteException {
                    callback.onPrintResult(code, msg);
                }

            };
        }
        return null;
    }

}