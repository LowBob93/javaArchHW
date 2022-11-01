package ru.geekbrains.patterns.flyweight;

public class FlyweightMain {

        public static void main(String[] args) {
            TeaMaker teaMaker = new TeaMaker();
            TeaShop shop = new TeaShop(teaMaker);
            shop.takeOrder(new KarakTea(), 1);
            shop.takeOrder(new KarakTea(), 2);
            shop.takeOrder(new KarakTea(), 5);

            shop.serve();
        }
    }