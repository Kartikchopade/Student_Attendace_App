package com.example.attendanceapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
public class SheetActivity extends AppCompatActivity {
Toolbar tlbr;
ImageButton br;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet);
        tlbr=findViewById(R.id.sheettool);
        setSupportActionBar(tlbr);
        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);
        //actionBar.setTitle("Attendance Sheet");
        backButton();
       // br=findViewById(R.id.backsheet);
        //br.setOnClickListener(view -> onBackPressed());
        /*br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SheetActivity.this,MainActivity.class);
                startActivity(i);
            }
        });*/
        showTable();
    }
    private void backButton() {
        ImageButton back=findViewById(R.id.backsheet);
        back.setOnClickListener(view -> onBackPressed());
    }
    private void showTable() {
        DBHelper dbHelper=new DBHelper(this);
        TableLayout tableLayout=findViewById(R.id.tableLayout);
        long[] idArray=getIntent().getLongArrayExtra("idArray");
        int[] rollArray=getIntent().getIntArrayExtra("rollArray");
        String[] nameArray=getIntent().getStringArrayExtra("nameArray");
        String month=getIntent().getStringExtra("month");
        int DAYmonth=getDayInMonth(month);
        //row setup getDayInMonth(as);
        int rowSize=idArray.length+1;
        TableRow[] row=new TableRow[idArray.length+1];
        TextView[] rolls_tvs=new TextView[rowSize];
        TextView[] name_tvs=new TextView[rowSize];
        TextView[][] status_tvs=new TextView[rowSize][DAYmonth+1];
        for(int i=0;i<rowSize;i++)
        {
            rolls_tvs[i]=new TextView(this);
            name_tvs[i]=new TextView(this);
            for(int j=1;j<=DAYmonth;j++)
            {
                status_tvs[i][j]=new TextView(this);
            }
        }
        //header
        rolls_tvs[0].setText("Roll");
        rolls_tvs[0].setTypeface(rolls_tvs[0].getTypeface(), Typeface.BOLD);
        name_tvs[0].setText("Name");
        name_tvs[0].setTypeface(name_tvs[0].getTypeface(), Typeface.BOLD);
        for(int i=1;i<=DAYmonth;i++)
        {
            status_tvs[0][i].setText(String.valueOf(i));
           status_tvs[0][i].setTypeface(status_tvs[0][i].getTypeface(), Typeface.BOLD);
        }
        for (int i=1;i<rowSize;i++)
        {
            rolls_tvs[i].setText(String.valueOf(rollArray[i-1]));
            name_tvs[i].setText(nameArray[i-1]);
            for (int j=1;j<=DAYmonth;j++)
            {
                String day=String.valueOf(j);
                if(day.length()==1) day="0"+day;
                String date=day+"."+month;
                String status=dbHelper.getStatus(idArray[i-1],date);
                status_tvs[i][j].setText(status);
            }
        }
        for(int i=0;i<rowSize;i++)
        {
            row[i]=new TableRow(this);
            if(i%2==0)
                row[i].setBackgroundColor(Color.parseColor("#EEEEEE"));
            else row[i].setBackgroundColor(Color.parseColor("#E4E4E4"));
            rolls_tvs[i].setPadding(16,16,16,16);
            name_tvs[i].setPadding(16,16,16,16);
            row[i].addView(rolls_tvs[i]);
            row[i].addView(name_tvs[i]);
            for (int j=1;j<=DAYmonth;j++)
            {
                status_tvs[i][j].setPadding(16,16,16,16);
                row[i].addView(status_tvs[i][j]);
            }
            tableLayout.addView(row[i]);
        }
        tableLayout.setShowDividers(TableLayout.SHOW_DIVIDER_MIDDLE);
    }
    private int getDayInMonth(String month) {
        int monthIndex = Integer.parseInt(month.substring(0, 2)) - 1;
        int year = Integer.parseInt(month.substring(3));
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, monthIndex);
        calendar.set(Calendar.YEAR, year);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }
   /* @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}