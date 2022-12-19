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

        insertTestBill(); // TODO: 测试用，回头连着下面的函数一起删掉
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

    private void insertTestBill() {
        List<Bill> bills = query_all_bill();
        if (bills.isEmpty()) {
            insert_bill(new Bill(0, 1641009600886L, 12.0, "测试一")); // 2022.1.1 12:00:00
            insert_bill(new Bill(0, 1643774400899L, 11.0, "测试二")); // 2022.2.2 12:00:00
            insert_bill(new Bill(0, 1646280000900L, 10.0, "测试三")); // 2022.3.3 12:00:00
            insert_bill(new Bill(0, 1649044800900L, 9.0, "测试四")); // 2022.4.4 12:00:00
            insert_bill(new Bill(0, 1651723200900L, 8.0, "测试五")); // 2022.5.5 12:00:00
            insert_bill(new Bill(0, 1654488000900L, 7.0, "测试六")); // 2022.6.6 12:00:00
            insert_bill(new Bill(0, 1657166400900L, 6.0, "测试七")); // 2022.7.7 12:00:00
            insert_bill(new Bill(0, 1659931200900L, 5.0, "测试八")); // 2022.8.8 12:00:00
            insert_bill(new Bill(0, 1662696000900L, 4.0, "测试九")); // 2022.9.9 12:00:00
            insert_bill(new Bill(0, 1665374400900L, 3.0, "测试十")); // 2022.10.10 12:00:00
            insert_bill(new Bill(0, 1668139200900L, 2.0, "测试十一")); // 2022.11.11 12:00:00
            insert_bill(new Bill(0, 1670817600901L, 1.0, "测试十二")); // 2022.12.12 12:00:00
            insert_bill(new Bill(0, 1672545600348L, 99.99, "测试十三")); // 2023.1.1 12:00:00
        }
    }
}
