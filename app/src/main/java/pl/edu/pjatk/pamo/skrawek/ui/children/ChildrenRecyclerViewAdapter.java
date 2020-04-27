package pl.edu.pjatk.pamo.skrawek.ui.children;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pl.edu.pjatk.pamo.skrawek.R;
import pl.edu.pjatk.pamo.skrawek.ui.children.ChildrenSelectDialog.OnSelectChildrenFromList;


import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {} and makes a call to the
 * specified {@link OnSelectChildrenFromList}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ChildrenRecyclerViewAdapter extends RecyclerView.Adapter<ChildrenRecyclerViewAdapter.ViewHolder> {

    private final List<String> mValues;
    private final OnSelectChildrenFromList mListener;

    public ChildrenRecyclerViewAdapter(List<String> items, OnSelectChildrenFromList listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.child_select_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.nameAndSurname.setText(mValues.get(position));
        holder.peselNumber.setText(mValues.get(position));

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
        public String mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            nameAndSurname = (TextView) view.findViewById(R.id.child_name);
            peselNumber = (TextView) view.findViewById(R.id.child_pesel);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + peselNumber.getText() + "'";
        }
    }
}
