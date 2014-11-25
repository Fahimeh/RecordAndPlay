package org.example.recordandplay;

import java.io.File;
import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	String outputFile;
	MediaRecorder mediaRecorder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button play = (Button) findViewById(R.id.play);
		Button start = (Button) findViewById(R.id.start);
		Button stop = (Button) findViewById(R.id.stop);

		play.setOnClickListener(this);
		stop.setOnClickListener(this);
		start.setOnClickListener(this);

		outputFile = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/recorderSample.3gp";

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.start:
			startRecordingVoice();
			try {
				mediaRecorder.prepare();
				mediaRecorder.start();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		case R.id.stop:
			mediaRecorder.stop();
			mediaRecorder.release();
			break;
		case R.id.play:
			MediaPlayer mediaPlayer = new MediaPlayer();
			try {
				mediaPlayer.setDataSource(outputFile);
				mediaPlayer.prepare();
				mediaPlayer.start();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}

	}

	private void startRecordingVoice() {
		mediaRecorder = new MediaRecorder();
		mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
		File f = new File(outputFile);
		if (f.exists()) {
			// create new file and save on outputFile String;
		}
		mediaRecorder.setOutputFile(outputFile);
	}

}
