package com.merge.user.aninterface.oval;

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
import com.merge.user.aninterface.heart.VictorRectangular;
import com.merge.user.aninterface.mask.MainMask;

public class MainOval extends AppCompatActivity {

    private static final String TAG = MainOval.class.getSimpleName();
    private static final double MIN_OPENGL_VERSION = 3.0;
    private FaceArFragment arFragment;

    //declare button
    private Button aviatorbtn;
    private Button roundframebtn;
    private Button squareglassesbtn;
    private Button victorrectangularbtn;
    private Button squaresunglassesbtn;
    private Button back;
    //---------------------

    @Override
    @SuppressWarnings({"AndroidApiChecker", "FutureReturnValueIgnored"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!checkIsSupportedDeviceOrFinish(this)) {
            return;
        }

        setContentView(R.layout.oval_face_ar);
        aviatorbtn = (Button)findViewById(R.id.new_aviatorbutton);
        roundframebtn = (Button)findViewById(R.id.round_framebutton);
        squareglassesbtn = (Button)findViewById(R.id.square_glassesbutton);
        victorrectangularbtn = (Button)findViewById(R.id.victor_rectangularbutton);
        squaresunglassesbtn = (Button)findViewById(R.id.square_sunglassesbutton);
        back = (Button)findViewById(R.id.back);
        arFragment = (FaceArFragment)getSupportFragmentManager().findFragmentById(R.id.face_fragment);

        //assign listener to button
        aviatorbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAviator();
            }
        });

        roundframebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRoundframe();
            }
        });

        squareglassesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSquareglasses();
            }
        });

        victorrectangularbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToVictorrectangular();
            }
        });

        squaresunglassesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSquaresunglasses();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToBack();
            }
        });

    }

    public void goToAviator() {
        Intent intent = new Intent(this, Aviator.class);
        String message = "aviator glasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public void goToRoundframe() {
        Intent intent = new Intent(this, RoundFrameOval.class);
        String message = "roundframe glasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public void goToSquareglasses() {
        Intent intent = new Intent(this, SquareGlasses.class);
        String message = "square glasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public void goToVictorrectangular() {
        Intent intent = new Intent(this, VictorRectangularOval.class);
        String message = "victor rectangular glasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public void goToSquaresunglasses() {
        Intent intent = new Intent(this, SquareSunglasses.class);
        String message = "square sunglasses";
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
