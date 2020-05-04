package pl.edu.pjatk.pamo.skrawek.ui.finances;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import pl.edu.pjatk.pamo.skrawek.R;
import pl.edu.pjatk.pamo.skrawek.rest.model.finances.IncomingPayment;

/**
 * {@link RecyclerView.Adapter} that can display a {} and makes a call to the
 * specified {@link }.
 */
public class IncomingPaymentRecyclerViewAdapter extends RecyclerView.Adapter<IncomingPaymentRecyclerViewAdapter.ViewHolder> {

    private final List<IncomingPayment> mValues;
    private final FinancesFragment.OnListFragmentInteractionListener mListener;

    public IncomingPaymentRecyclerViewAdapter(List<IncomingPayment> items,
                                              FinancesFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_incoming_payment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mDate.setText(mValues.get(position).getTransactionDate());
        holder.mValue.setText(String.valueOf(mValues.get(position).getTransactionAmount()));

        holder.mView.findViewById(R.id.incomingPaymentsCard).setOnClickListener(v -> {
            mListener.onSelectPayment(holder.mItem);
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mDate;
        public final TextView mValue;
        public IncomingPayment mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mDate = view.findViewById(R.id.datePayment);
            mValue = view.findViewById(R.id.valuePayment);
        }
    }
}
