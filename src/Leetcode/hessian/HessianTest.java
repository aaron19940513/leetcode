package leetcode.hessian;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

import org.junit.Test;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

public class HessianTest implements Serializable{


    private static final long serialVersionUID = 2265642052862362376L;




    @Test
    public void test() throws IOException {
        UserInfo user = new UserInfo();
        user.setUsername("hello world");
        user.setPassword("buzhidao");
        user.setAge(21);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        //Hessian的序列化输出
        HessianOutput ho = new HessianOutput(os);
        ho.writeObject(user);

        byte[] userByte = os.toByteArray();
        ByteArrayInputStream is = new ByteArrayInputStream(userByte);


        //Hessian的反序列化读取对象
        HessianInput hi = new HessianInput(is);
        UserInfo u = (UserInfo) hi.readObject();
        System.out.println("姓名：" + u.getUsername());
        System.out.println("年龄：" + u.getAge());
    }

}
