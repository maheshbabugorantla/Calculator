package com.calculator.mahes_000.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView result;

    StringBuilder expression = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Getting the reference to the TextView that shows the final Result of the Expression
        result = (TextView) findViewById(R.id.result);

        // Setting the OnClick Listeners to handle the button Clicks in the Calculator
        (findViewById(R.id.button0)).setOnClickListener(this);      // 0
        (findViewById(R.id.button1)).setOnClickListener(this);      // 1
        (findViewById(R.id.button2)).setOnClickListener(this);      // 2
        (findViewById(R.id.button3)).setOnClickListener(this);      // 3
        (findViewById(R.id.button4)).setOnClickListener(this);      // 4
        (findViewById(R.id.button5)).setOnClickListener(this);      // 5
        (findViewById(R.id.button6)).setOnClickListener(this);      // 6
        (findViewById(R.id.button7)).setOnClickListener(this);      // 7
        (findViewById(R.id.button8)).setOnClickListener(this);      // 8
        (findViewById(R.id.button9)).setOnClickListener(this);      // 9
        (findViewById(R.id.button_plus)).setOnClickListener(this);  // +
        (findViewById(R.id.button_minus)).setOnClickListener(this); // -
        (findViewById(R.id.button_mul)).setOnClickListener(this);   // *
        (findViewById(R.id.button_div)).setOnClickListener(this);   // /
//        (findViewById(R.id.button_mod)).setOnClickListener(this);   // %
        (findViewById(R.id.button_eq)).setOnClickListener(this);    // =
        (findViewById(R.id.clear)).setOnClickListener(this);        // 'C'
        (findViewById(R.id.Backspace)).setOnClickListener(this);    // <=
        (findViewById(R.id.right_br)).setOnClickListener(this);     // ')'
        (findViewById(R.id.left_br)).setOnClickListener(this);      // '('
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        
        return super.onOptionsItemSelected(item);
    }

    // This function handles all the Button clicks in the Calculator Application
    @Override
    public void onClick(View view) {

        switch(view.getId()) {

            case R.id.button0:
                expression.append("0");
                result.setText(expression.toString());
                break;

            case R.id.button1:
                expression.append("1");
                result.setText(expression.toString());
                break;

            case R.id.button2:
                expression.append("2");
                result.setText(expression.toString());
                break;

            case R.id.button3:
                expression.append("3");
                result.setText(expression.toString());
                break;

            case R.id.button4:
                expression.append("4");
                result.setText(expression.toString());
                break;

            case R.id.button5:
                expression.append("5");
                result.setText(expression.toString());
                break;

            case R.id.button6:
                expression.append("6");
                result.setText(expression.toString());
                break;

            case R.id.button7:
                expression.append("7");
                result.setText(expression.toString());
                break;

            case R.id.button8:
                expression.append("8");
                result.setText(expression.toString());
                break;

            case R.id.button9:
                expression.append("9");
                result.setText(expression.toString());
                break;

            case R.id.button_plus:
                expression.append("+");
                result.setText(expression.toString());
                break;

            case R.id.button_minus:
                expression.append("-");
                result.setText(expression.toString());
                break;

            case R.id.button_mul:
                expression.append("*");
                result.setText(expression.toString());
                break;

            case R.id.button_div:
                expression.append("/");
                result.setText(expression.toString());
                break;

/*            case R.id.button_mod:
                expression.append("%");
                result.setText(expression.toString());
                break; */

            case R.id.right_br:
                expression.append(")");
                result.setText(expression.toString());
                break;

            case R.id.left_br:
                expression.append("(");
                result.setText(expression.toString());
                break;

            case R.id.clear:
                expression.setLength(0);
                result.setText(expression.toString());
                break;

            case R.id.Backspace:
                if(expression.length() > 0) {
                    expression = expression.deleteCharAt(expression.length() - 1);
                    result.setText(expression.toString());
                }
                break;

            case R.id.button_eq:
                String postFixExpr = new ExpressionConversion().infix_to_Postfix(expression.toString());

                if(postFixExpr == null) {
                    result.setText("ERROR");
                    expression.setLength(0);
                }
                else {
                    Log.d("PostFix ", postFixExpr);
                    // Evaluating the postFix Expression for the final Value.
                    String resultVal = new ExpressionConversion().evaluatePostfix(postFixExpr);

                    // Showing error if there abnormal no.of operators in the PostFix Expression
                    if(resultVal == null) {
                        result.setText("ERROR");
                    }
                    else {
//                        Toast.makeText(this, "Computing...", Toast.LENGTH_SHORT).show();
                        Log.d("Result ", resultVal);
                        result.setText(resultVal);
                    }
                }
                break;

            default:
                break;
        }
    }
}
