package id.sch.smktelkom_mlg.privateassignment.xirpl511.worldofmovies;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import id.sch.smktelkom_mlg.privateassignment.xirpl511.worldofmovies.adapter.NowAdapter;

public class DetailActivity extends AppCompatActivity {

    String getName, getOverview, getPoster, getRelease, getVote, getAverage;
    TextView tvTitle, tvOverview, tvRelease, tvVote, tvAverage;
    ImageView ivPoster;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getName = getIntent().getStringExtra("title");
        getPoster = getIntent().getStringExtra("poster");
        getOverview = getIntent().getStringExtra("overview");
        getRelease = getIntent().getStringExtra("release");
        getVote = getIntent().getStringExtra("vote");
        getAverage = getIntent().getStringExtra("average");

        tvTitle = (TextView) findViewById(R.id.textViewName);
        tvOverview = (TextView) findViewById(R.id.textViewOverview);
        tvRelease = (TextView) findViewById(R.id.textViewDate);
        tvVote = (TextView) findViewById(R.id.textViewCount);
        tvAverage = (TextView) findViewById(R.id.textViewAverage);
        ivPoster = (ImageView) findViewById(R.id.imageViewPosterDetail);

        setTitle("");
        setText();
    }

    private void setText() {

        tvOverview.setText(getOverview);
        tvAverage.setText(getAverage);
        tvTitle.setText(getName);
        tvVote.setText(getVote);
        tvRelease.setText(getRelease);


        Glide.with(NowAdapter.context).load("http://image.tmdb.org/t/p/w500" + getPoster)
                .crossFade()
                .centerCrop()
                .error(R.mipmap.ic_launcher)
                .into(ivPoster);
    }
}
