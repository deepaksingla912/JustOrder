package com.example.android.justorder;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 1;
    int price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {

        quantity = quantity + 1;
        displayQuantity(quantity);

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        EditText text = (EditText)findViewById(R.id.name_field);
        String name = text.getText().toString();
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        price = calculatePrice(quantity, hasWhippedCream);
        String priceMessage = createOrderSummary(hasWhippedCream,name);

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Coffee order summary: " + priceMessage);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        }

    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {
        if(quantity==1){
            Toast.makeText(this,"Kuch ta order kar" +"\nk vehliyan marn nu aaya", Toast.LENGTH_SHORT).show();
            return;
        }
            quantity = quantity - 1;
            displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }




    private int calculatePrice(int quantity, boolean addWhippedCream) {
        int basePrice = 10;
        if(addWhippedCream){
            basePrice=basePrice+5;
        }
        price = quantity *  basePrice;
        return price;
    }

    private String createOrderSummary(boolean addWhipedCream ,String yourName) {
        String abc = "Name : " + yourName + "\nquantity : " + quantity + "\ncream lyi aa :- " + addWhipedCream + "\ntotal : $ " + price + "\nthank you ;-)";
        return abc;
    }


}