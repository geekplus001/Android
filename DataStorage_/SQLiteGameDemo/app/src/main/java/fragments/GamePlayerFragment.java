package fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ben.sqlitegamedemo.R;

import java.util.ArrayList;

import entity.GamePlayer;

/**
 * A simple {@link Fragment} subclass.
 */
public class GamePlayerFragment extends Fragment {

    private GamePlayerAdapter gamePlayerAdapter;
    private GamePlayerFragmentListener gamePlayerFragmentListener;

    public static interface GamePlayerFragmentListener{
        public void showGamePlayerFragment();
        public void showUpdateFragment(int id);
        public void delete(int id);
        public ArrayList<GamePlayer> findAll();
    }

    public GamePlayerFragment() {
        // Required empty public constructor
    }

    //连接Activity和Fragment
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            gamePlayerFragmentListener = (GamePlayerFragmentListener) activity;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    public static GamePlayerFragment newInstance()
    {
        GamePlayerFragment gamePlayerFragment = new GamePlayerFragment();
        return gamePlayerFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<GamePlayer> gamePlayers = gamePlayerFragmentListener.findAll();
        gamePlayerAdapter = new GamePlayerAdapter(getActivity(), gamePlayers);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_player,container,false);
        ListView listView = (ListView) view.findViewById(R.id.listView);
        registerForContextMenu(listView);//长按弹出
        listView.setAdapter(gamePlayerAdapter);
        return view;
    }

    //创建菜单项(弹出)
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle(R.string.menu_edit_delete);
        menu.setHeaderIcon(android.R.drawable.ic_menu_edit);
        getActivity().getMenuInflater().inflate(R.menu.listview_context_menu,menu);
    }
    //菜单选择事件
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.delete_menu:
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                TextView textView_id = (TextView) info.targetView.findViewById(R.id.textView_id);
                int id = Integer.parseInt(textView_id.getText().toString());
                gamePlayerFragmentListener.delete(id);
                changeData();
                break;
            case R.id.update_menu:
                info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                textView_id = (TextView) info.targetView.findViewById(R.id.textView_id);
                id = Integer.parseInt(textView_id.getText().toString());
                gamePlayerFragmentListener.showUpdateFragment(id);

                break;
        }
        return super.onContextItemSelected(item);
    }

    //通过Adapter把game——player——list——item——layout添加到GamePlayerFragment
    private static class GamePlayerAdapter extends BaseAdapter{

        private Context context;
        private ArrayList<GamePlayer> gamePlayers;
        public void setGamePlayers(ArrayList<GamePlayer> gamePlayers)
        {
            this.gamePlayers = gamePlayers;
        }
        public GamePlayerAdapter(Context context,ArrayList<GamePlayer> gameplayers)
        {
            this.context = context;
            this.gamePlayers = gameplayers;
        }
        @Override
        public int getCount() {
            return gamePlayers.size();
        }

        @Override
        public Object getItem(int position) {
            return gamePlayers.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
              ViewHolder vh = null;
            if(convertView == null)
            {
                convertView = LayoutInflater.from(context).inflate(R.layout.game_player_list_item_layout,null);
                vh = new ViewHolder();
                vh.tv_id = (TextView)convertView.findViewById(R.id.textView_id);
                vh.tv_player = (TextView)convertView.findViewById(R.id.textView_player);
                vh.tv_score = (TextView)convertView.findViewById(R.id.textView_score);
                vh.tv_level = (TextView)convertView.findViewById(R.id.textView_level);
                convertView.setTag(vh);
            }else
            {
                vh = (ViewHolder) convertView.getTag();
            }
            GamePlayer g = gamePlayers.get(position);
            vh.tv_id.setText(String.valueOf(g.getId()));
            vh.tv_player.setText(g.getPlayer());
            vh.tv_score.setText(String.valueOf(g.getScore()));
            vh.tv_level.setText(String.valueOf(g.getLevel()));

            return convertView;
        }
        private static class ViewHolder{
            TextView tv_id;
            TextView tv_player;
            TextView tv_score;
            TextView tv_level;

        }
    }

    //玩家信息改变时需要
    public void changeData()
    {
        gamePlayerAdapter.setGamePlayers(gamePlayerFragmentListener.findAll());
        gamePlayerAdapter.notifyDataSetChanged();
    }

    //解除  把Listener置空
    @Override
    public void onDetach() {
        super.onDetach();
        gamePlayerFragmentListener = null;
    }
}
