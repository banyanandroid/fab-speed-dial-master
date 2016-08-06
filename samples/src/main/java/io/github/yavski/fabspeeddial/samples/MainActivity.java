/*
 * Copyright 2016 Yavor Ivanov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.yavski.fabspeeddial.samples;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import io.github.yavski.fabmenu.samples.R;
import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;

public class MainActivity extends ActionBarActivity {

     String s2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_sample);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("USSD Code");
        toolbar.setTitleTextColor(0xFFFFFFFF);



        FabSpeedDial fabSpeedDial = (FabSpeedDial) findViewById(R.id.fab_speed_dial);
        fabSpeedDial.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                String str_menu= String.valueOf(menuItem.getTitle());

                if(str_menu.equals("Recharger")){

                    showChangeLangDialog();
                }else if(str_menu.equals("Share")){

                    shareTextUrl();
                }else if(str_menu.equals("About us")){

                    showChangeLangDialog_about();

                }
                return false;
            }
        });

        //THE EXPANDABLE
        ExpandableListView elv=(ExpandableListView) findViewById(R.id.expandableListView1);

        final ArrayList<Team> team=getData();

        //CREATE AND BIND TO ADAPTER
        CustomAdapter adapter=new CustomAdapter(this, team);
        elv.setAdapter(adapter);

        //SET ONCLICK LISTENER
        elv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPos,
                                        int childPos, long id) {

                String s= team.get(groupPos).players.get(childPos);

                String CurrentString = s;
                String[] separated = CurrentString.split(":");
                final String s1 = separated[0];
                  s2 = separated[1];

                showChangeLangDialog_cal();

                return false;
            }
        });



    }

    //ADD AND GET DATA

    private ArrayList<Team> getData()
    {

        Team t1=new Team("Airtel");
        t1.players.add("Balance Check Code:*123#");
        t1.players.add("Own Mobile Number Check:*121*1#");
        t1.players.add("Mobile Number Validity:*121*2#");
        t1.players.add("2G Internet Pack Details:*121*8#");
        t1.players.add("3G/4G Internet Pack Details:*121*8#");
        t1.players.add("2G/3G Internet Data Loan Code:*121*8#");
        t1.players.add("Airtel SMS Packs Detail:*121*8#");
        t1.players.add("Local SMS balance Check Code:*123*2#");
        t1.players.add("STD SMS Balance Check Code:*123*3#");
        t1.players.add("Airtel Internet balance check:*123*10#");
        t1.players.add("Airtel Loan USSD Code: *141*10#");

        Team t2=new Team("Vodafone");
        t2.players.add("My Balance:*111*2#");
        t2.players.add("Vodafone Internet MB Check:*111*6*2#");
        t2.players.add("Activate 2G Mobile Internet MB Pack Check Code:*111*6*3#");
        t2.players.add("Activate 3G Mobile Internet MB Pack Check Code:*111*6*4#");
        t2.players.add("Low Balance Services Check Code:*111*10# ");
        t2.players.add("Free Night Minutes Balance:*111*4*2#");
        t2.players.add("Free SMS Balance Check code:*111*4*2#");
        t2.players.add("Local and STD call rate check code:*111*4*2#");
        t2.players.add("USSD code for Vodafone 2G/3G recharge(dial and select your pack):*121#");
        t2.players.add(" Call Balance Check code:*141#");

        Team t3=new Team("Idea");
        t3.players.add("Idea Balance Check:*121#");
        t3.players.add("My plan Information Check Code:*147*1*3#");
        t3.players.add("Last charges Check Code:*147*1*4#");
        t3.players.add("Idea 2G Net Balance: *125# ");
        t3.players.add("Idea SMS Balance:*451#");
        t3.players.add("Idea Activate Call Waiting:*43#");
        t3.players.add("Idea  Idea price Information:*567#");
        t3.players.add("Activate idea night, SMS packs, local packs:*369#");
        t3.players.add("Idea USSD code to view loan benefits:*167*4#");
        t3.players.add("Customer Care number or Complaint number:198");

        Team t4=new Team("Reliance");
        t4.players.add(" Balance Check Code:*111*1*1*1#");
        t4.players.add("Mobile Internet Data Check Code:*111*1*1*3#");
        t4.players.add("Mobile Internet Loan Code(dial and Reply 1):*111*1*1*3#");
        t4.players.add("My Activated Pack Check Code:*111*1*4*3#");
        t4.players.add("Best Offers Check Code:*111*2*1*1#");
        t4.players.add("Call Conference Service Check Code:*111*2*2*1#");
        t4.players.add(" USSD to check Internet Balance Code:*111*1*3# ");
        t4.players.add("Check Special packs:*777#");
        t4.players.add("USSD for Missed Call Alert :*333*3*2*1#");
        t4.players.add("USSD Code for Free Recharge :*123*099#");
        t4.players.add("Customer care number:198");
        t4.players.add("Know own mobile number:*1#");

        Team t5=new Team("Aircel");
        t5.players.add("Main Balance:*111#");
        t5.players.add("Internet Balance:*126*1#");
        t5.players.add("Check Special Deals and Discounts:*789#");
        t5.players.add("3G Internet Data balance:*123*11#");
        t5.players.add("Check Minutes Balance:*123#");
        t5.players.add("3G Internet:*122*5*1#");
        t5.players.add("Check Rate cutter:*122#");
        t5.players.add("Customer Care Number:121");
        t5.players.add("Codes Check own Mobile Number:*131#");
        t5.players.add("SSD Code to Get the Loan:*414# ");

        Team t6=new Team("BSNL");
        t6.players.add("Check Balance:*123*1#");
        t6.players.add("Validity Data Balance Enquiry:*234#");
        t6.players.add("Validity  2G Net Balance:*123*10#");
        t6.players.add("Internet main balance:*124#");
        t6.players.add("3G data check:*112#");
        t6.players.add("SMS Balance Enquiry:*125#");
        t6.players.add("Validity SMS Balance:*123*1#");
        t6.players.add("Network Call:*123*5#");
        t6.players.add("Last Call Charges Details:*102#");
        t6.players.add("Validity  Local Network Call:*123*6#");

        Team t7=new Team("Tata DoCoMo");
        t7.players.add("Balance Check Code:*111#");
        t7.players.add("Check 2G/3G Internet:*111*1# ");
        t7.players.add("Internet Packs:*141#");
        t7.players.add("Start/Stop Sevices Check Code:*191*7#  ");
        t7.players.add("Daily SMS Count Code:*191*9*3#");
        t7.players.add("Special Offers Code:*191*9*8# ");
        t7.players.add(" 3G Internet Packs Check Code:*141*1#");
        t7.players.add("USSD code for local flavor:*141*2# ");
        t7.players.add("Customer care number USSD  code:121 ");
        t7.players.add("Loan USSD code:*444#");
        t7.players.add(" recharge your mobile number:*135*2*#");

        ArrayList<Team> allTeams=new ArrayList<Team>();
        allTeams.add(t1);
        allTeams.add(t2);
        allTeams.add(t3);
        allTeams.add(t4);
        allTeams.add(t5);
        allTeams.add(t6);
        allTeams.add(t7);

        return allTeams;
    }
    //.........................................................................................................

    // alert for calling
    public void showChangeLangDialog_cal() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_alert_forcal, null);
        dialogBuilder.setView(dialogView);

        final EditText edt = (EditText) dialogView.findViewById(R.id.edt_ph);
        edt.setText(s2);
        dialogBuilder.setTitle("Do you want to call..?");

        dialogBuilder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }


        });
        dialogBuilder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
                String number_mob = edt.getText().toString().trim();

                if(number_mob.equals("")){
                    Toast.makeText(MainActivity.this, "Please Enter your number..!", Toast.LENGTH_LONG).show();
                }else{
                    System.out.print("eswari_sathya" + number_mob);
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + number_mob));
                    startActivity(callIntent);

                }
            }
        });



        AlertDialog b = dialogBuilder.create();
        b.show();
    }

//.........................................................................................................

    // alert for recharger
    public void showChangeLangDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_alert_email, null);
        dialogBuilder.setView(dialogView);

        final EditText edt = (EditText) dialogView.findViewById(R.id.emailaddress);
        dialogBuilder.setTitle("Click to recharger your mobile !");
        dialogBuilder.setMessage("Please Enter your voucher code");
        dialogBuilder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }


        });
        dialogBuilder.setNegativeButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
                String number = edt.getText().toString().trim();

                if(number.equals("")){
                    Toast.makeText(MainActivity.this, "Please Enter your voucher code..!", Toast.LENGTH_LONG).show();
                }else{
                    System.out.print("eswari_sathya" + number);
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + number));
                    startActivity(callIntent);

                }
            }
        });



        AlertDialog b = dialogBuilder.create();
        b.show();
    }
//.........................................................................................................

    // alert for about us
    public void showChangeLangDialog_about() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_alert_about, null);
        dialogBuilder.setView(dialogView);

        final TextView txt1 = (TextView) dialogView.findViewById(R.id.txt_view_details);
        dialogBuilder.setTitle("About Us !");
        dialogBuilder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }
//.........................................................................................................

    // Method to share either text or URL.
    private void shareTextUrl() {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, "PharmaJobs-India");
        share.putExtra(Intent.EXTRA_TEXT, "Hi friends..!");

        startActivity(Intent.createChooser(share, "Share link!"));
    }
    //.........................................................................................................

}





