package com.ben.media_playback;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MusicIntentReceiver extends BroadcastReceiver {

    private static final String action = "android.media.AUDIO_BECOMING_NOISY";

    public MusicIntentReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(action))
        {
            Intent intent1 = new Intent(MusicService.ACTION_STOP);
            context.startService(intent1);
        }
    }
}
