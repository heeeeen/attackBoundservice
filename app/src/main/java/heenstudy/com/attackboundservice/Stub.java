package heenstudy.com.attackboundservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * Created by heeeeen on 16/3/8.
 */
public class Stub extends Binder implements a {

    /** Construct the stub at attach it to the interface. */
    public Stub() {
        super();
        this.attachInterface(this, DESCRIPTOR);
    }

    /** Cast an IBinder object into an ITestInterface(heenstudy.com.attackboundservice.a) interface,
     * generating a proxy if needed
    */
    public static a asInterface(IBinder obj) {
        if (obj == null) {
            return null;
        }
        IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
        if(((iin != null) && (iin instanceof a))) {
            return (a)iin;
        }

        return new Stub.Proxy(obj);
    }

    public IBinder asBinder() {
        return this;
    }

    public boolean onTransact(int code, Parcel data, Parcel reply, int flag) throws RemoteException{
        boolean v0 = true;
        switch(code) {
            case 1: {
                data.enforceInterface(DESCRIPTOR);
                String v1 = this.a(data.readString());
                reply.writeNoException();
                reply.writeString(v1);
                break;
            }
            case 1598968902: {
                reply.writeString(DESCRIPTOR);
                break;
            }
            default: {
                v0 = super.onTransact(code, data, reply, flag);
                break;
            }
        }

        return v0;
    }

    public  String a(String cmd) throws RemoteException {
        // we are not bind server, do not have to implement this method, so just return null
        return null;
    }

    private static class Proxy implements a {
        private IBinder mRemote;

        Proxy(IBinder remote) {
            mRemote = remote;
        }

        @Override
        public IBinder asBinder() {
            return mRemote;
        }

        public String getInterfaceDescriptor() {
            return DESCRIPTOR;
        }

        @Override
        public String a(String cmd) throws RemoteException{
            String result = null;
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            try {
                data.writeInterfaceToken(DESCRIPTOR);
                data.writeString(cmd);
                mRemote.transact(1, data, reply, 0);
                reply.readException();
                result = reply.readString();
            }
            finally {
                reply.recycle();
                data.recycle();
            }

            return result;
        }
    }



}
