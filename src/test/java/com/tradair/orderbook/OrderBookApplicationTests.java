package com.tradair.orderbook;

import com.tradair.orderbook.common.Order;
import com.tradair.orderbook.common.OrderKey;
import com.tradair.orderbook.service.AssetManager;
import com.tradair.orderbook.service.BookManager;
import com.tradair.orderbook.service.Checker;
import com.tradair.orderbook.service.Referent;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderBookApplicationTests {

	Logger logger = LoggerFactory.getLogger(OrderBookApplicationTests.class);

	@Test
	public void contextLoads() {
	}

	@Test
	public void addTrades()
	{
		boolean OK;

		AssetManager assetManager = new AssetManager();

		BookManager bookManager = assetManager.getBookManager("IBM");


		Order bid1 = new Order("ABC", 1, 12345, Order.OrderSide.Bid, "IBM", 100, Order.OrderType.Limit, 129.25);
		Order bid2 = new Order("ABC", 2, 12345, Order.OrderSide.Bid, "IBM", 100, Order.OrderType.Limit, 130.25);
		Order bid3 = new Order("ABC", 3, 12345, Order.OrderSide.Bid, "IBM", 100, Order.OrderType.Limit, 127.25);
		Order bid4 = new Order("ABC", 4, 12345, Order.OrderSide.Bid, "IBM", 100, Order.OrderType.Limit, 131.25);
		Order bid5 = new Order("ABC", 5, 12345, Order.OrderSide.Bid, "IBM", 100, Order.OrderType.Limit, 128.25);

		Order ask1 = new Order("XYZ", 6, 12345, Order.OrderSide.Offer, "IBM", 100, Order.OrderType.Limit, 129.25);
		Order ask2 = new Order("XYZ", 7, 12345, Order.OrderSide.Offer, "IBM", 100, Order.OrderType.Limit, 128.25);
		Order ask3 = new Order("XYZ", 8, 12345, Order.OrderSide.Offer, "IBM", 100, Order.OrderType.Limit, 131.25);
		Order ask4 = new Order("XYZ", 9, 12345, Order.OrderSide.Offer, "IBM", 100, Order.OrderType.Limit, 127.25);
		Order ask5 = new Order("XYZ", 10, 12345, Order.OrderSide.Offer, "IBM", 100, Order.OrderType.Limit, 126.25);

		OK = bookManager.newOrder(bid1) &&
				bookManager.newOrder(bid2) &&
				bookManager.newOrder(bid3) &&
				bookManager.newOrder(bid4) &&
				bookManager.newOrder(bid5);

		Assert.assertTrue(OK);

		OK = bookManager.newOrder(ask1) &&
			bookManager.newOrder(ask2) &&
			bookManager.newOrder(ask3) &&
			bookManager.newOrder(ask4) &&
			bookManager.newOrder(ask5);

		Assert.assertTrue(OK);


		Assert.assertEquals(bookManager.getBestBid(), 131.25, 0);
		Assert.assertEquals(bookManager.getBestOffer(), 126.25, 0);

		logger.info("Print bids");
		bookManager.getBidValues().forEach(
				order -> logger.info(order.toString())
		);

		logger.info("Print offers");
		bookManager.getOfferValues().forEach(
				order -> logger.info(order.toString())
		);

		List<Order> depth = bookManager.getBidsDepth(130, "ABC");
		logger.info("Best bids better then:" + Double.toString(130));
		depth.forEach(o->logger.info(o.toString()));

		depth = bookManager.getOfferDepth(130, "ABC");
		logger.info("Best asks better then:" + Double.toString(130));
		depth.forEach(o->logger.info(o.toString()));

	}

	@Test
	public void checkReefence() throws Exception
	{
		Order order = new Order("ABC", 1, 12345, Order.OrderSide.Bid, "IBM", 100, Order.OrderType.Limit, 129.25);

		ReferenceQueue queue = new ReferenceQueue();

		Checker<Order> checker = new Checker<>(queue);

		Referent<Order> wf = new Referent<>(order, new ReferenceQueue<>());

		Thread test = new Thread(checker);
		test.start();

		wf.clear();

		test.join(5000);
	}

}

