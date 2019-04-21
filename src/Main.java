import com.zhouwei.study.proxy.cglib.CglibAgency;
import com.zhouwei.study.proxy.customer.CustomerAgency;
import com.zhouwei.study.proxy.jdk.JdkAgency;
import com.zhouwei.study.proxy.Renter;
import com.zhouwei.study.proxy.SpecialRenter;

public class Main {

    /**
     * jdk代理模式的测试
     */
    private static void testJdkProxy(){
        try {
            Renter renter = (Renter) new JdkAgency().getInstance(new SpecialRenter());
            renter.findHouse();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * cglib代理模式的测试
     */
    private static void testCglibProxy(){
        try {
            SpecialRenter renter = (SpecialRenter) new CglibAgency().getInstance(SpecialRenter.class);
            renter.findHouse();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 自定义代理模式的测试
     */
    private static void testCustomerProxy(){
        try {
            Renter renter = (Renter) new CustomerAgency().getInstance(new SpecialRenter());
            renter.findHouse();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //jdk
        testJdkProxy();
        //cglib
        //testCglibProxy();
        //customer
        //testCustomerProxy();

    }
}
