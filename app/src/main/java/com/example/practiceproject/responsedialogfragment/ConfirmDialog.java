package com.example.practiceproject.responsedialogfragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.practiceproject.R;
import com.example.practiceproject.retrofitservice.ApiService;
import com.example.practiceproject.retrofitservice.RetrofitService;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmDialog extends DialogFragment implements View.OnClickListener{
    public ImageView closeDialog;
    public static final String FIRST_NAME ="firstName";
    public static final String LAST_NAME ="lastName";
    public static final String EMAIL="email";
    public static final String GIT="git";

    public static final String SUCCESSFUL_TAG="Successful";
    public static final String UNSUCCESSFUL_TAG="Unsuccessful";

    public MaterialButton yes;

    public ConfirmDialog(){

    }
    @Override
    public void onStart(){
        super.onStart();
        getDialog().getWindow()
                .setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.confirm_dialog,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        yes=view.findViewById(R.id.yes);
        yes.setOnClickListener(this);

        closeDialog=view.findViewById(R.id.closeDialog);
        closeDialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.closeDialog:
                getDialog().dismiss();
                break;
            case R.id.yes:
                submitProject();
                break;
        }
    }

    private void submitProject() {
        ApiService service= RetrofitService.getFormApiService(ApiService.class);
        Bundle bundle=getArguments();

        Call<Void> call=service.submitForm(bundle.getString(FIRST_NAME),bundle.getString(LAST_NAME),
                bundle.getString(EMAIL),bundle.getString(GIT));

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    SuccessfulDialog success=new SuccessfulDialog();
                    getDialog().dismiss();
                    success.show(getFragmentManager(),"success");
                    Log.i(SUCCESSFUL_TAG,"Submission Successful");

                }
                else{
                    getDialog().dismiss();
                    UnsuccessfulDialog unsuccessful=new UnsuccessfulDialog();
                    unsuccessful.show(getFragmentManager(),"success");
                    Log.i(UNSUCCESSFUL_TAG,"Submission is Unsuccessful");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                getDialog().dismiss();
                UnsuccessfulDialog unsuccessful=new UnsuccessfulDialog();
                unsuccessful.show(getFragmentManager(),"success");
                Log.i(UNSUCCESSFUL_TAG,"Submission is Unsuccessful "+ t.getMessage());
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
