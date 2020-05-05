package pl.edu.pjatk.pamo.skrawek.ui.children;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.edu.pjatk.pamo.skrawek.R;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Child;
import pl.edu.pjatk.pamo.skrawek.ui.children.ChildrenSelectDialogFragment.OnSelectChildrenFromList;

/**
 * Feed all your data to the list.
 * This object creates views for {@link Child}, and replaces the content of some of the views with new data
 * items when the original item is no longer visible.
 */
public class ChildrenRecyclerViewAdapter extends RecyclerView.Adapter<ChildrenRecyclerViewAdapter.ViewHolder> {

    private final List<Child> mValues;
    private final OnSelectChildrenFromList mListener;

    public ChildrenRecyclerViewAdapter(List<Child> items, OnSelectChildrenFromList listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_child_select, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        String fullName = mValues.get(position).getName() + " " + mValues.get(position).getSurname();
        holder.mItem = mValues.get(position);
        holder.nameAndSurname.setText(fullName);
        holder.peselNumber.setText(mValues.get(position).getPesel());

        holder.mView.setOnClickListener(v -> {
            if (null != mListener) {
                mListener.onSelectedChild(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView nameAndSurname;
        public final TextView peselNumber;
        public Child mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            nameAndSurname = view.findViewById(R.id.child_name);
            peselNumber = view.findViewById(R.id.child_pesel);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + peselNumber.getText() + "'";
        }
    }
}
