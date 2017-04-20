package de.android.dimbadn;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener{
    private Spinner spinner;
    private EditText editText1, editText2, editText3;
    private Button button, reset;
    private TextView textView;
    private int position, val1, val2, val3;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);

        spinner = (Spinner)view.findViewById(R.id.spinner);
        editText1 = (EditText)view.findViewById(R.id.editText1);
        editText2 = (EditText)view.findViewById(R.id.editText2);
        editText3 = (EditText)view.findViewById(R.id.editText3);
        button = (Button)view.findViewById(R.id.button);
        reset = (Button)view.findViewById(R.id.reset);
        textView = (TextView)view.findViewById(R.id.textView);

        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.options));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        button.setOnClickListener(this);
        reset.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                if (isDigit(editText1.getText().toString(),
                        (editText2.getText().toString()),
                        (editText3.getText().toString()))) {
                    textView.setText(String.valueOf(position * (val1 + val2 + val3)));
                }else {
                    Toast.makeText(getActivity(), "Введите 3 числовых значения в поля ввода", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.reset:
                spinner.setSelection(0);
                editText1.setText("");
                editText2.setText("");
                editText3.setText("");
                textView.setText("");
                editText1.requestFocus();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.position = position + 1;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // ignore
    }

    private  boolean isDigit(String s1, String s2, String s3) throws NumberFormatException {
        try {
            val1 = Integer.parseInt(s1);
            val2 = Integer.parseInt(s2);
            val3 = Integer.parseInt(s3);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
