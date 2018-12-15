package com.tradair.orderbook;

import com.tradair.orderbook.common.Order;
import com.tradair.orderbook.service.BookManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderBookApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void addTrades() {
		boolean OK = false;

		BookManager bookManager = new BookManager();

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

		OK = bookManager.accept(bid1) &&
				bookManager.accept(bid2) &&
				bookManager.accept(bid3) &&
				bookManager.accept(bid4) &&
				bookManager.accept(bid5);

		Assert.assertTrue(OK);

		OK = bookManager.accept(ask1) &&
			bookManager.accept(ask2) &&
			bookManager.accept(ask3) &&
			bookManager.accept(ask4) &&
			bookManager.accept(ask5);

		Assert.assertTrue(OK);


		Assert.assertEquals(bookManager.getBestBid(), 131.25, 0);
		Assert.assertEquals(bookManager.getBestOffer(), 126.25, 0);

	}

}

