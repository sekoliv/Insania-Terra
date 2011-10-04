package game.android;

import unit.Unit;

import com.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AndroidActivity extends Activity {
	private Unit character;
	private Unit monster;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.initial_screen);
	}

	public void viewMainMenu(View view) {
		setContentView(R.layout.main_screen);
	}

	public void viewInitialMenu(View view) {
		setContentView(R.layout.initial_screen);
	}

	public void viewProfileMenu(View view) {
		setContentView(R.layout.profile_screen);
		TextView nameText = (TextView) findViewById(R.id.nameText);
		TextView hpText = (TextView) findViewById(R.id.minMaxHPText);
		TextView mpText = (TextView) findViewById(R.id.minMaxMPText);
		ProgressBar hpBar = (ProgressBar) findViewById(R.id.hpBar);
		ProgressBar mpBar = (ProgressBar) findViewById(R.id.mpBar);
		hpBar.setProgressDrawable(getResources().getDrawable(R.drawable.hp_bar));
		mpBar.setProgressDrawable(getResources().getDrawable(R.drawable.mp_bar));
		String actualHP = Integer.toString(character.getActualHitPoints());
		String actualMP = Integer.toString(character.getActualMagicPoints());
		String maxHP = Integer.toString(character.getMaxHitPoints());
		String maxMP = Integer.toString(character.getMaxMagicPoints());
		hpText.setText(actualHP.concat("/").concat(maxHP));
		mpText.setText(actualMP.concat("/").concat(maxMP));
		nameText.setText(character.getName());
		hpBar.setProgress(character.getActualHitPoints());
		mpBar.setProgress(character.getActualMagicPoints());
	}

	public void closeProgram(View view) {
		this.finish();
	}

	public void healCharacter(View view) {
		character.fullHeal();
	}

	public void fightMonster(View view) {
		monster.fullHeal();
		String fightLog = character.fight(monster);
		TextView logText = (TextView) findViewById(R.id.logText);
		logText.setText(fightLog);
	}

	public void login(View view) {
		EditText userText = (EditText) findViewById(R.id.userText);
		EditText passwordText = (EditText) findViewById(R.id.passwordText);
		character = new Unit(userText.getText().toString());
		monster = new Unit("Monster", 2, 3, 2, 0, 0, 1);
		setContentView(R.layout.main_screen);
		Log.d("testando", userText.getText() + "  " + passwordText.getText());
	}
}
