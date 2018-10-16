package com.dao.model;

import com.dao.controler.Global;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2018/9/26.
 */
public class FileOperating {

    public static ArrayList<goods> readKind(){
		/*
		 * ��ȡgoodsDataFile
		 */
        FileInputStream fis=null;
        ObjectInputStream ois=null;
        ArrayList<goods> res=null;
        try {
            fis=new FileInputStream(Global.goodsDataFile);if(fis.available()!=0) {
                try {
                    ois = new ObjectInputStream(fis);
                } catch (EOFException ioe) {
                    //无法避免的异常
                }
                res=(ArrayList<goods>) ois.readObject();
            }else{
                return new ArrayList<>();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                if(ois!=null)
                   ois.close();
                else if(fis!=null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        return res;
    }

    public static void writeKind(ArrayList<goods> arr){
		/*
		 * ������Ʒ
		 * ��arrд���ļ�goodsDataFile
		 */
        FileOutputStream fos= null;
        try {
            fos = new FileOutputStream(Global.goodsDataFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        ObjectOutputStream oos= null;

        try {
            oos = new ObjectOutputStream(fos);
            oos.writeObject(arr);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }finally {
            try {
                if(oos!=null)
                    oos.close();
                else if(fos!=null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void write(String name,ArrayList<goods> g){
		/*
		 * ��gд���ļ�buyFile
		 */
        ConcurrentHashMap<String,ArrayList> map= null;
        map = read();
        if(!map.contains(name))
            map.put(name, g);
        else{
            ArrayList<goods> g1=map.get(name);
            for(goods gg:g)
                g1.add(gg);
        }
        FileOutputStream fos= null;
        ObjectOutputStream oos= null;

        try {
            File f=new File(Global.buyFile);
            fos = new FileOutputStream(f,false);
            if(f.length()>0){
                oos = new ObjectOutputStream(fos){
                    @Override
                    protected void writeStreamHeader() throws IOException {

                    }
                };
            }else{
                oos=new ObjectOutputStream(fos);
            }
            oos.writeObject(map);
            oos.flush();
        } catch (NotSerializableException nse){

        }catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }catch (IOException e) {
            e.printStackTrace();
            return;
        }finally {
            try {
                if(oos!=null)
                    oos.close();
                else if(fos!=null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ConcurrentHashMap<String,ArrayList> read() {
		/*
		 * ��ȡ�ļ�buyFile�е�ConcurrentHashMap
		 *
		 */
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        ConcurrentHashMap<String,ArrayList> res=null;
        try {
            fis = new FileInputStream(Global.buyFile);
            if (fis.available() != 0) {
                ois = new ObjectInputStream(fis);
            }else {
                return new ConcurrentHashMap<String,ArrayList>();
            }

            res=(ConcurrentHashMap<String,ArrayList>) ois.readObject();

        }catch (NotSerializableException|WriteAbortedException nse) {

        }catch(EOFException eof){
            return new ConcurrentHashMap<String,ArrayList>();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                if(ois!=null)
                    ois.close();
                else if(fis!=null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        return res==null?new ConcurrentHashMap<>():res;
    }
}
