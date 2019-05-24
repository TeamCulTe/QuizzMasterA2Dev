package com.imie.a2dev.teamculte.quizzmaster.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.imie.a2dev.teamculte.quizzmaster.R;
import com.imie.a2dev.teamculte.quizzmaster.entities.Answer;
import com.imie.a2dev.teamculte.quizzmaster.entities.Category;
import com.imie.a2dev.teamculte.quizzmaster.entities.Clue;
import com.imie.a2dev.teamculte.quizzmaster.entities.Question;
import com.imie.a2dev.teamculte.quizzmaster.entities.dbentities.Difficulty;
import com.imie.a2dev.teamculte.quizzmaster.entities.dbentities.Game;
import com.imie.a2dev.teamculte.quizzmaster.entities.dbentities.GameMode;
import com.imie.a2dev.teamculte.quizzmaster.managers.DifficultyDbManager;
import com.imie.a2dev.teamculte.quizzmaster.views.adapters.DifficultySpinnerAdapter;
import com.imie.a2dev.teamculte.quizzmaster.views.adapters.QuestionRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.imie.a2dev.teamculte.quizzmaster.schemas.AbstractGamesPlayersDifficultiesDbSchema.GAME;

/**
 * Fragment managing the player creation.
 */
public class CreateQuestionFragment extends Fragment implements View.OnClickListener {
    /**
     * Stores the title text.
     */
    private TextView txtTitle;

    /**
     * Stores the question edit text.
     */
    private EditText editTxtQuestion;

    /**
     * Stores the first answer edit text.
     */
    private EditText editTxtAnswer1;

    /**
     * Stores the second answer edit text.
     */
    private EditText editTxtAnswer2;

    /**
     * Stores the third answer edit text.
     */
    private EditText editTxtAnswer3;

    /**
     * Stores the fourth answer edit text.
     */
    private EditText editTxtAnswer4;

    /**
     * Stores the clue edit text.
     */
    private EditText editTxtClue;

    /**
     * Stores the category edit text.
     */
    private EditText editTxtCategory;

    /**
     * Stores the difficulty spinner.
     */
    private Spinner spinnerDifficulty;

    /**
     * Stores the correct answer radio group.
     */
    private RadioGroup radioGroupCorrectAnswer;

    /**
     * Store the list of questions.
     */
    private RecyclerView recyclerQuestionsList;

    /**
     * Stores the difficulty spinner adapter.
     */
    private DifficultySpinnerAdapter difficultyAdapter;

    /**
     * Stores the question recycler adapter.
     */
    private QuestionRecyclerViewAdapter questionAdapter;

    /**
     * Defaukt constructor.
     */
    public CreateQuestionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_question, container, false);
        
        this.init(view);
        
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_create_question) {
            if (this.validateData()) {
                this.onValidateClick();

                if (!this.getRealActivity().hasAllQuestions()) {
                    this.clearFields();
                    this.updateTitle();
                    
                    if (this.getRealActivity().getGame().getQuestions().size() == Game.QUESTION_NB) {
                        Toast.makeText(this.getContext(), R.string.next_player_questions, Toast.LENGTH_LONG).show();
                    }
                } else { 
                    Intent intent = new Intent(this.getContext(), GameActivity.class);

                    intent.putExtra(GAME, this.getRealActivity().getGame());

                    this.getRealActivity().startActivity(intent);
                }
            } else {
                Toast.makeText(this.getContext(), R.string.invalid_fields, Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Initializes the view components.
     * @param view The fragment's view.
     */
    private void init(View view) {
        this.txtTitle = view.findViewById(R.id.txt_title);
        this.editTxtQuestion = view.findViewById(R.id.edit_txt_question_value);
        this.editTxtAnswer1 = view.findViewById(R.id.edit_txt_answer_1);
        this.editTxtAnswer2 = view.findViewById(R.id.edit_txt_answer_2);
        this.editTxtAnswer3 = view.findViewById(R.id.edit_txt_answer_3);
        this.editTxtAnswer4 = view.findViewById(R.id.edit_txt_answer_4);
        this.editTxtClue = view.findViewById(R.id.edit_txt_clue);
        this.editTxtClue = view.findViewById(R.id.edit_txt_question_category);
        this.spinnerDifficulty = view.findViewById(R.id.spinner_question_difficulty);
        this.radioGroupCorrectAnswer = view.findViewById(R.id.radio_group_correct_answer);
        this.recyclerQuestionsList = view.findViewById(R.id.recycler_question_list);
        
        this.difficultyAdapter = new DifficultySpinnerAdapter(this.getContext(),
                new DifficultyDbManager(this.getContext()).queryAllSQLite());
        this.questionAdapter = new QuestionRecyclerViewAdapter(this.getRealActivity().getGame().getQuestions());
        
        this.spinnerDifficulty.setAdapter(this.difficultyAdapter);
        this.recyclerQuestionsList.setAdapter(this.questionAdapter);
        
        view.findViewById(R.id.btn_create_question).setOnClickListener(this);

        this.updateTitle();
        Toast.makeText(this.getContext(), R.string.first_player_questions, Toast.LENGTH_LONG).show();
    }

    /**
     * Checks the user inputs.
     * @return True if valid else false.
     */
    private boolean validateData() {
        return (!this.editTxtQuestion.getText().toString().equals("") &&
                !this.editTxtQuestion.getText().toString().equals("") &&
                !this.editTxtAnswer1.getText().toString().equals("") &&
                !this.editTxtAnswer2.getText().toString().equals("") &&
                !this.editTxtAnswer3.getText().toString().equals("") &&
                !this.editTxtAnswer4.getText().toString().equals("") &&
                !this.editTxtClue.getText().toString().equals("") &&
                !this.editTxtCategory.getText().toString().equals(""));
    }

    /**
     * Clears all the fields.
     */
    private void clearFields() {
        this.editTxtQuestion.setText("");
        this.editTxtAnswer1.setText("");
        this.editTxtAnswer2.setText("");
        this.editTxtAnswer3.setText("");
        this.editTxtAnswer4.setText("");
        this.editTxtClue.setText("");
        this.editTxtCategory.setText("");
        ((RadioButton) this.getView().findViewById(
                this.radioGroupCorrectAnswer.getCheckedRadioButtonId())).setChecked(false);
    }

    /**
     * Updates the title.
     */
    private void updateTitle() {
        String title = (String.format(this.getString(R.string.create_question_replacement),
                String.valueOf(this.getRealActivity().getGame().getQuestions().size() + 1),
                String.valueOf(Game.QUESTION_NB)));

        this.txtTitle.setText(title);
    }

    /**
     * Defines the behaviour when clicking on validate.
     */
    private void onValidateClick() {
        String imagePath = "";
        int correctAnswerIndex;
        Difficulty difficulty = this.difficultyAdapter.getItem(this.spinnerDifficulty.getSelectedItemPosition());
        Clue clue = new Clue(this.editTxtClue.getText().toString());
        Category category = new Category(this.editTxtCategory.getText().toString());
        List<Answer> answers = new ArrayList<>();
        
        answers.add(new Answer(this.editTxtAnswer1.getText().toString()));
        answers.add(new Answer(this.editTxtAnswer2.getText().toString()));
        answers.add(new Answer(this.editTxtAnswer3.getText().toString()));
        answers.add(new Answer(this.editTxtAnswer4.getText().toString()));
        
        switch (this.radioGroupCorrectAnswer.getCheckedRadioButtonId()) {
            case R.id.radio_btn_1:
                correctAnswerIndex = 0;
                
                break;
            case R.id.radio_btn_2:
                correctAnswerIndex = 1;

                break;
            case R.id.radio_btn_3:
                correctAnswerIndex = 2;
                
                break;
            case R.id.radio_btn_4:
                correctAnswerIndex = 3;

                break;
            default:
                
                return;
        }
        Question created = new Question(this.editTxtQuestion.getText().toString(),
                correctAnswerIndex,
                difficulty,
                clue,
                category,
                answers,
                imagePath);
        
        this.getRealActivity().getGame().getQuestions().add(created);
        this.questionAdapter.notifyDataSetChanged();
    }

    /**
     * Gets the real activity managing the fragment.
     * @return The activity.
     */
    private InitGameActivity getRealActivity() {
        return (InitGameActivity) this.getActivity();
    }
}
