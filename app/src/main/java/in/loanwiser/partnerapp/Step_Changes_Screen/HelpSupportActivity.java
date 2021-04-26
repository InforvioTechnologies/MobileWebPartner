package in.loanwiser.partnerapp.Step_Changes_Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import in.loanwiser.partnerapp.BankStamentUpload.ReportIssueActivity;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

public class HelpSupportActivity extends SimpleActivity {

    AppCompatButton emailsend_btn,reportissue_btn,crm_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_help_support);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this, R.layout.activity_help_support);
        initTools(R.string.help_sup);

        emailsend_btn=findViewById(R.id.emailsend_btn);
        reportissue_btn=findViewById(R.id.reportissue_btn);
        crm_btn=findViewById(R.id.crm_btn);

        reportissue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HelpSupportActivity.this,ReportIssueActivity.class);
                startActivity(intent);
            }
        });

        emailsend_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SENDTO);
               // i.setType("message/rfc822");
                i.setData(Uri.parse("mailto:")); // only email apps should handle this
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"help@loanwiser.in"});
                i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
                i.putExtra(Intent.EXTRA_TEXT   , "body of email");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(HelpSupportActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        crm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HelpSupportActivity.this, RelationshipContact.class));

            }
        });
    }
}