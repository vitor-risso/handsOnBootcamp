// Generated by view binder compiler. Do not edit!
package com.example.how2x.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.example.how2x.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DepositFragmentBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button btnDeposit;

  @NonNull
  public final LinearLayout depositFragment;

  @NonNull
  public final EditText depositValue;

  @NonNull
  public final TextView personName;

  @NonNull
  public final Button returnMyAcc;

  private DepositFragmentBinding(@NonNull LinearLayout rootView, @NonNull Button btnDeposit,
      @NonNull LinearLayout depositFragment, @NonNull EditText depositValue,
      @NonNull TextView personName, @NonNull Button returnMyAcc) {
    this.rootView = rootView;
    this.btnDeposit = btnDeposit;
    this.depositFragment = depositFragment;
    this.depositValue = depositValue;
    this.personName = personName;
    this.returnMyAcc = returnMyAcc;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DepositFragmentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DepositFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.deposit_fragment, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DepositFragmentBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnDeposit;
      Button btnDeposit = rootView.findViewById(id);
      if (btnDeposit == null) {
        break missingId;
      }

      LinearLayout depositFragment = (LinearLayout) rootView;

      id = R.id.depositValue;
      EditText depositValue = rootView.findViewById(id);
      if (depositValue == null) {
        break missingId;
      }

      id = R.id.personName;
      TextView personName = rootView.findViewById(id);
      if (personName == null) {
        break missingId;
      }

      id = R.id.returnMyAcc;
      Button returnMyAcc = rootView.findViewById(id);
      if (returnMyAcc == null) {
        break missingId;
      }

      return new DepositFragmentBinding((LinearLayout) rootView, btnDeposit, depositFragment,
          depositValue, personName, returnMyAcc);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
