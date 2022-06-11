package com.example.attendanceapp;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class MyDialog extends DialogFragment {
    public static final String Class_Add_Dialog="addClass";
    public static final String Class_Update_Dialog="updateClass";
    public static final String STUDENT_Add_Dialog="addStudent";
    public static final String Student_Update_Dialog="updateStudent";
    private OnClickListener listener;
    private int roll;
    private String name;
    public MyDialog(int roll, String name) {
        this.roll=roll;
        this.name=name;
    }
    public MyDialog() {
    }
    public interface OnClickListener
    {
        void onClick(String text1,String text2);
    }
    public void setListener(OnClickListener listener)
    {
        this.listener=listener;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
       Dialog dialog=null;
       if(getTag().equals(Class_Add_Dialog))dialog=getAddClassDialog();
       if(getTag().equals(STUDENT_Add_Dialog))dialog=getAddStudentDialog();
       if(getTag().equals(Class_Update_Dialog))dialog=getUpdateClassDialog();
        if(getTag().equals(Student_Update_Dialog))dialog=getUpdateStudentDialog();
       dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
       return dialog;
    }
    private Dialog getUpdateStudentDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        View view= LayoutInflater.from(getActivity()).inflate(R.layout.dialog,null);
        builder.setView(view);
        TextView title=view.findViewById(R.id.titleDialog);
        title.setText("Update Student");
        EditText roll_edt=view.findViewById(R.id.edt01);
        EditText name_edt=view.findViewById(R.id.edt02);
        roll_edt.setHint("Roll");
        name_edt.setHint("Name");
        Button cancel=view.findViewById(R.id.cancel_btn);
        Button add=view.findViewById(R.id.add_btn);
        add.setText("update");
        roll_edt.setText(roll+"");
        roll_edt.setEnabled(false);
        name_edt.setText(name);
        cancel.setOnClickListener(view1 -> dismiss());
        add.setOnClickListener(view1 -> {
            if(roll_edt.length()==0)
            {
                roll_edt.requestFocus();
                roll_edt.setError("This field is required & Enter Only Integer Value");
            }
            else if (name_edt.length()==0)
            {
                name_edt.requestFocus();
                name_edt.setError("This field is required");
            }
            else {
                String roll = roll_edt.getText().toString();
                String name = name_edt.getText().toString();
                listener.onClick(roll, name);
                dismiss();
            }
        });
        return builder.create();
    }
    private Dialog getUpdateClassDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        View view= LayoutInflater.from(getActivity()).inflate(R.layout.dialog,null);
        builder.setView(view);
        TextView title=view.findViewById(R.id.titleDialog);
        title.setText("Update Class");
        EditText class_edt=view.findViewById(R.id.edt01);
        EditText subject_edt=view.findViewById(R.id.edt02);
        class_edt.setHint("Class Name");
        subject_edt.setHint("Subject Name");
        Button cancel=view.findViewById(R.id.cancel_btn);
        Button add=view.findViewById(R.id.add_btn);
        add.setText("Update");
        cancel.setOnClickListener(view1 -> dismiss());
        add.setOnClickListener(view1 -> {
            if(class_edt.length()==0)
            {
                class_edt.requestFocus();
                class_edt.setError("This field is required");
            }
            else if (subject_edt.length()==0)
            {
                subject_edt.requestFocus();
                subject_edt.setError("This field is required");
            }
            else {
                String className = class_edt.getText().toString();
                String subName = subject_edt.getText().toString();
                listener.onClick(className, subName);
                dismiss();
            }
        });
        return builder.create();
    }

    private Dialog getAddStudentDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        View view= LayoutInflater.from(getActivity()).inflate(R.layout.dialog,null);
        builder.setView(view);
        TextView title=view.findViewById(R.id.titleDialog);
        title.setText("Add New Student");

        EditText roll_edt=view.findViewById(R.id.edt01);
        EditText name_edt=view.findViewById(R.id.edt02);

        roll_edt.setHint("Roll");
        name_edt.setHint("Name");
        Button cancel=view.findViewById(R.id.cancel_btn);
        Button add=view.findViewById(R.id.add_btn);

        cancel.setOnClickListener(view1 -> dismiss());
        add.setOnClickListener(view1 -> {
            if(roll_edt.length()==0)
            {
                roll_edt.requestFocus();
                roll_edt.setError("This field is required & Enter Only Integer Value");
            }
            else if (name_edt.length()==0)
            {
                name_edt.requestFocus();
                name_edt.setError("This field is required");
            }
            else {
                String roll = roll_edt.getText().toString();
                String name = name_edt.getText().toString();
                roll_edt.setText(String.valueOf(Integer.parseInt(roll) + 1));
                name_edt.setText("");
                listener.onClick(roll, name);
            }
        });
        return builder.create();
    }
    private Dialog getAddClassDialog()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        View view= LayoutInflater.from(getActivity()).inflate(R.layout.dialog,null);
        builder.setView(view);
        TextView title=view.findViewById(R.id.titleDialog);
        title.setText("Add New Class");
        EditText class_edt=view.findViewById(R.id.edt01);
        EditText subject_edt=view.findViewById(R.id.edt02);
        class_edt.setHint("Class Name");
        subject_edt.setHint("Subject Name");
        Button cancel=view.findViewById(R.id.cancel_btn);
        Button add=view.findViewById(R.id.add_btn);
        cancel.setOnClickListener(view1 -> dismiss());
        add.setOnClickListener(view1 -> {
            if(class_edt.length()==0)
            {
                class_edt.requestFocus();
                class_edt.setError("This field is required");
            }
            else if (subject_edt.length()==0)
            {
                subject_edt.requestFocus();
                subject_edt.setError("This field is required");
            }
            else {
                String className = class_edt.getText().toString();
                String subName = subject_edt.getText().toString();
                listener.onClick(className, subName);
                dismiss();
            }
        });
        return builder.create();
    }
}
