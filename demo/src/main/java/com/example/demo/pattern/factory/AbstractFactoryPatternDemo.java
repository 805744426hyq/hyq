package com.example.demo.pattern.factory;

/**
 * @author 黄永琦
 * @description
 * @date 2021/7/9
 */
public class AbstractFactoryPatternDemo {
	public static void main(String[] args) {
		//获取形状工厂
		AbstractFactory shapeFactory = FactoryProducer.getFactory("形状");

		//获取形状为 Circle 的对象
		Shape shape1 = shapeFactory.getShape("圆形");

		//调用 Circle 的 draw 方法
		shape1.draw();

		//获取形状为 Rectangle 的对象
		Shape shape2 = shapeFactory.getShape("长方形");

		//调用 Rectangle 的 draw 方法
		shape2.draw();

		//获取形状为 Square 的对象
		Shape shape3 = shapeFactory.getShape("正方形");

		//调用 Square 的 draw 方法
		shape3.draw();

		//获取颜色工厂
		AbstractFactory colorFactory = FactoryProducer.getFactory("颜色");

		//获取颜色为 Red 的对象
		Color color1 = colorFactory.getColor("红色");

		//调用 Red 的 fill 方法
		color1.fill();

		//获取颜色为 Green 的对象
		Color color2 = colorFactory.getColor("绿色");

		//调用 Green 的 fill 方法
		color2.fill();

		//获取颜色为 Blue 的对象
		Color color3 = colorFactory.getColor("蓝色");

		//调用 Blue 的 fill 方法
		color3.fill();
	}
}
