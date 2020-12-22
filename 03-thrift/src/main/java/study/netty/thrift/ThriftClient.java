package study.netty.thrift;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import thrift.generated.Person;
import thrift.generated.PersonService;

/**
 * Author：zhuangzhuang.hu
 * Date: 2020/12/22
 * Desc:
 **/
public class ThriftClient {

    public static void main(String[] args) {
        TTransport transport = new TFramedTransport(new TSocket("localhost",8899),600);
        TProtocol protocol = new TCompactProtocol(transport);
        PersonService.Client client = new PersonService.Client(protocol);
        try {

            transport.open();

            Person person = client.getPersonByUsername("HelloWorld");

            System.out.println(person.getUsername());
            System.out.println(person.getAge());
            System.out.println(person.isMarried());

            client.savePerson(person);

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        } finally {
            transport.close();
        }
    }
}
