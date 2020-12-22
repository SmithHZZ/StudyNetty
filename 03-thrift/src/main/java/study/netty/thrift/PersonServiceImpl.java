package study.netty.thrift;

import org.apache.thrift.TException;
import thrift.generated.DataException;
import thrift.generated.Person;
import thrift.generated.PersonService;

/**
 * Author：zhuangzhuang.hu
 * Date: 2020/12/22
 * Desc:
 **/
public class PersonServiceImpl implements PersonService.Iface {


    @Override
    public Person getPersonByUsername(String username) throws DataException, TException {
        System.out.println("Got Client");

        Person person = new Person();
        person.setUsername(username);
        person.setAge(20);
        person.setMarried(false);
        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println("Got Client Param:");

        System.out.println(person.getUsername());

        System.out.println(person.getAge());

        System.out.println(person.isMarried());
    }
}
