package org.ieselcaminas.daniel.buscaminas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.text.Layout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Set;

import static android.R.attr.button;
import static android.os.Build.VERSION_CODES.N;
import static java.security.AccessController.getContext;
import static org.ieselcaminas.daniel.buscaminas.R.id.gridLayout;
import static org.ieselcaminas.daniel.buscaminas.R.id.middle;

public class MainActivity extends AppCompatActivity {
    private MIneButton[][] mIneButtons;
    private BombMatrix bombMatrix;
    private ImageButton imageButton;
    private GridLayout gridLayout;
    private FrameLayout frameLayout;
    private Button buttonConfig;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButton = (ImageButton) findViewById(R.id.imageButton2);
        mIneButtons = new MIneButton[Singleton.sharedInstance().getNumRows()][Singleton.sharedInstance().getNumCols()];
        bombMatrix = new BombMatrix();

        gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        gridLayout.setRowCount(Singleton.sharedInstance().getNumRows());
        gridLayout.setColumnCount(Singleton.sharedInstance().getNumCols());
        buttonConfig = (Button) findViewById(R.id.button);
        startGame();

        buttonConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyPreferencesActivity.class);
                startActivity(intent);
            }
        });

    }

    public boolean hasBomb(int row, int col){
        if(row<0 || row >=Singleton.sharedInstance().getNumRows() || col<0 || col>=Singleton.sharedInstance().getNumCols()) {
            return false;
        } else {
            if(bombMatrix.getValue(row,col)==-1) {
                return true;
            } else {
                return false;
            }
        }
    }

    protected void onResume() {
        super.onResume();
        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if(pref!=null) {
            Toast.makeText(MainActivity.this,"Display Bombs:    "+pref.getBoolean("option1",false)
                                                +"\n Enable Sound:  "+pref.getBoolean("option2",false)
                                        +"\n Size:  "+pref.getString("option3","")
                                        +"\n Difficulty:    "+pref.getString("option4","")
                                        +"\n Email: "+pref.getString("option5",""), Toast.LENGTH_LONG).show();

        }

    }


    public void open(MIneButton[][] buttonsArray, BombMatrix bombMatrix, int row, int col) {
        int numRows = Singleton.sharedInstance().getNumRows();
        int numCols = Singleton.sharedInstance().getNumCols();

        if (row >= 0 && row < numRows && col >= 0 && col < numCols &&
                bombMatrix.getValue(row, col) != -1 && buttonsArray[row][col].getState() == ButtonState.CLOSED) {

            buttonsArray[row][col].setState(ButtonState.OPEN);
            buttonsArray[row][col].setVisibility(View.INVISIBLE);

            if (row >= 0 && row < numRows && col >= 0 && col < numCols && bombMatrix.getValue(row,col) == 0) {

                open(buttonsArray, bombMatrix, row - 1, col);
                open(buttonsArray, bombMatrix, row - 1, col - 1);
                open(buttonsArray, bombMatrix, row - 1, col + 1);
                open(buttonsArray, bombMatrix, row + 1, col);
                open(buttonsArray, bombMatrix, row + 1, col + 1);
                open(buttonsArray, bombMatrix, row + 1, col - 1);
                open(buttonsArray, bombMatrix, row, col - 1);
                open(buttonsArray, bombMatrix, row, col + 1);

            }
        }
    }

    public void gameOver() {
        for(int i = 0; i < mIneButtons.length; i++) {
            for(int j = 0; j < mIneButtons.length; j++) {
                if(hasBomb(i,j)) {
                    bombMatrix.resetMatrix();
                    open(mIneButtons, bombMatrix, i, j);
                    mIneButtons[i][j].setEnabled(false);

                }
            }
        }
        Toast.makeText(MainActivity.this, "Game over!", Toast.LENGTH_LONG).show();
    }


    public void reset() {
        gridLayout.removeAllViews();
        startGame();
    }

    public void startGame() {
        for(int i=0; i<Singleton.sharedInstance().getNumRows(); i++) {
            for(int j=0; j<Singleton.sharedInstance().getNumCols(); j++) {
                mIneButtons[i][j] = new MIneButton(getApplicationContext(),i,j);
                mIneButtons[i][j].setScaleType(ImageView.ScaleType.FIT_XY);

                frameLayout = new FrameLayout(this);
                Cell backCell = new Cell(this);
                frameLayout.addView(backCell);

                if(bombMatrix.getValue(i,j) == -1) {
                    BombView bomb = new BombView(this);
                    frameLayout.addView(bomb);
                } else {
                    NumView number = new NumView(this, bombMatrix.getValue(i,j));
                    frameLayout.addView(number);
                }

                frameLayout.addView(mIneButtons[i][j]);
                gridLayout.addView(frameLayout);


                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        reset();
                    }
                });



                mIneButtons[i][j].setOnClickListener(new View.OnClickListener() {

                    @Override

                    public void onClick(View view) {
                        if (((MIneButton) view).getState() != ButtonState.FLAG && (((MIneButton) view).getState() != ButtonState.QUESTION)){
                            if (((MIneButton) view).getState() == ButtonState.CLOSED) {
                                ((MIneButton) view).setImageDrawable(getResources().getDrawable(R.drawable.back));

                                MIneButton mineButton = (MIneButton) view;

                                if (mineButton.getState() == ButtonState.CLOSED) {

                                    mineButton.setVisibility(View.INVISIBLE);

                                    open(mIneButtons, bombMatrix, mineButton.getRow(), mineButton.getCol());

                                }

                            } else {
                                ((MIneButton) view).setImageDrawable(getResources().getDrawable(R.drawable.boton));
                            }
                        }

                        if (bombMatrix.getValue(((MIneButton) view).getRow(), ((MIneButton) view).getCol())==-1) {
                            ((MIneButton) view).setImageDrawable(getResources().getDrawable(R.drawable.bomb));
                            ((MIneButton) view).setState(ButtonState.BOMB);

                            if(mIneButtons[((MIneButton) view).getRow()][((MIneButton) view).getCol()].isPressed()) {
                                gameOver();
                            }

                        }
                    }



                });



                mIneButtons[i][j].setOnLongClickListener(new View.OnLongClickListener() {

                    @Override

                    public boolean onLongClick(View view) {

                        if (((MIneButton) view).getState() == ButtonState.CLOSED) {
                            ((MIneButton) view).setImageDrawable(getResources().getDrawable(R.drawable.flag));
                            ((MIneButton) view).setState(ButtonState.FLAG);

                        } else {

                            if (((MIneButton) view).getState() == ButtonState.FLAG) {
                                ((MIneButton) view).setImageDrawable(getResources().getDrawable(R.drawable.question));
                                ((MIneButton) view).setState(ButtonState.QUESTION);

                            } else {

                                if (((MIneButton) view).getState() == ButtonState.QUESTION) {
                                    ((MIneButton) view).setImageDrawable(getResources().getDrawable(R.drawable.boton));
                                    ((MIneButton) view).setState(ButtonState.CLOSED);

                                }
                            }
                        }
                        return true;
                    }

                });
            }
        }
    }
}
