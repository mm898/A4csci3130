package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * This the detail view activity that allow us to update/delete business
 */
public class DetailViewActivity extends Activity {

    private EditText nameField, addressField, bussinessNumberField;
    private Spinner primaryBusinessSpinner, provinceSpinner;
    private MyApplicationData appState;

    Contact receivedPersonInfo;

    /**
     * Method for creating the detail view
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");

        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        nameField = (EditText) findViewById(R.id.name);
        addressField = (EditText) findViewById(R.id.address);
        bussinessNumberField = (EditText) findViewById(R.id.business_number);
        primaryBusinessSpinner = (Spinner) findViewById(R.id.primary_bznz_spinner);
        provinceSpinner = (Spinner) findViewById(R.id.province_spinner);

        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            addressField.setText(receivedPersonInfo.address);
            bussinessNumberField.setText(String.valueOf(receivedPersonInfo.business_number));
            primaryBusinessSpinner.setSelection(findIndex(receivedPersonInfo.primary_business, primaryBusinessSpinner));
            provinceSpinner.setSelection(findIndex(receivedPersonInfo.province, provinceSpinner));
        }
    }

    /**
     * Update the business that is selected with the new fields entries
     *
     * @param v
     */
    public void updateContact(View v){
        String name = nameField.getText().toString();
        String province = provinceSpinner.getSelectedItem().toString();
        String primaryBusiness = primaryBusinessSpinner.getSelectedItem().toString();
        String address = addressField.getText().toString();
        int businessNumber = Integer.parseInt(bussinessNumberField.getText().toString());


        Contact person = new Contact(businessNumber, receivedPersonInfo.uid, name, primaryBusiness,
                address, province);

        appState.firebaseReference.child(receivedPersonInfo.uid).setValue(person);
        finish();
    }

    /**
     * Erase the business that is selceted
     *
     * @param v
     */
    public void eraseContact(View v) {
        appState.firebaseReference.child(receivedPersonInfo.uid).removeValue();

        finish();
    }

    /**
     * Find index method that is used for finding the index from the spinners arrays
     *
     * @param recivedString
     * @param spiner
     * @return
     */
    public int findIndex(String recivedString, Spinner spiner){
        for (int i = 0; i < spiner.getCount(); i++){
            if(spiner.getItemAtPosition(i).equals(recivedString))
                return i;
        }

        return 0;
    }
}
