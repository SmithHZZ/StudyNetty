package study.netty.a.hello;

import study.netty.protobuf.DataInfo;

/**
 * Author：zhuangzhuang.hu
 * Date: 2020/11/11
 * Desc:
 **/
public class ProtoBufTest {

    public static void main(String[] args) {
        try {
            DataInfo.Student student = DataInfo.Student.newBuilder().setName("Hello").setAge(18).setAddress("北京市东城区中南海").build();

            byte[] bytes = student.toByteArray();

            DataInfo.Student stu = DataInfo.Student.parseFrom(bytes);

            System.out.println(stu);
            System.out.println(stu.getName());
            System.out.println(stu.getAge());
            System.out.println(stu.getAddress());

        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }

}
