package com.kc.inspiration.thread;

import com.kc.inspiration.biz.IAuctionBiz;
import com.kc.inspiration.biz.impl.AuctionBizImpl;
import com.kc.inspiration.dao.ITradeDao;
import com.kc.inspiration.dao.impl.TradeDaoImpl;

public class AuctionThread extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		//创建AuctionBiz
		IAuctionBiz auctionBiz = new AuctionBizImpl();
		ITradeDao tradeDao=new TradeDaoImpl();
		int affectedRows;
		int tradeNumber;
		while(true){
			try {
				Thread.sleep(5000);
				//System.out.println("[AuctionThread]: > 后台监控拍卖状态......");
				// 在这里监控全站的拍卖状态
				//说明：status=1：表示还未开始拍卖；status=2：表示正在拍卖；status=3:表示拍卖结束
				//更新拍卖状态
				//affectedRows=auctionBiz.update();
				//System.out.println("[AuctionThread]: > 修改了"+affectedRows+"条记录！");
				// 查询结束的auction，检查是否有交易成功的
				//tradeNumber=tradeDao.check();
				//System.out.println("[AuctionThread]: > 生成了"+tradeNumber+"条交易!");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
