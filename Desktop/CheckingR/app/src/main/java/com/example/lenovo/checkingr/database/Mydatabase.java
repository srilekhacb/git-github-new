package com.example.lenovo.checkingr.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lenovo.checkingr.helper.MyHelper;

public class Mydatabase {

    public static final  String  MY_DB="employeeDB";

    Context myCon;
    SQLiteDatabase sdb;
    MyHelper myHelper;
//to create anything alt+insert key

    public  Mydatabase(Context myContext) {
        myCon= myContext;
       myHelper = new MyHelper(myCon,MY_DB,null,1);


    }

    //method to open database
    public void open(){
        sdb=myHelper.getWritableDatabase();

    }
    //method to insert the values
    public void insertEmp(ContentValues cv){
        //query to insert the values into the table
        sdb.insert("employee", null, cv);
        Log.i("EmployeeDB","Data inserted");

    }
    //to read database from table cursor,pointer
    public Cursor getEmp(){

        sdb=myHelper.getReadableDatabase();
        Cursor cursor=sdb.query("employee",null,null,null,
                null,null,null);

        return cursor;

    }

}
