package heenstudy.com.attackboundservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "BoundServiceAttack";
    private EditText editCommand = null;
    private TextView txtResult = null;
    private Button btnBind = null;

    boolean mBound = false;
    private ServiceConnection mServiceConnection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editCommand = (EditText) findViewById(R.id.command);
        txtResult = (TextView) findViewById(R.id.textResult);
        btnBind = (Button)findViewById(R.id.bind);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBound)
            realseService();
    }

    private void realseService() {
        unbindService(mServiceConnection);
        mServiceConnection = null;
        mBound = false;
        Log.d(TAG, "releaseService() unbound.");
    }

    private void initService() {
        mServiceConnection = new myServiceConnection();
    }

    class myServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "OnServiceConnected ");
            String command = editCommand.getText().toString();

            try {
                a mTestService = Stub.asInterface(service);
                String result = mTestService.a(command);
                Log.d(TAG, "exec result is:" + result);
                txtResult.setText(result);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnect()");
            mBound = false;
            mServiceConnection = null;
        }
    }

    public void onBindButtonClick(View v) {
        if (!mBound) {
            initService();
            Intent i = new Intent();
            i.setClassName("com.jakev.boundserver", "com.jakev.boundserver.ITestService");
            boolean ret = bindService(i, mServiceConnection, BIND_AUTO_CREATE);
            Log.d(TAG, "initService() bound with " + ret);
            mBound = ret;
            btnBind.setText("Unbind");
        } else{
            realseService();
            btnBind.setText("Bind");
        }
    }
}

