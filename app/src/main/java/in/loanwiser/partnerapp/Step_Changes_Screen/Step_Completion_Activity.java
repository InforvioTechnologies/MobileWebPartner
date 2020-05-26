package in.loanwiser.partnerapp.Step_Changes_Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import ernestoyaquello.com.verticalstepperform.VerticalStepperFormLayout;
import ernestoyaquello.com.verticalstepperform.interfaces.VerticalStepperForm;
import in.loanwiser.partnerapp.R;

public class Step_Completion_Activity extends AppCompatActivity implements VerticalStepperForm {
    private VerticalStepperFormLayout verticalStepperForm;

    private static final int TITLE_STEP_NUM = 0;
    private static final int DESCRIPTION_STEP_NUM = 1;
    private static final int TIME_STEP_NUM = 2;
    private static final int DAYS_STEP_NUM = 3;

    Button proceed_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step__completion_);
        proceed_button=(Button)findViewById(R.id.proceed_button);
        initializeActivity();

        proceed_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Step_Completion_Activity.this,Lead_Crration_Activity.class);
                startActivity(intent);
            }
        });
    }

    private void initializeActivity() {

        // Vertical Stepper form vars
        int colorPrimary = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary12);
        int colorPrimaryDark = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark12);
        String[] stepsNames = getResources().getStringArray(R.array.steps_names);

        // Here we find and initialize the form
        verticalStepperForm = (VerticalStepperFormLayout) findViewById(R.id.vertical_stepper_form);
        VerticalStepperFormLayout.Builder.newInstance(verticalStepperForm, stepsNames, this, this)
                .primaryColor(colorPrimary)
                .primaryDarkColor(colorPrimaryDark)
                .displayBottomNavigation(false)
                .init();

    }

    @Override
    public View createStepContentView(int stepNumber) {
        View view = null;
        switch (stepNumber) {
            case TITLE_STEP_NUM:
                view = stepOne();
                break;
            case DESCRIPTION_STEP_NUM:
                view = steoTwo();

                break;
            case TIME_STEP_NUM:
                view = stepThree();

                break;

        }
        return view;

    }

    @Override
    public void onStepOpening(int stepNumber) {

        switch (stepNumber) {
            case TITLE_STEP_NUM:
                verticalStepperForm.setStepAsCompleted(stepNumber);
                verticalStepperForm.setActiveStepAsCompleted();
                verticalStepperForm.setActivated(true);

            case DESCRIPTION_STEP_NUM:
                verticalStepperForm.setStepAsCompleted(stepNumber);
                verticalStepperForm.setActiveStepAsCompleted();
                verticalStepperForm.setActivated(true);



            case TIME_STEP_NUM:
                verticalStepperForm.setStepAsCompleted(stepNumber);
                verticalStepperForm.setActiveStepAsCompleted();
                verticalStepperForm.setActivated(false);


                break;

        }

    }

    @Override
    public void sendData() {
     /*   Intent intent=new Intent(Step_Completion_Activity.this,Lead_Crration_Activity.class);
        startActivity(intent);
*/
    }


    private View stepOne(){
        LayoutInflater inflater = LayoutInflater.from(getBaseContext());

        LinearLayout steponecontent=(LinearLayout)inflater.inflate(R.layout.stepone_view,null,false);
        TextView basiclead=(TextView)steponecontent.findViewById(R.id.basic_lead);
        TextView basicleaddesc=(TextView)steponecontent.findViewById(R.id.basic_leaddesc);



        return steponecontent;
    }

    private View steoTwo(){
        LayoutInflater inflater = LayoutInflater.from(getBaseContext());
        LinearLayout steptwocontent=(LinearLayout)inflater.inflate(R.layout.steptwo,null,false);


        return steptwocontent;
    }

    private View stepThree(){
        LayoutInflater inflater = LayoutInflater.from(getBaseContext());
        LinearLayout stepthreecontent=(LinearLayout)inflater.inflate(R.layout.step_three,null,false);

        return stepthreecontent;
    }
}
