package Other;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class BigIntegerAndDecimal {
    public static void main(String[] args) {
        //大数阶乘
        int n = 50;//(表示n的阶乘)
        BigInteger res1 = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            res1 = res1.multiply(BigInteger.valueOf(i));
        }
        System.out.println(n+"的阶乘为："+res1);
        //大数取模
        BigInteger base = new BigInteger("12345678901234567890");
        BigInteger exponent = new BigInteger("1000");
        BigInteger modulus = new BigInteger("9876543210");
        BigInteger res2 = base.modPow(exponent, modulus);//表示 base ^ exponent % modulus
        System.out.println(base+" ^ "+exponent+" % "+modulus+" = "+res2);
        //高精度除法(1/7,保留50位小数)
        BigDecimal numertor=new BigDecimal(1);
        BigDecimal denominator=new BigDecimal(7);
        BigDecimal res3=numertor.divide(denominator,50, RoundingMode.HALF_UP);
        System.out.println(numertor+" / "+denominator+" = "+res3);
        System.out.println(numertor+" / "+denominator+"的小数位数为："+res3.scale());
        //判断一个数是否为素数
        BigInteger two=BigInteger.valueOf(2);
        BigInteger meisen=two.pow(127).subtract(BigInteger.valueOf(1));//表示梅森素数（2^127-1）
        BigInteger mersenne=new BigInteger(String.valueOf(meisen));
        System.out.println(meisen+" 是否为素数: "+mersenne.isProbablePrime(100));
    }
}
