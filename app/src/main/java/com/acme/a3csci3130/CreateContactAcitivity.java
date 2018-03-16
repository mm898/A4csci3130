package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * This is create contact activity which allow us to
 * create new contacts.
 */
public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText nameField, addressField, bussinessNumberField;
    private Spinner primaryBusinessSpinner, provinceSpinner;
    private MyApplicationData appState;

    /**
     * Method for creating the contact activity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.name);
        addressField = (EditText) findViewById(R.id.address);
        bussinessNumberField = (EditText) findViewById(R.id.business_number);
        primaryBusinessSpinner = (Spinner) findViewById(R.id.primary_bznz_spinner);
        provinceSpinner = (Spinner) findViewById(R.id.province_spinner);

    }

    /**
     * Get the text from the form then create person object
     * then use that created object to store it into Firebase
     *
     * @param v
     */
    public void submitInfoButton(View v) {

        //each entry needs a unique ID
        String personID = appState.firebaseReference.push().getKey();
        String name = nameField.getText().toString();
        String province = provinceSpinner.getSelectedItem().toString();
        String primaryBusiness = primaryBusinessSpinner.getSelectedItem().toString();
        String address = addressField.getText().toString();
        int businessNumber = Integer.parseInt(bussinessNumberField.getText().toString());


        Contact person = new Contact(businessNumber, personID, name, primaryBusiness,
                address, province);

        appState.firebaseReference.child(personID).setValue(person);

        finish();

    }
}
