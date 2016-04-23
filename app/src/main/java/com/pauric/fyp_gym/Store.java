package com.pauric.fyp_gym;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.paypal.android.MEP.PayPal;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalItem;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalPaymentDetails;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import java.math.BigDecimal;

import static com.pauric.fyp_gym.Util.uName;


public class Store extends Activity implements View.OnClickListener {


    private static final int REQUEST_CODE_PAYMENT = 1;

    // PayPal configuration
   /* private static PayPalConfiguration paypalConfig = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX).clientId(
                    Util.paypal_sdk_id);*/
    private static PayPalConfiguration paypalConfig=new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId( Util.paypal_sdk_id)
            .acceptCreditCards(true)
                    // The following are only used in PayPalFuturePaymentActivity.
            .merchantName("Code_Crash")
            .merchantPrivacyPolicyUri(
                    Uri.parse("https://www.paypal.com/webapps/mpp/ua/privacy-full"))
            .merchantUserAgreementUri(
                    Uri.parse("https://www.paypal.com/webapps/mpp/ua/useragreement-full"));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        // Starting PayPal service
        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, paypalConfig);
        startService(intent);
        ImageButton membershipBtn1= (ImageButton) findViewById(R.id.membershipBtn1);
        ImageButton membershipBtn3= (ImageButton) findViewById(R.id.membershipBtn3);
        ImageButton membershipBtn6= (ImageButton) findViewById(R.id.membershipBtn6);
        ImageButton membershipBtn12= (ImageButton) findViewById(R.id.membershipBtn12);
        membershipBtn1.setOnClickListener(this);
        membershipBtn3.setOnClickListener(this);
        membershipBtn6.setOnClickListener(this);
        membershipBtn12.setOnClickListener(this);
    }




            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.membershipBtn1:
                        launchPayPalPayment(49.00, uName);
                        break;
                    case R.id.membershipBtn3:
                        launchPayPalPayment(140.00,"3 Months Membership");
                        break;
                    case R.id.membershipBtn6:
                        launchPayPalPayment(245.00,"6 Months Membership");
                        break;
                    case R.id.membershipBtn12:
                        launchPayPalPayment(395.00,"12 Months Membership");
                        break;


                }

                //call pay pal sdk method

            }








    private void launchPayPalPayment(double cost, String type) {

        PayPalPayment thingToBuy = new PayPalPayment(new BigDecimal(cost),"EUR", type,PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(Store.this, PaymentActivity.class);

        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, paypalConfig);

        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, thingToBuy);

        startActivityForResult(intent, REQUEST_CODE_PAYMENT);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {

                Toast.makeText(getApplicationContext(), "Payment done succesfully ", Toast.LENGTH_LONG).show();

            }

            else if (resultCode == Activity.RESULT_CANCELED) {

                Toast.makeText(getApplicationContext(), "Payment Canceled , Try again ", Toast.LENGTH_LONG).show();


            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {

                Toast.makeText(getApplicationContext(), "Payment failed , Try again ", Toast.LENGTH_LONG).show();

            }
        }
    }


}
