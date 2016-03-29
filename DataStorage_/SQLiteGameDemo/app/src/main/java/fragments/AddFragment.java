package fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.ben.sqlitegamedemo.R;

import entity.GamePlayer;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends DialogFragment {

    private AddFragmentListener addFragmentListener;
    public static interface AddFragmentListener{
        public void add(GamePlayer gamePlayer);
    }

    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            addFragmentListener = (AddFragmentListener) activity;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    public static AddFragment newInstance()
    {
        AddFragment addFragment = new AddFragment();
        return addFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final View view = LayoutInflater.from(getActivity()).inflate(R.layout.create_gameplayer_dialog,null);

        return new AlertDialog.Builder(getActivity())
                .setIcon(android.R.drawable.ic_input_add)
                .setView(view)
                .setTitle(R.string.title_new_game_player)
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText et_player = (EditText) view.findViewById(R.id.editText_player);
                        EditText et_score = (EditText) view.findViewById(R.id.editText2_score);
                        EditText et_level = (EditText) view.findViewById(R.id.editText3_level);
                        GamePlayer gamePlayer = new GamePlayer();
                        gamePlayer.setPlayer(et_player.getText().toString());
                        gamePlayer.setScore(Integer.parseInt(et_score.getText().toString()));
                        gamePlayer.setLevel(Integer.parseInt(et_level.getText().toString()));
                        addFragmentListener.add(gamePlayer);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                .create();
    }

}
