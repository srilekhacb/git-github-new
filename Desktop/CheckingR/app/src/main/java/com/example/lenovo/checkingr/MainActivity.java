package com.example.lenovo.checkingr;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.lenovo.checkingr.database.Mydatabase;

public class MainActivity extends AppCompatActivity {

    Mydatabase mdb=new Mydatabase(this);
    Cursor cursor;
    SimpleCursorAdapter sca;

    EditText etName,etAddress,etSalary;
    Button btnInsert;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName=findViewById(R.id.editText3);
        etAddress=findViewById(R.id.editText2);
        etSalary=findViewById(R.id.editText4);
        lv=findViewById(R.id.listViewEmployee);
        mdb.open();

        btnInsert=findViewById(R.id.button2);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //String Variables to store the edittext values
                String stName=etName.getText().toString();
                String stAddress=etAddress.getText().toString();
                Integer salary=Integer.parseInt(etSalary.getText().toString());

                //now we have got the values from the user,lets send it into the db

                ContentValues cv=new ContentValues();
                cv.put("emp_name",stName);
                cv.put("emp_location", stAddress);
                cv.put("emp_salary", salary);
//insert the data
                mdb.insertEmp(cv);
                    //clear the fields for new entries
                etName.setText(null);
                etAddress.setText(null);
                etSalary.setText(null);

                cursor.requery();

            }
        });
        cursor=mdb.getEmp();
        String [] dbRef={"emp_name","emp_location","emp_salary"};
        int [] tvRef={R.id.textViewname,R.id.textViewAddress,R.id.textViewSalary};
        sca=new SimpleCursorAdapter(this,R.layout.row,cursor,dbRef,tvRef);
        lv.setAdapter(sca);
        sca.notifyDataSetChanged();
        cursor.requery();



    }
}
