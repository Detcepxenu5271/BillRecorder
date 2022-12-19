package com.example.billrecorder.iflytek;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.billrecorder.AddActivity;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ASRUtil {

    public static String appid = "6b98ee69";

    private AddActivity addActivity;

    public void setAddActivity(AddActivity addActivity) { this.addActivity = addActivity; }

    // -------- 识别结果 --------

    private String result;

    // -------- API 函数 --------

    // 初始化语音配置对象，需要放在程序入口处（如 Activity 的 onCreate 方法中
    public void InitASR(Context context) {
        SpeechUtility.createUtility(context, SpeechConstant.APPID + "=" + appid);
    }

    public void StartSpeech(Context context) {
        SpeechRecognizer mIat = SpeechRecognizer.createRecognizer(context, null);

        //设置语法ID和 SUBJECT 为空，以免因之前有语法调用而设置了此参数
        if (mIat == null) {
            Log.d("ASR", "mIat is null");
            return;
        }
        mIat.setParameter( SpeechConstant.CLOUD_GRAMMAR, null );
        mIat.setParameter( SpeechConstant.SUBJECT, null );
        //设置返回结果格式，目前支持json,xml以及plain 三种格式，其中plain为纯听写文本内容
        mIat.setParameter(SpeechConstant.RESULT_TYPE, "plain"); // 听写纯文本内容
        //此处engineType为“cloud”
        mIat.setParameter( SpeechConstant.ENGINE_TYPE, "cloud" );
        //设置语音输入语言，zh_cn为简体中文
        mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        //设置结果返回语言
        mIat.setParameter(SpeechConstant.ACCENT, "mandarin"); // 普通话
        // 设置语音前端点:静音超时时间，单位ms，即用户多长时间不说话则当做超时处理
        //取值范围{1000～10000}
        mIat.setParameter(SpeechConstant.VAD_BOS, "4000");
        //设置语音后端点:后端点静音检测时间，单位ms，即用户停止说话多长时间内即认为不再输入，
        //自动停止录音，范围{0~10000}
        mIat.setParameter(SpeechConstant.VAD_EOS, "1000");
        //设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
        mIat.setParameter(SpeechConstant.ASR_PTT,"0"); // 无标点

        //开始识别，并设置监听器
        mIat.startListening(mRecogListener);

        Log.d("ASR", "start listener");
    }

    // -------- Recognizer 监听器 --------

    private final RecognizerListener mRecogListener = new RecognizerListener() {
        @Override
        public void onVolumeChanged(int i, byte[] bytes) {

        }

        @Override
        public void onBeginOfSpeech() {
            Log.d("ASR", "onBeginOfSpeech");

            result = ""; // 初始化 result 为空字符串
        }

        @Override
        public void onEndOfSpeech() {
            Log.d("ASR", "onEndOfSpeech");

            addActivity.SpeechFinish();
        }

        @Override
        public void onResult(RecognizerResult recognizerResult, boolean b) {
            Log.d("ASR", "onResult");
            // onResult 函数有可能在一次会话中多次调用，所以需要拼接每次返回的 plain 文本
            if (recognizerResult != null) { // recognizerResult 可能为 null
                result = result + recognizerResult.getResultString();
                Log.d("ASR", "return: " + recognizerResult.getResultString() + ", " + b);
                Log.d("ASR", "result: " + result);
            }
            if (b) { // 语音识别结束
                // 处理语音输入结果，提取类别、价格、备注信息
                Pattern p;
                Matcher m;

                // 提取类别（收入/支出）
                boolean is_outcome = true;
                p = Pattern.compile("(收入|支出)");
                m = p.matcher(result);
                while (m.find()) {
                    if (Objects.equals(m.group(0), "收入")) {
                        is_outcome = false;
                    } else
                    if (Objects.equals(m.group(0), "支出")) {
                        is_outcome = true;
                    } else {
                        is_outcome = true; // 没有识别到收入/支出，默认支出
                    }
                }
                result = m.replaceAll(""); // 除去“收入”和“支出”（替换为空串）

                // 提取金额和备注
                double amount = 0.0;
                String note = "";
                p = Pattern.compile("(\\D*)(\\d+(\\.\\d+|))(元|块钱|块|)(.*)");
                m = p.matcher(result);
                if (m.find()) {
                    amount = Double.parseDouble(Objects.requireNonNull(m.group(2)));
                    note = m.group(1) + m.group(5);
                }

                // 返回结果
                addActivity.setIs_outcome(is_outcome);
                addActivity.setAmount(amount);
                addActivity.setNote(note);

                addActivity.RecognizeFinish();
            }
        }

        @Override
        public void onError(SpeechError speechError) {

        }

        @Override
        public void onEvent(int i, int i1, int i2, Bundle bundle) {

        }
    };
}
