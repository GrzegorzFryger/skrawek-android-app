package pl.edu.pjatk.pamo.skrawek.ui.absence;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.edu.pjatk.pamo.skrawek.R;
import pl.edu.pjatk.pamo.skrawek.rest.model.calendar.Absence;


/**
 * {@link RecyclerView.Adapter} that can display a {@link Absence}
 *
 */
public class AbsenceRecyclerViewAdapter extends RecyclerView.Adapter<AbsenceRecyclerViewAdapter.ViewHolder> {

    private final List<Absence> mValues;

    /**
     * Instantiates a new Absence recycler view adapter.
     *
     * @param items the items
     */
    public AbsenceRecyclerViewAdapter(List<Absence> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_absence, parent, false);
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

    /**
     * The type View holder.
     */
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
