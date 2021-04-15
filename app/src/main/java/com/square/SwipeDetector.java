package com.square;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Fragment;

public class SwipeDetector extends Fragment {

    private UpdateBytesHandler updateBytesHandler;
    private UpdateBitsHandler updateBitsHandler;
    private TextView decodedStringView;
    private TextView strippedBinaryView;
    private Button startBtn;
    private MagRead read;

    // TODO: Rename and change types and number of parameters
    public static SwipeDetector newInstance() {
        SwipeDetector fragment = new SwipeDetector();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_swipe_detector, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        startBtn = view.findViewById(R.id.startbtn);
//        stopBtn = (Button)findViewById(R.id.stopbtn);
//        stopBtn.setEnabled(false);
        decodedStringView = view.findViewById(R.id.bytes);
        strippedBinaryView = view.findViewById(R.id.bits);

        read = new MagRead();
        read.addListener(new MagReadListener() {

            @Override
            public void updateBytes(String bytes) {
                Message msg = new Message();
                msg.obj = bytes;
                updateBytesHandler.sendMessage(msg);
            }

            @Override
            public void updateBits(String bits) {
                Message msg = new Message();
                msg.obj = bits;
                updateBitsHandler.sendMessage(msg);

            }
        });
        MicListener ml = new MicListener();
        startBtn.setOnClickListener(ml);
//        stopBtn.setOnClickListener(ml);
        updateBytesHandler = new UpdateBytesHandler();
        updateBitsHandler = new UpdateBitsHandler();
    }

    private class MicListener implements View.OnClickListener {
        /**
         * Called when the mic button is clicked
         *
         * @param
         */
        @Override
        public void onClick(View v) {
//            if(v == stopBtn){//stop listening
//                stopBtn.setEnabled(false);
//                startBtn.setEnabled(true);
//                read.stop();
//            }else if(v == startBtn) {//start listening
//            stopBtn.setEnabled(true);
            startBtn.setEnabled(false);
            read.start();
//          }
        }

    }

    private class UpdateBytesHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            String bytes = (String) msg.obj;
            decodedStringView.setText(bytes);
            read.stop();
            Toast.makeText(getContext(), "Now move towards payment", Toast.LENGTH_SHORT).show();
        }

    }

    private class UpdateBitsHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            String bits = (String) msg.obj;
            strippedBinaryView.setText(bits);
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        read.release();
    }

}