package com.ppp.sunmit2.contracts;

import android.os.RemoteException;

public interface SunmiCallback {
    void onRunResult(boolean isSuccess) throws RemoteException;
    void onReturnString(String result) throws RemoteException;
    void onRaiseException(int code, String msg) throws RemoteException;
    void onPrintResult(int code, String msg) throws RemoteException;

}