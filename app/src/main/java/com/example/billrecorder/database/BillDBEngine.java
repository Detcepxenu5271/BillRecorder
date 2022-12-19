package com.example.billrecorder.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.Date;
import java.util.List;

// 实现数据库操作的类（部分通过异步方式）
public class BillDBEngine {
    private static final String TAG = "BillDBEngine";

    private BillDao billDao;

    public BillDBEngine(Context context) {
        // 注意 BillDatabase 是单例的，所以哪怕实例化多个 BillDBEngine 也只会有一个 BillDatabase
        BillDatabase billDatabase = BillDatabase.getInstance(context);
        billDao = billDatabase.get_bill_dao();
    }
    //插入
    public void insert_bill(Bill ... bill) {
        new InsertAsynTask(billDao).execute(bill);
    }
    //更新
    public void update_bill(Bill ... bill) {
        new UpdateAsynTask(billDao).execute(bill);
    }
    //删除
    public void delete_bill(Bill ... bill) {
        new DeleteAsynTask(billDao).execute(bill);
    }
    //全部删除
    public void delete_all_bill(Bill ... bill) {
        new DeleteAllAsynTask(billDao).execute();
    }
    //全部查询 TODO: 通过异步实现（需要解决类如何交互的问题）
    // public LiveData<List<Bill>> query_all_bill() {
    public List<Bill> query_all_bill() {
        return billDao.get_all_bill();
    }
    /*public Bill query_bill_by_id(int id) {
        return billDao.get_bill_by_id(id);
    }*/
    public List<Bill> query_k_latest_bill(int k) {
        return billDao.get_k_latest_bill(k);
    }

    //开启异步操作
    static class InsertAsynTask extends AsyncTask<Bill,Void,Void> {
        private BillDao billDao;
        public InsertAsynTask(BillDao billDao) {
            this.billDao = billDao;
        }

        @Override
        protected Void doInBackground(Bill ... bills) {
            billDao.insert_bill(bills);
            return null;
        }
    }
    static class UpdateAsynTask extends AsyncTask<Bill,Void,Void> {
        private BillDao billDao;
        public UpdateAsynTask(BillDao billDao) {
            this.billDao = billDao;
        }

        @Override
        protected Void doInBackground(Bill... bills) {
            this.billDao.update_bill(bills);
            return null;
        }
    }
    static class DeleteAsynTask extends AsyncTask<Bill,Void,Void> {
        private BillDao billDao;
        public DeleteAsynTask(BillDao billDao) {
            this.billDao = billDao;
        }

        @Override
        protected Void doInBackground(Bill... bills) {
            this.billDao.delete_bill(bills);
            return null;
        }
    }
    //全部删除
    static class DeleteAllAsynTask extends AsyncTask<Void,Void,Void> {
        private BillDao billDao;
        public DeleteAllAsynTask(BillDao billDao) {
            this.billDao = billDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            this.billDao.delete_all_bill();
            return null;
        }
    }
    /*static class QuaryAllAsynTask extends AsyncTask<Void,Void,Void> {

        private BillDao billDao;
        public QuaryAllAsynTask(BillDao billDao) {
            this.billDao = billDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            List<Bill> all_bill = this.billDao.get_all_bill();
            //遍历全部查询的结果
            for (Bill bill:all_bill) {
                Log.i(TAG, "doInBackground: "+bill.toString());
            }
            return null;
        }
    }*/
}
