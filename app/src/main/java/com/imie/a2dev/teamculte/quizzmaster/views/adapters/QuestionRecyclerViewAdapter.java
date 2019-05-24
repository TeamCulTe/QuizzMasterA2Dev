package com.imie.a2dev.teamculte.quizzmaster.views.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.imie.a2dev.teamculte.quizzmaster.R;
import com.imie.a2dev.teamculte.quizzmaster.entities.Question;

import java.util.List;

/**
 * Custom recycler view adapter used to display the list of questions.
 */
public final class QuestionRecyclerViewAdapter extends RecyclerView.Adapter<QuestionRecyclerViewAdapter.QuestionViewHolder> {

    /**
     * Stores the list of questions to display.
     */
    private List<Question> questions;

    /**
     * Full filled constructor
     * @param questions The questions to set.
     */
    public QuestionRecyclerViewAdapter(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_question, parent, false);

        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        holder.bind(this.questions.get(position));
    }

    @Override
    public int getItemCount() {
        return this.questions.size();
    }

    /**
     * Inner ViewHolder class used to display the data into the rows.
     */
    public final class QuestionViewHolder extends RecyclerView.ViewHolder {
        /**
         * Stores the text displaying the number of the question.
         */
        private TextView txtQuestionNumber;

        /**
         * Stores the text displaying the question value.
         */
        private TextView txtQuestion;

        /**
         * QuestionViewHolder's constructor.
         * @param itemView The generated view.
         */
        public QuestionViewHolder(View itemView) {
            super(itemView);

            this.txtQuestionNumber = itemView.findViewById(R.id.txt_question_number);
            this.txtQuestion = itemView.findViewById(R.id.txt_question_value);
        }

        /**
         * Bind the data to the view.
         * @param question The question to display.
         */
        private void bind(Question question) {
            this.txtQuestionNumber.setText(QuestionRecyclerViewAdapter.this.questions.indexOf(question));
            this.txtQuestion.setText(question.getValue());
        }
    }
}
