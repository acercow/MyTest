package com.archzues.mytest.binderpool;

import android.os.RemoteException;

import com.archzues.mytest.pool.ICompute;

/**
 * Created by acercow on 18-9-11.
 */

public class ComputeImpl extends ICompute.Stub {
    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}
