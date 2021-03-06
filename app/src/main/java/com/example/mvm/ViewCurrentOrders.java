package com.example.mvm;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ViewCurrentOrders extends AppCompatActivity {

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.parent_menu, menu);
        MenuItem cartItem = menu.getItem(4);
        cartItem.setIcon(0);
        cartItem.setTitle("");
        cartItem.setEnabled(false);
        return true;
    }
    AlertDialog.Builder alertBuilder;
    public void onLogoutClick(final Context context) {
        alertBuilder = new AlertDialog.Builder(ViewCurrentOrders.this);
        alertBuilder.setTitle("Confirm Logout");
        alertBuilder.setMessage("Are you sure you want to logout ?");
        alertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(context,MainActivity.class));
                dialogInterface.dismiss();
            }
        });
        alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                onLogoutClick(getApplicationContext());
                return true;
            case R.id.home:
                startActivity(new Intent(this,OperatorHomeScreen.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_orders);
        TableLayout ll = findViewById(R.id.table_layout);

        List<List<String>> list = new ArrayList<>();
        List<String> child = new ArrayList<>();
            child.add("1234");
            child.add("Bruce Wayne");
            child.add("Pending");
            child.add("2");
            child.add("$12.00");
            list.add(child);
        child = new ArrayList<>();
        child.add("1235");
        child.add("Bruce Wayne");
        child.add("Pending");
        child.add("4");
        child.add("$12.00");
        list.add(child);
        child = new ArrayList<>();
        child.add("1236");
        child.add("Bruce Wayne");
        child.add("Pending");
        child.add("2");
        child.add("$12.00");
        list.add(child);
        child = new ArrayList<>();
        child.add("1237");
        child.add("Bruce Wayne");
        child.add("Pending");
        child.add("3");
        child.add("$12.00");
        list.add(child);
        for (int i = 2; i <= list.size()+1; i++) {
            TableRow row = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            List<String> orders = list.get(i-2);
            int in = 0;
            final String orderId = orders.get(0);
            for (String order : orders) {
                TextView textView = new TextView(this);
                textView.setText(order);
                if(in == 1)
                    textView.setWidth(300);
                else
                    textView.setWidth(265);
                if (in == 4) {
                    textView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
                } else
                    textView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                row.addView(textView);
                in++;
            }
            ll.addView(row,i);
            row.setClickable(true);  //allows you to select a specific row

            row.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent=new Intent(v.getContext(),OrderDetailsForOperator.class);
                    startActivityForResult(intent,0);
                }
            });
        }
    }
}
