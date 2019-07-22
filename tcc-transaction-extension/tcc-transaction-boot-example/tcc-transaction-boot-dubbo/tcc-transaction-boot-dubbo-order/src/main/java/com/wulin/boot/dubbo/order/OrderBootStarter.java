package com.wulin.boot.dubbo.order;

import org.apel.gaia.container.boot.PlatformStarter;

public class OrderBootStarter {

	public static void main(String[] args) {
		PlatformStarter.start(args);
		System.out.println("order 启动成功!");
	}
}
