package com.example.simplecalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    public String str = "";
    Character op = 'q';
    float num, numtemp;
    TextView showresult;

    Button one, two, three, four, five, six, seven, eight, nine, zero;
    Button div, mul, sub, add, equal, clear;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        zero = findViewById(R.id.zero);
        div = findViewById(R.id.div);
        mul = findViewById(R.id.mul);
        sub = findViewById(R.id.sub);
        add = findViewById(R.id.add);
        equal = findViewById(R.id.equal);
        clear = findViewById(R.id.clear);
        showresult = findViewById(R.id.result_id);

        // Number buttons
        one.setOnClickListener(v -> insert(1));
        two.setOnClickListener(v -> insert(2));
        three.setOnClickListener(v -> insert(3));
        four.setOnClickListener(v -> insert(4));
        five.setOnClickListener(v -> insert(5));
        six.setOnClickListener(v -> insert(6));
        seven.setOnClickListener(v -> insert(7));
        eight.setOnClickListener(v -> insert(8));
        nine.setOnClickListener(v -> insert(9));
        zero.setOnClickListener(v -> insert(0));

        // Operation buttons
        add.setOnClickListener(v -> { perform(); op = '+'; });
        sub.setOnClickListener(v -> { perform(); op = '-'; });
        mul.setOnClickListener(v -> { perform(); op = '*'; });
        div.setOnClickListener(v -> { perform(); op = '/'; });

        // Equals button
        equal.setOnClickListener(v -> calculate());

        // Clear button
        clear.setOnClickListener(v -> reset());

        // Handle system insets (optional)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void reset() {
        str = "";
        op = 'q';
        num = 0;
        numtemp = 0;
        showresult.setText("0");
    }

    public void insert(int i) {
        str += i;
        try {
            num = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            str = "";
            num = 0;
        }
        showresult.setText(str);
    }

    public void perform() {
        numtemp = num;
        str = "";
    }

    public void calculate() {
        switch (op) {
            case '+':
                num = numtemp + num;
                break;
            case '-':
                num = numtemp - num;
                break;
            case '*':
                num = numtemp * num;
                break;
            case '/':
                if (num != 0) {
                    num = numtemp / num;
                } else {
                    showresult.setText("Error");
                    return;
                }
                break;
            default:
                return;
        }
        str = String.valueOf(num);
        showresult.setText(str);
    }
}
