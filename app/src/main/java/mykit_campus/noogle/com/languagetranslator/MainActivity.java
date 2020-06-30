package mykit_campus.noogle.com.languagetranslator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {
    Button translateButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialisation();
        listenerMethod();
    }
    void initialisation(){
       translateButton = (Button)findViewById(R.id.translateButton);
    }
    void listenerMethod(){
        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TranslateClass.class);
                startActivity(intent);
            }
        });
    }
}
