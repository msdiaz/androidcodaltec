package mundial.initics.martes;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainActivity extends AppCompatActivity implements PatternLockViewListener {


    SharedPreferences SP;
    SharedPreferences.Editor EDITOR;

    Button rgistrar,login;

    PatternLockView mPatternLockView;
    String patron;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imagen = (ImageView)findViewById(R.id.imageView2);

        Picasso.with(this).load("http://www.initics.com/img/logo.png").into(imagen);

        mPatternLockView = (PatternLockView) findViewById(R.id.pattern_lock_view);
        mPatternLockView.addPatternLockListener(this);

        SP=getSharedPreferences("patron", Context.MODE_PRIVATE);
        EDITOR = SP.edit();

        rgistrar=(Button)findViewById(R.id.btn_registrar);
        rgistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EDITOR.putString("patron_key",patron);
                EDITOR.apply();
                mPatternLockView.clearPattern();
            }
        });

        login=(Button)findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SP.getString("patron_key","").equalsIgnoreCase(patron)){
                    Toast.makeText(getApplicationContext(),"INGRESO CORRECTO",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"ERROR , ERROR , ERROR",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onStarted() {
    }

    @Override
    public void onProgress(List<PatternLockView.Dot> progressPattern) {
    }

    @Override
    public void onComplete(List<PatternLockView.Dot> pattern) {
        patron = PatternLockUtils.patternToString(mPatternLockView, pattern);

    }

    @Override
    public void onCleared() {

    }
}
