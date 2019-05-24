package com.imie.a2dev.teamculte.quizzmaster.views.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.imie.a2dev.teamculte.quizzmaster.R;
import com.imie.a2dev.teamculte.quizzmaster.entities.Answer;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom recycler view adapter used to display the list of answer for a question.
 */
public final class AnswerRecyclerViewAdapter extends RecyclerView.Adapter<AnswerRecyclerViewAdapter.AnswerViewHolder> {
    /**
     * Stores the associated listener.
     */
    private AnswerRowListener listener;
    
    /**
     * Stores the list of players to display.
     */
    private List<Answer> answers;
    /**
     * Default constructor
     */
    public AnswerRecyclerViewAdapter() {
        this.answers = new ArrayList<>();
    }
    
    /**
     * Full filled constructor
     * @param answers The players to set.
     */
    public AnswerRecyclerViewAdapter(List<Answer> answers) {
        this.answers = answers;
    }

    /**
     * Gets the listener attribute.
     * @return The AnswerRowListener value of listener attribute.
     */
    public AnswerRowListener getListener() {
        return this.listener;
    }

    /**
     * Sets the listener attribute.
     * @param newListener The new AnswerRowListener value to set.
     */
    public void setListener(AnswerRowListener newListener) {
        this.listener = newListener;
    }

    /**
     * Gets the answers attribute.
     * @return The List<Answer> value of answers attribute.
     */
    public List<Answer> getAnswers() {
        return this.answers;
    }

    /**
     * Sets the answers attribute.
     * @param newAnswers The new List<Answer> value to set.
     */
    public void setAnswers(List<Answer> newAnswers) {
        this.answers = newAnswers;
        this.notifyDataSetChanged();
    }

    /**
     * Adds an answer to the list ans update the view.
     * @param answer The answer to add.
     */
    public void addAnswer(Answer answer) {
        this.answers.add(answer);
        this.notifyDataSetChanged();
    }
    /**
     * Removes an answer to the list ans update the view.
     * @param answer The answer to remove.
     */
    public void removeAnswer(Answer answer) {
        this.answers.remove(answer);
        this.notifyDataSetChanged();
    }
    

    @Override
    public AnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_answer, parent, false);

        return new AnswerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerViewHolder holder, int position) {
        holder.bind(this.answers.get(position));
    }

    @Override
    public int getItemCount() {
        return this.answers.size();
    }

    /**
     * Inner ViewHolder class used to display the data into the rows.
     */
    public final class AnswerViewHolder extends RecyclerView.ViewHolder {
        /**
         * Stores the text displaying the answer number.
         */
        private TextView txtAnswerNumber;
        
        /**
         * Stores the text displaying the answer name.
         */
        private TextView txtAnswer;

        /**
         * Stores the content view.
         */
        private View content;
        
        /**
         * PlayerViewHolder's constructor.
         * @param itemView The generated view.
         */
        public AnswerViewHolder(View itemView) {
            super(itemView);

            this.content = itemView;
            this.txtAnswerNumber = itemView.findViewById(R.id.txt_answer_number);
            this.txtAnswer = itemView.findViewById(R.id.txt_answer_value);
        }

        /**
         * Bind the data to the view.
         * @param answer The answer to display.
         */
        private void bind(Answer answer) {
            this.txtAnswerNumber.setText(String.valueOf(AnswerRecyclerViewAdapter.this.answers.indexOf(answer)));
            this.txtAnswer.setText(answer.getValue());
            
            this.content.setOnClickListener(view -> {
                AnswerRecyclerViewAdapter.this.listener.answerRowSelected(answer);
            });
        }
    }

    /**
     * Listener interface used to notify when a player row is selected.
     */
    public interface AnswerRowListener {
        void answerRowSelected(Answer answer);
    }
}
