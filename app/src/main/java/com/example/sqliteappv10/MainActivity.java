package com.example.sqliteappv10;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName, editSurname, editMarks, editID;
    Button btn_add;
    Button btn_view;
    Button btn_update;
    Button btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       myDb = new DatabaseHelper(this);


        editName=findViewById(R.id.editText_name);
        editSurname=findViewById(R.id.editText_surname);
        editMarks=findViewById(R.id.editText_marks);
        editID=findViewById(R.id.editText_ID);
        btn_add=findViewById(R.id.btn_addData);
        btn_view=findViewById(R.id.btn_view);
        btn_update=findViewById(R.id.btn_update);
        btn_delete=findViewById(R.id.btn_delete);
        AddData();
        viewAll();
        updateData();
        deleteData();
    }

    public void deleteData(){
        btn_delete.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows=myDb.deleteData(editID.getText().toString());
                        if(deletedRows>0)
                            Toast.makeText(MainActivity.this,"Data is deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"data isn't deleted",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    public void updateData(){
        btn_update.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdated=myDb.updataData(editID.getText().toString(),
                                editName.getText().toString(),
                                editSurname.getText().toString(),
                                editMarks.getText().toString());
                        if (isUpdated==true)
                            Toast.makeText(MainActivity.this,"Data is updated",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"data isn't updated",Toast.LENGTH_LONG).show();

                    }
                }
        );

    }

    public void AddData(){
        btn_add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
          boolean isInserted = myDb.insertData(editName.getText().toString(),
                               editSurname.getText().toString(),
                               editMarks.getText().toString());

                        if(isInserted==true)
                            Toast.makeText(MainActivity.this,"Data is inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"data isn't inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll(){

        btn_view.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                   Cursor res= myDb.getAllData();
                       if(res.getCount()==0){
                           //show message
                           showMessage("Error","Nothing found");
                           return;
                        }
                       StringBuffer buffer=new StringBuffer();

                       while (res.moveToNext()){
                           buffer.append("ID :"+res.getString(0)+"\n");
                           buffer.append("NAME :"+res.getString(1)+"\n");
                           buffer.append("SURNAME :"+res.getString(2)+"\n");
                           buffer.append("MARKS :"+res.getString(3)+"\n\n");
                        }
                       //show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title,String message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
       builder.setCancelable(true);
       builder.setTitle(title);
       builder.setMessage(message);
       builder.show();
    }
}
