package fragments;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ben.sqlitegamedemo.R;

import entity.GamePlayer;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateFragment extends Fragment implements View.OnClickListener {

    private EditText et_player,et_score,et_level;
    private UpdateFragmentListener updateFragmentListener;
    private GamePlayer gamePlayer;


    public static interface UpdateFragmentListener{
        public void update(GamePlayer gamePlayer);
        public GamePlayer findById(int id);
    }

    public UpdateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            updateFragmentListener = (UpdateFragmentListener) activity;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    public static UpdateFragment newInstance(int id)
    {
        UpdateFragment updateFragment = new UpdateFragment();
        Bundle b = new Bundle();
        b.putInt("id", id);
        updateFragment.setArguments(b);
        return updateFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int id = getArguments().getInt("id");
        gamePlayer = updateFragmentListener.findById(id);
    }

    //View
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update,container,false);
        TextView tv_id = (TextView) view.findViewById(R.id.textView_id);
        et_player = (EditText) view.findViewById(R.id.editText_player);
        et_score = (EditText) view.findViewById(R.id.editText2_score);
        et_level = (EditText) view.findViewById(R.id.editText3_level);
        Button button_save = (Button) view.findViewById(R.id.button_save);
        Button button_cancel = (Button) view.findViewById(R.id.button_cancel);
        button_save.setOnClickListener(this);
        button_cancel.setOnClickListener(this);
        tv_id.setText(String.valueOf(gamePlayer.getId()));
        et_player.setText(gamePlayer.getPlayer());
        et_score.setText(String.valueOf(gamePlayer.getScore()));
        et_level.setText(String.valueOf(gamePlayer.getLevel()));
        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        updateFragmentListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_save:
                save();
                break;
            case R.id.button_cancel:
                getActivity().getFragmentManager().popBackStack();
                break;
        }
    }

    public void save()
    {
        GamePlayer g = new GamePlayer();
        g.setId(gamePlayer.getId());
        g.setPlayer(et_player.getText().toString());
        g.setScore(Integer.parseInt(et_score.getText().toString()));
        g.setLevel(Integer.parseInt(et_level.getText().toString()));
        updateFragmentListener.update(g);
        getActivity().getFragmentManager().popBackStack();
    }

}
