package com.example.bjanash_c196.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bjanash_c196.R;
import com.example.bjanash_c196.database.TermEntity;
import com.example.bjanash_c196.term_editor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;

import static com.example.bjanash_c196.utilities.Constants.END_TERM_TITLE_ID;
import static com.example.bjanash_c196.utilities.Constants.START_TERM_TITLE_ID;
import static com.example.bjanash_c196.utilities.Constants.TERM_TITLE_ID;

public class TermsAdapter extends RecyclerView.Adapter<TermsAdapter.ViewHolder> {
    private final List<TermEntity> mTerms;
    private final Context mContext;

    public TermsAdapter(List<TermEntity> mTerms, Context mContext) {
        this.mTerms = mTerms;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.single_term, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final TermEntity term = mTerms.get(position);
        holder.mTextViewTermTitle.setText(term.getTermTitle());

        holder.mFabEditTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, term_editor.class);
                intent.putExtra(TERM_TITLE_ID, term.getTermTitle());
                intent.putExtra(START_TERM_TITLE_ID, term.getTermStartDate());
                intent.putExtra(END_TERM_TITLE_ID, term.getTermEndDate());
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mTerms.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.TermTitle)
        TextView mTextViewTermTitle;
        @BindView (R.id.fab_editTerm)
        FloatingActionButton mFabEditTerm;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
