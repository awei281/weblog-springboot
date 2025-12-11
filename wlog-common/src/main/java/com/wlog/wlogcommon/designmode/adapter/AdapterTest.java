package com.wlog.wlogcommon.designmode.adapter;

/**
 * @author： wsw
 * @date： 2025/12/11 16:20
 * @describe：
 */
public class AdapterTest {
    public static void main(String[] args) {

        Mobile mobile = new Mobile();

        V220Power v220Power = new V220Power();
        V5Power adapter = new Charger(v220Power);

        mobile.inputPower(adapter);


    }
}
