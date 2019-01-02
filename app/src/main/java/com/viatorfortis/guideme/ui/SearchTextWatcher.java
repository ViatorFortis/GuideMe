package com.viatorfortis.guideme.ui;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.viatorfortis.guideme.R;

public class SearchTextWatcher implements TextWatcher {

    private EditText mEditText;
    private int mBeforeActionCharSequenceLength;

    SearchTextWatcher(EditText editText) {
        mEditText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        mBeforeActionCharSequenceLength = count;
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() == 0) {
            //Toast.makeText(mEditText.getContext(), "s.length() == 0", Toast.LENGTH_SHORT).show();
            mEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_search_black_24dp, 0);
        } else {
            if (mBeforeActionCharSequenceLength == 0) {
                //Toast.makeText(mEditText.getContext(), "s.length() != 0", Toast.LENGTH_SHORT).show();
                mEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_black_24dp, 0);
            }
        }
    }
}
