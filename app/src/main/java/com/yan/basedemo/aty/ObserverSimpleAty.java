package com.yan.basedemo.aty;

import android.os.Environment;
import android.view.View;

import com.yan.base.BaseAty;
import com.yan.basedemo.R;
import com.yan.basedemo.bean.Cat;
import com.yan.basedemo.bean.Mouse;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import butterknife.OnClick;

/**
 * Created by YanZi on 2018/5/23.
 * describe：
 * modify:
 * modify date:
 */
public class ObserverSimpleAty extends BaseAty {

    Cat cat = new Cat("大脸猫",1,false);

    Mouse mouse =new Mouse("小老鼠",1);


    @Override
    protected int setContentLayout() {
        return R.layout.aty_observer_simple;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initData() {
        mouse.setCat(cat);
    }

    @OnClick({R.id.btn_observer_simple_out, R.id.btn_observer_simple_in})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_observer_simple_out:
                cat.setOut(true, "草地");
                loadFile();
                System.out.println(ADDRESS);
                System.out.println(machine_id);
                break;
            case R.id.btn_observer_simple_in:
                cat.setOut(false, "老窝");
                break;
            default:
                break;
        }
    }
    String ADDRESS;
    String machine_id;

    private void loadFile(){
        //先读取配置文件
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"ip_set.txt";//手机上地址
        System.out.println(filePath);
        try {
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {       //文件存在的前提
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file),getCharset(file));
                BufferedReader br = new BufferedReader(isr);
                String lineTxt =br.readLine() ;
                if(lineTxt!=null){
                    lineTxt = lineTxt.replaceAll(" ","");
                    lineTxt = lineTxt.replaceAll("　","");
                    lineTxt=lineTxt.replaceAll(" ","");
                    String[] a=   lineTxt.split("=");
                    ADDRESS=a[1];
                }
                String lineTxt2 =br.readLine() ;
                if(lineTxt2!=null){
                    lineTxt2 = lineTxt2.replaceAll("　","");
                    lineTxt2 = lineTxt2.replaceAll(" ","");
                    lineTxt2=lineTxt2.replaceAll(" ","");
                    String[] a=   lineTxt2.split("=");
                    machine_id=a[1];
                }
                isr.close();
                br.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String getCharset(File fileName) throws IOException {

        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fileName));
        int p = (bin.read() << 8) + bin.read();

        String code = null;

        switch (p) {
            case 0xefbb:
                code = "UTF-8";
                break;
            case 0xfffe:
                code = "Unicode";
                break;
            case 0xfeff:
                code = "UTF-16BE";
                break;
            default:
                code = "GBK";
        }
        return code;
    }
}
