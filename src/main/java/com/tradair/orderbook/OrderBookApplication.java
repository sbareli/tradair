package com.tradair.orderbook;

import com.tradair.orderbook.common.Order;
import com.tradair.orderbook.service.Checker;
import com.tradair.orderbook.service.MyTask;
import com.tradair.orderbook.service.Referent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.ref.ReferenceQueue;

@SpringBootApplication
public class OrderBookApplication {

	public static void main(String[] args) throws Exception
	{
		SpringApplication.run(OrderBookApplication.class, args);

		Order order = new Order("ABC", 1, 12345, Order.OrderSide.Bid, "IBM", 100, Order.OrderType.Limit, 129.25);

		ReferenceQueue queue = new ReferenceQueue();

		Checker<Order> checker = new Checker<>(queue);

		Referent<Order> wf = new Referent<>(order, queue);

		MyTask task = new MyTask();
		Thread T1 = new Thread(task);
		T1.start();
		T1.join();

		Thread test = new Thread(checker);
		test.start();

//		boolean e = wf.enqueue();
//		if (e) {
//			System.out.println("Enqueued");
//		}

		order = wf.get();
		order = null;

		test.join(10000);

		System.out.println("Running GC");
		Runtime.getRuntime().gc();

		test.join(10000);
	}

}

