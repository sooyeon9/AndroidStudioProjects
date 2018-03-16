//IntentDemo1: making a phone call
package cse4034.lab.lab8;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Intent_Ex5 extends Activity {

    Button btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new ClickHandler());
    }// onCreate

    private class ClickHandler implements OnClickListener {
        @Override
        public void onClick(View v) {
            try {
                /*
				 * To-Do #1
				 */

                // 1
				/*
				Intent myIntent = new Intent();
				
				myIntent.setAction(Intent.ACTION_DIAL);
				myIntent.setData(Uri.parse("tel:031-400-5190"));
				
				startActivity(myIntent);
				*/

                // 2
				/*
				Intent myIntent = new Intent();
				
				myIntent.setAction(Intent.ACTION_VIEW);
				myIntent.setData(Uri.parse("http://www.youTube.com"));
				
				startActivity(myIntent);
				*/

                // 3
				/*
				Intent myIntent = new Intent();
				
				myIntent.setAction(Intent.ACTION_VIEW);
				String myData = ContactsContract.Contacts.CONTENT_URI.toString();
				myIntent.setData(Uri.parse(myData));
				
				startActivity(myIntent);
				*/

                // 4
				/*
				Intent myIntent = new Intent();
				
				myIntent.setAction(Intent.ACTION_VIEW);
				String myData = ContactsContract.Contacts.CONTENT_URI.toString() + "/1";
				myIntent.setData(Uri.parse(myData));
				
				startActivity(myIntent);
				*/

                // 5
				/*
				Intent myIntent = new Intent();
				
				myIntent.setAction(Intent.ACTION_GET_CONTENT);
				myIntent.setType("image/*");
				
				startActivity(myIntent);
				*/
				
				/*
				// 6
				Intent myIntent = new Intent();
				
				myIntent.setAction(Intent.ACTION_GET_CONTENT);
				myIntent.setType(ContactsContract.Contacts.CONTENT_ITEM_TYPE);
				
				startActivity(myIntent);
				*/

                // 7
                //Intent myIntent = new Intent();

                //myIntent.setAction(Intent.ACTION_GET_CONTENT);
                //myIntent.setType("*/*");
                //myIntent.addCategory(Intent.CATEGORY_OPENABLE);

                //startActivity(myIntent);

                // 8
				/*
				Intent myIntent = new Intent(Intent.ACTION_VIEW);
				
				Uri data = Uri.parse("file:///sdcard/Music/Guckkasten-Memory of One Shot");
				String type = "audio/mp3";
				
				myIntent.setDataAndType(data, type);
				
				startActivity(myIntent);
				*/

                // 9
				/*
				Intent myIntent = new Intent();
				
				myIntent.setAction(Intent.ACTION_WEB_SEARCH);
				
				myIntent.putExtra(SearchManager.QUERY, "Tiger Woods' Swing");
				
				startActivity(myIntent);
				*/

                // 10
				/*
				String myData = "mailto:kimjs@hanyang.ac.kr";
				
				Intent myIntent = new Intent(Intent.ACTION_SENDTO,
				                             Uri.parse(myData));
				myIntent.putExtra(Intent.EXTRA_SUBJECT, "Welcome to COM308");
				myIntent.putExtra(Intent.EXTRA_TEXT, "Your Subject Goes Here!");
				
				startActivity(myIntent);			
				*/

                // 11
				/*
				String where = "한양대학교 ERICA 캠퍼스";
				
				Intent myIntent = new Intent(Intent.ACTION_VIEW,
						Uri.parse("geo:0,0?q=(" + where + ")" ) );
				
				startActivity(myIntent);
				*/

            } catch (Exception e) {
                Toast.makeText(getBaseContext(), e.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        }// onClick
    }// ClickerHandler
}// IntentDemo1