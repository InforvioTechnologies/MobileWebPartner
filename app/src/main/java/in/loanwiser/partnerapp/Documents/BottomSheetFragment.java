package in.loanwiser.partnerapp.Documents;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import in.loanwiser.partnerapp.CameraActivity.MainActivity;
import in.loanwiser.partnerapp.CameraActivity.ManiActivity_Image2;
import in.loanwiser.partnerapp.R;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     BottomSheetFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 */
public class BottomSheetFragment extends BottomSheetDialogFragment {

    Button cameralay,gallerylay,pdflay;

    public BottomSheetFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setStyle(DialogFragment.STYLE_NO_FRAME,0);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
      View view= inflater.inflate(R.layout.fragment_bottom_sheet_list_dialog, container, false);

        final Dialog dialog = new Dialog(getContext());
       // view.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
       /* cameralay=view.findViewById(R.id.cameralay);
        gallerylay=view.findViewById(R.id.gallerylay);
        pdflay=view.findViewById(R.id.pdflay);*/
      /*  cameralay.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Toast.makeText(getActivity(), "ok", Toast.LENGTH_LONG).show();
          }
      });*/

        cameralay=view.findViewById(R.id.cameralay);
        gallerylay=view.findViewById(R.id.gallerylay);
        pdflay=view.findViewById(R.id.pdflay);
        cameralay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ManiActivity_Image2.class);
                intent.putExtra("action", "1");
                startActivity(intent);
            }
        });

        gallerylay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ManiActivity_Image2.class);
                intent.putExtra("action", "2");
                startActivity(intent);
              /*  Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("action", "2");
                startActivity(intent);*/
            }
        });
        pdflay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ManiActivity_Image2.class);
                intent.putExtra("action", "3");
                startActivity(intent);

            }
        });

        return view;
    }
}