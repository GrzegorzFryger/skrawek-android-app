package pl.edu.pjatk.pamo.skrawek.ui.absence;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.edu.pjatk.pamo.skrawek.R;
import pl.edu.pjatk.pamo.skrawek.dummy.DummyContent.DummyItem;
import pl.edu.pjatk.pamo.skrawek.rest.model.calendar.Absence;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link}.
 * TODO: Replace the implementation with code for your data type.
 */
public class AbsenceRecyclerViewAdapter extends RecyclerView.Adapter<AbsenceRecyclerViewAdapter.ViewHolder> {

    private final List<Absence> mValues;

    public AbsenceRecyclerViewAdapter(List<Absence> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_absence_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.date.setText(mValues.get(position).getDate());
        holder.reason.setText(mValues.get(position).getReason());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView date;
        public final TextView reason;
        public Absence mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            date = view.findViewById(R.id.date);
            reason = view.findViewById(R.id.reason);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + reason.getText() + "'";
        }
    }
}
