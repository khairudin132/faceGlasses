package com.merge.user.aninterface.round;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.ar.core.ArCoreApk;
import com.merge.user.aninterface.FaceArFragment;
import com.merge.user.aninterface.R;
import com.merge.user.aninterface.mask.MainMask;

public class MainRound extends AppCompatActivity {

    private static final String TAG = MainRound.class.getSimpleName();
    private static final double MIN_OPENGL_VERSION = 3.0;
    private FaceArFragment arFragment;

    //declare button
    private Button gramercybtn;
    private Button wayfarerbtn;
    private Button marcybtn;
    private Button billiebtn;
    private Button carlsunglassesbtn;
    private Button back;
    //---------------------

    @Override
    @SuppressWarnings({"AndroidApiChecker", "FutureReturnValueIgnored"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!checkIsSupportedDeviceOrFinish(this)) {
            return;
        }

        setContentView(R.layout.round_face_ar);
        gramercybtn = (Button)findViewById(R.id.gramercy2button);
        wayfarerbtn = (Button)findViewById(R.id.wayfarerbutton);
        marcybtn = (Button)findViewById(R.id.marcybutton);
        billiebtn = (Button)findViewById(R.id.billiebutton);
        carlsunglassesbtn = (Button)findViewById(R.id.carlsunglassesbutton);
        back = (Button)findViewById(R.id.back);
        arFragment = (FaceArFragment)getSupportFragmentManager().findFragmentById(R.id.face_fragment);

        //assign listener to button
        gramercybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGramercy();
            }
        });

        wayfarerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToWayfarer();
            }
        });

        marcybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMarcy();
            }
        });

        billiebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToBillie();
            }
        });

        carlsunglassesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCarlsunglasses();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToBack();
            }
        });

    }

    public void goToGramercy() {
        Intent intent = new Intent(this, Gramercy.class);
        String message = "gramercy glasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public void goToWayfarer() {
        Intent intent = new Intent(this, Wayfarer.class);
        String message = "wayfarer glasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public void goToMarcy() {
        Intent intent = new Intent(this, Marcy.class);
        String message = "marcy glasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public void goToBillie() {
        Intent intent = new Intent(this, Billie.class);
        String message = "billie glasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public void goToCarlsunglasses() {
        Intent intent = new Intent(this, CarlSunglasses.class);
        String message = "carl sunglasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public void goToBack() {
        Intent intent = new Intent(this, MainMask.class);
        String message = "roundframe glasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public static boolean checkIsSupportedDeviceOrFinish(final Activity activity) {
        if (ArCoreApk.getInstance().checkAvailability(activity)
                == ArCoreApk.Availability.UNSUPPORTED_DEVICE_NOT_CAPABLE) {
            Log.e(TAG, "Augmented Faces requires ArCore.");
            Toast.makeText(activity, "Augmented Faces requires ArCore", Toast.LENGTH_LONG).show();
            activity.finish();
            return false;
        }
        String openGlVersionString =
                ((ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE))
                        .getDeviceConfigurationInfo()
                        .getGlEsVersion();
        if (Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
            Log.e(TAG, "Sceneform requires OpenGL ES 3.0 later");
            Toast.makeText(activity, "Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG)
                    .show();
            activity.finish();
            return false;
        }
        return true;
    }
}
