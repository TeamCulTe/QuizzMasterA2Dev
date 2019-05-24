package com.imie.a2dev.teamculte.quizzmaster.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.imie.a2dev.teamculte.quizzmaster.R;
import com.imie.a2dev.teamculte.quizzmaster.entities.Answer;
import com.imie.a2dev.teamculte.quizzmaster.entities.Question;
import com.imie.a2dev.teamculte.quizzmaster.entities.dbentities.Game;
import com.imie.a2dev.teamculte.quizzmaster.entities.dbentities.Player;
import com.imie.a2dev.teamculte.quizzmaster.views.adapters.AnswerRecyclerViewAdapter;

import java.util.Random;

/**
 * Fragment managing the playing phases.
 */
public class PlayFragment extends Fragment implements AnswerRecyclerViewAdapter.AnswerRowListener, View.OnClickListener {
    /**
     * Stores the text displaying the current player that plays.
     */
    private TextView txtCurrentPlayer;

    /**
     * Stores the text displaying the turn timer.
     */
    private TextView txtTimer;

    /**
     * Stores the text displaying the current question progress.
     */
    private TextView txtCurrentQuestion;

    /**
     * Stores the text displaying the question.
     */
    private TextView txtQuestion;
    
    /**
     * Stores the text displaying the category.
     */
    private TextView txtCategory;
    
    /**
     * Stores the text displaying the clue.
     */
    private TextView txtClue;
    
    /**
     * Stores the answers list.
     */
    private RecyclerView recyclerAnswerList;

    /**
     * Stores the recycler adapter.
     */
    private AnswerRecyclerViewAdapter adapter;
    
    /**
     * Stores the current question index.
     */
    private int currentQuestionIndex = 0;

    /**
     * Stores the current player index.
     */
    private int currentPlayerIndex = 0;

    /**
     * Stores the timer value.
     */
    private int timer = Game.ANSWERING_TIME;
    
    /**
     * Default constructor.
     */
    public PlayFragment() {
    }

    /**
     * Checks if the game is over.
     * @return True if over else false.
     */
    public boolean gameIsOver() {
        boolean over;
                
        if (this.getGame().getMode().getPlayerNumber() == 1) {
            over = (this.currentQuestionIndex + 1 == Game.QUESTION_NB);
        } else {
            over = (this.currentPlayerIndex == this.getGame().getPlayers().size() - 1 &&
                    this.currentQuestionIndex + 1 == Game.QUESTION_NB * this.getGame().getPlayers().size());
        }
        
        return over;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_play, container, false);
        
        this.init(view);
        
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_clue) {
            this.txtClue.setText(this.getCurrentQuestion().getClue().getValue());
        } else if (view.getId() == R.id.btn_skip) {
            this.answerRowSelected(null);
        }  else if (view.getId() == R.id.btn_remove_one) {
            if (this.getCurrentPlayer().getBonuses() > 0) {
                this.getCurrentPlayer().setBonuses(this.getCurrentPlayer().getBonuses() - 1);
                
                int indexToRemove = this.getCurrentQuestion().getCorrectAnswerIndex();
                
                while (indexToRemove == this.getCurrentQuestion().getCorrectAnswerIndex()) {
                    indexToRemove = (int) (Math.random() * (this.getCurrentQuestion().getAnswers().size() - 1));
                }
                
                this.getCurrentQuestion().getAnswers().remove(indexToRemove);
                this.adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this.getContext(), R.string.no_bonus, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void answerRowSelected(Answer answer) {
        if (this.getCurrentQuestion().getCorrectAnswer() == answer) {
            Toast.makeText(this.getContext(), String.format(this.getString(R.string.player_found_answer),
                    this.getCurrentPlayer().getName()), Toast.LENGTH_SHORT).show();
            this.getCurrentPlayer().setPoints(this.getCurrentPlayer().getPoints() + 1);
            this.getCurrentPlayer().setBonuses(this.getCurrentPlayer().getPoints() + 1);
        } else {
            Toast.makeText(this.getContext(), String.format(this.getString(R.string.wrong_answer),
                    String.valueOf(this.getCurrentQuestion().getCorrectAnswerIndex())), Toast.LENGTH_SHORT).show();
        }

        if (!this.gameIsOver()) {
            if (this.currentQuestionIndex == Game.QUESTION_NB - 1) {
                this.currentPlayerIndex++;

                Toast.makeText(this.getContext(), this.getString(R.string.player_turn,
                        this.getCurrentPlayer().getName()), Toast.LENGTH_SHORT).show();
            }

            this.currentQuestionIndex++;
            this.setViews();
            this.initTimeout();
        } else {
            this.getRealActivity().replaceFragment(new ResultsFragment());
        }
        
        this.timer = Game.ANSWERING_TIME;
    }

    /**
     * Initializes the fragment's view components.
     * @param view The fragment's view.
     */
    private void init(View view) {
        this.txtCurrentPlayer = view.findViewById(R.id.txt_current_player);
        this.txtCurrentQuestion = view.findViewById(R.id.txt_current_question);
        this.txtQuestion = view.findViewById(R.id.txt_question);
        this.txtClue = view.findViewById(R.id.txt_clue);
        this.txtCategory = view.findViewById(R.id.txt_category);
        this.txtTimer = view.findViewById(R.id.txt_question_timer);
        this.recyclerAnswerList = view.findViewById(R.id.recycler_answer_list);
        this.adapter = new AnswerRecyclerViewAdapter();
        
        this.adapter.setListener(this);
        
        view.findViewById(R.id.btn_skip).setOnClickListener(this);
        view.findViewById(R.id.btn_clue).setOnClickListener(this);
        view.findViewById(R.id.btn_remove_one).setOnClickListener(this);
        
        this.recyclerAnswerList.setAdapter(this.adapter);
        this.recyclerAnswerList.setLayoutManager(new LinearLayoutManager(this.getContext()));
        this.setViews();
        
        this.initTimeout();
    }

    /**
     * Sets the view components depending on the current indexes.
     */
    private void setViews() {
        this.txtCurrentPlayer.setText(this.getCurrentPlayer().getName());
        this.txtCurrentQuestion.setText(String.format(this.getString(R.string.s_s),
                String.valueOf(this.currentQuestionIndex),
                String.valueOf(Game.QUESTION_NB)));
        this.txtQuestion.setText(this.getCurrentQuestion().getValue());
        this.txtCategory.setText(this.getCurrentQuestion().getCategory().getName());
        this.txtClue.setText("");
        this.adapter.setAnswers(this.getCurrentQuestion().getAnswers());
    }

    /**
     * Initialize the timer.
     */
    private void initTimeout() {
        final String timeoutString = this.getString(R.string.time_over);
        
        new CountDownTimer(Game.ANSWERING_TIME * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                String sec = (PlayFragment.this.timer <= 9) ? "0" + PlayFragment.this.timer : String.valueOf(PlayFragment.this.timer);
                
                PlayFragment.this.txtTimer.setText("0:" + sec);
                PlayFragment.this.timer--;
            }

            public void onFinish() {
                Toast.makeText(PlayFragment.this.getContext(), timeoutString, Toast.LENGTH_LONG).show();
                PlayFragment.this.timer = Game.ANSWERING_TIME;
                PlayFragment.this.txtTimer.setText("0:" + PlayFragment.this.timer);
                PlayFragment.this.answerRowSelected(null);
            }

        }.start();
    }

    /**
     * Gets the real activity managing the fragment.
     * @return The activity.
     */
    private GameActivity getRealActivity() {
        return (GameActivity) this.getActivity();
    }

    /**
     * Gets the current game.
     * @return The game.
     */
    private Game getGame() {
        return this.getRealActivity().getGame();
    }
    
    /**
     * Gets the current question.
     * @return The current question.
     */
    private Question getCurrentQuestion() {
        return this.getGame().getQuestions().get(this.currentQuestionIndex);
    }
    
    /**
     * Gets the current player.
     * @return The current player.
     */
    private Player getCurrentPlayer() {
        return this.getGame().getPlayers().get(this.currentPlayerIndex);
    }
}
