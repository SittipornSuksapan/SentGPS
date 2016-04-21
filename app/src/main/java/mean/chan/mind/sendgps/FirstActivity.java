package mean.chan.mind.sendgps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    } //main mathod

    public void clickAddPlate(View view) {
        startActivity(new Intent(FirstActivity.this,MainActivity.class));
    }
} //main class
