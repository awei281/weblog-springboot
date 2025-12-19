package com.wlog.wlogcommon.designmode.flyweight;

/**
 * @author： wsw
 * @date： 2025/12/19 15:01
 * @describe：
 */
public class FlyweightTest {
    public static void main(String[] args) {
        AddressFlyweight addressFlyweightMap = AddressFlyweightFactory.getAddressFlyweightMap("a", "Arial", "12");
        addressFlyweightMap.display(10, 10);
        /**
         * 享元模式总结就是确实是能减少对象的创建 尤其是有很多通用字段一致的字段,但是对于大量的比如游戏的中的树木,编辑器中的字符,该有的单个对象依旧是独立存在的,只是说维护了相对较少的属性,
         * 大量的属性交给享元模式中 ConcreteFlyweight 来维护 并交给 享元工厂进行创建  这个创建应该也是需要单例的吧
         */
        /**
         * 享元模式并不会减少业务对象本身的创建数量，
         * 而是通过将对象中可共享的内部状态抽取为享元对象，
         * 使大量业务对象持有对少量享元实例的引用，
         * 从而避免重量级属性的重复创建，显著降低内存占用和对象构建成本。
         */
    }

}
