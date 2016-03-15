package heenstudy.com.attackboundservice;

/**
 * Created by heeeeen on 16/3/8.
 */

import android.os.IInterface;
import android.os.RemoteException;

// in fact a is TestInterface
public interface a extends IInterface {
    static final String DESCRIPTOR = "com.jakev.boundserver.aidl.TestInterface";
    String a(String arg1) throws RemoteException;
}
