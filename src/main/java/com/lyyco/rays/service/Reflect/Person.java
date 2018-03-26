package com.lyyco.rays.service.Reflect;

import java.lang.reflect.InvocationTargetException;

/**
 * com.lyyco.rays.service.Reflect
 *
 * @Author liyangyang
 * 2018/3/22
 */
public class Person extends Entity<Person> {
    private String name;
    private Integer age;
    private Long id;
    private Double salary;
//    public Person(String name,int age){
//        this.age = age;
//        this.name = name;
//    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }



    public Person _setAge(int age) {
        this.age = age;
        return this;
    }

    public static void main(String... args) throws Exception {

        Person p = new Person();
        p.setId(1L);
        p.setAge(21);
        p.setName("Sasaki");
        p.setSalary(32000.0);

        Person p_ = new Person();
        // 链式方式设置属性
        p_.set(p_, "id", 1L).set(p_, "age", 21).set(p_, "name", "Sasaki").set(p_, "salary", 32000.0);

        assert p.getId() == p_.getId();
        assert p.getAge() == p_.getAge();
        assert p.getName().equals(p_.getName());
        assert p.getSalary().equals(p_.getSalary());

        System.out.println("p & p_ matched.");

        UtilArrayList<Person> list = new UtilArrayList<>();
        list.add(p);
        list.add(p);
        list.add(p);

        UtilArrayList<Person> list_ = new UtilArrayList<>();
        list_._add(p_)._add(p_)._add(p_);// 链式方式List添加元素

        assert list_.size() == 3;

        UtilHashMap<String, Person> map = new UtilHashMap<>();
        map._put("1", p_)._put("2", p);// 链式方式Map添加元素

        assert map.size() == 2;

        Person p2 = new Person();
        p2.set(p2, "id", 1L).set(p2, "age", 21).set(p2, "name", "Sasaki").set(p2, "salary", 32000.0);
        Object[][] attrs_$attr = new Object[2][];
        attrs_$attr[0] = new String[]{"id", "age", "name", "salary"};
        attrs_$attr[1] = new Object[]{1L, 21, "Sasaki", 32000.0};
        p2.setMultiple(p2, attrs_$attr);

        System.out.println(toString(p2));
    }

}


